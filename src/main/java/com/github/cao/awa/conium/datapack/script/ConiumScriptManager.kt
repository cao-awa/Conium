package com.github.cao.awa.conium.datapack.script

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.script.ScriptExport
import com.github.cao.awa.conium.script.eval.ScriptEval
import com.github.cao.awa.conium.script.kts.ConiumScript
import com.github.cao.awa.language.translator.builtin.typescript.antlr.TypescriptLexer
import com.github.cao.awa.language.translator.builtin.typescript.antlr.TypescriptParser
import com.github.cao.awa.language.translator.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.language.translator.builtin.typescript.tree.TypescriptFile
import com.github.cao.awa.language.translator.builtin.typescript.visitor.LanguageTypescriptVisitor
import com.github.cao.awa.language.translator.translate.LanguageTranslator
import com.github.cao.awa.language.translator.translate.lang.TranslateTarget
import com.github.cao.awa.sinuatum.resource.loader.ResourceLoader
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.sinuatum.util.io.IOUtil
import net.minecraft.registry.RegistryKeys
import net.minecraft.resource.*
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.antlr.v4.runtime.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

/**
 * A dedicated script manager for conium that translate and compile scripts then evaluates.
 *
 * ConiumScriptManager is script dynamic loadable.
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
class ConiumScriptManager : SinglePreparationResourceReloader<MutableMap<Identifier, Resource>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumScriptManager")
        private val DATA_TYPE = RegistryKeys.getPath(ConiumRegistryKeys.SCRIPTS)

        // Commons script here, all script uses theses script.
        private val defaultCommons = IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.commons.kts"))
        private val defaultBedrockScriptInit =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.script.init.kts"))
        private val defaultBedrockCommons =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.commons.kts"))

        /**
         * The 'host' is kotlin scripting host that used to compile and evaluate the kotlin scripts.
         *
         * Don't use JSR223 API to evaluates scripts, it will cause unexpected produce env problems!
         */
        private val host = BasicJvmScriptingHost()
    }

    /**
     * The 'exportedScript' is shared script context when script return a '[ScriptExport]'
     *
     * Other scripts can use comment to import these contexts where they marked as:
     *
     * '// IMPORT: ExportName' or '// IMPORT: ExportName, OtherExportName'
     */
    private val exportedScript = CollectionFactor.hashMap<String, ScriptExport>()

    /**
     * Prepares the intermediate object.
     *
     * This method is called in the prepare executor in a reload.
     *
     * @param profiler the prepare profiler
     * @param manager the resource manager
     *
     * @return the prepared object
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun prepare(manager: ResourceManager, profiler: Profiler): MutableMap<Identifier, Resource> =
        CollectionFactor.hashMap<Identifier, Resource>().also {
            load(manager, it)
        }

    /**
     * Prepares the intermediate object.
     *
     * @param manager the resource manager
     * @param results the load results
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    private fun load(
        manager: ResourceManager,
        results: MutableMap<Identifier, Resource>
    ) {
        // There are 3 types finder need to load.
        val kotlinFinder = ResourceFinder(DATA_TYPE, ".kts")
        val javascriptFinder = ResourceFinder(DATA_TYPE, ".js")
        val typescriptFinder = ResourceFinder(DATA_TYPE, ".ts")

        // Load kotlin script.
        kotlinFinder.findResources(manager).entries.iterator().forEach {
            results[it.key] = it.value
        }

        // Load javascript.
        javascriptFinder.findResources(manager).entries.iterator().forEach {
            results[it.key] = it.value
        }

        // Load typescript.
        typescriptFinder.findResources(manager).entries.iterator().forEach {
            results[it.key] = it.value
        }
    }

    /**
     * Handles the prepared intermediate object.
     *
     * This method is called in the apply executor, or the game engine, in a reload.
     *
     * @param manager the resource manager
     * @param profiler the apply profiler
     * @param prepared the prepared object
     *
     * @author aco_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    override fun apply(prepared: MutableMap<Identifier, Resource>, manager: ResourceManager, profiler: Profiler) {
        // Clear conium event attaches.
        ConiumEvent.resetForever()

        // Clear old exported script, scripts will export to here again in next step loading
        this.exportedScript.clear()

        // Add script for next step ordered loading.
        CollectionFactor.linkedList<ScriptEval>().let { scripts ->
            // Load commons.
            scripts.add(ScriptEval(defaultCommons))

            // Bedrock common is only load when conium allow bedrock.
            if (Conium.allowBedrock) {
                scripts.add(ScriptEval(defaultBedrockCommons, "ConiumCommons"))
                scripts.add(ScriptEval(defaultBedrockScriptInit, "ConiumCommons", "ConiumBedrockCommons"))
            }

            for (script in prepared) {
                val resource = script.value
                val identifier = script.key

                // Read script source code.
                val content = resource.reader.readText()

                identifier.path.also { path ->
                    if (path.endsWith(".kts")) {
                        // Load script data after.
                        scripts.add(ScriptEval(content, "ConiumCommons"))
                    } else if (!Conium.allowBedrock) {
                        // When disabled bedrock script allows, then script won't be load.
                        LOGGER.warn("Conium are disabled bedrock script, ignored '$identifier'")
                    } else if (path.endsWith(".ts")) {
                        // Load script data after translate typescript to kotlin.
                        scripts.add(
                            ScriptEval(
                                translateBedrockTypescript(content),
                                "ConiumCommons",
                                "ConiumBedrockCommons"
                            )
                        )
                    } else if (path.endsWith(".js")) {
                        // Javascript supports are not done.
                        TODO("Javascript translator are not implemented yet.")
                    }
                }
            }

            // Scripts will compiles and executions in order.
            evalKotlin(scripts)
        }
    }

    private fun readTypescript(content: String): TypescriptFile {
        // Build lexer, parser and prepare tokens.
        val lexer = TypescriptLexer(CharStreams.fromString(content))
        val tokens: TokenStream = CommonTokenStream(lexer)
        val parser = TypescriptParser(tokens)

        // Error handles.
        parser.addErrorListener(object : BaseErrorListener() {
            override fun syntaxError(
                recognizer: Recognizer<*, *>?,
                offendingSymbol: Any,
                line: Int,
                charPositionInLine: Int,
                msg: String,
                e: RecognitionException
            ) {
                throw RuntimeException(
                    "Expected symbol '$offendingSymbol' but error in line $line chars $charPositionInLine: $msg",
                    e
                )
            }
        })

        // Use visitor to builds typescript AST after context parsed.
        return LanguageTypescriptVisitor().visitProgram(parser.program())
    }

    private fun translateBedrockTypescript(source: String): String {
        return readTypescript(source).let { typescriptFile ->

            // Prepares the typescript AST for next step translating.
            typescriptFile.prepares()

            // Translate typescript to conium script (kotlin script with conium API).
            val translated = LanguageTranslator.translate(
                // Use conium provider to processes something additional features.
                // See the package 'com.github.cao.awa.conium.script.translate'
                "conium",
                // Translate to kotlin script.
                TranslateTarget.KOTLIN_SCRIPT,
                // Whole file to translates.
                TypescriptTranslateElement.FILE,
                typescriptFile
            )

            // Bedrock script need post script instance to 'BedrockEventContext',
            // because the 'world' or 'system' or others variables may uniques in different context.
            // The bedrock commons use 'get() = access(this)' to access current context,
            // when context created, it will push to the 'contexts' in 'BedrockEventContext', so 'access' can got current context for this script.
            return@let """
            BedrockEventContext.post(this) {
                $translated
            }
            BedrockEventContext.clearPost()
        """.trimIndent()
        }
    }

    /**
     * Post the kotlin scripts to ordered eval function.
     *
     * @param scripts scripts data
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    private fun evalKotlin(scripts: List<ScriptEval>) = evalKotlin(scripts.iterator())

    /**
     * Compile and evaluate the kotlin scripts in order, post once when callback was called.
     *
     * The callback will be calls after a script runs done.
     *
     * @param scripts scripts data
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    private fun evalKotlin(scripts: Iterator<ScriptEval>) {
        // Do not continues load when scripts already not has next.
        if (scripts.hasNext()) {
            // Get next script and process it.
            scripts.next().let {
                evalKotlin(it.codes, *it.defaultImports) {
                    // Continues to processes until scripts has no next present.
                    evalKotlin(scripts)
                }
            }
        }
    }

    /**
     * Compile and evaluate the kotlin script.
     *
     * Use callback let script processes ordered, callback will be calls after a script runs done.
     *
     * @param source script source code
     * @param defaultImports script that forcefully import to this script
     * @param resultCallback callback that let scripts be processes ordered
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    private fun evalKotlin(
        source: String,
        vararg defaultImports: String,
        resultCallback: () -> Unit
    ): ResultWithDiagnostics<EvaluationResult> {
        // Import scripts to this script.
        val content = ScriptExport.import(this.exportedScript, source, *defaultImports)

        // The 'eval' in host will compile and evaluate the script.
        val result = host.eval(
            StringScriptSource(content),
            // Create compilation configuration that dependencies whole java classpath.
            // Dependencies whole classpath is necessary, otherwise kotlin script may not be executes anything,
            // like unable to load class 'java.lang.Object' or ETC.
            createJvmCompilationConfigurationFromTemplate<ConiumScript> {
                jvm {
                    dependenciesFromCurrentContext(wholeClasspath = true)
                }
            },
            // No evaluation configuration, script contexts has maintained by conium.
            null
        )

        // Processes export and calls callback to load next script.
        when (result) {
            is ResultWithDiagnostics.Success -> {
                // When result is 'Success', then 'returnValue' must not be null.
                result.value
                    .returnValue
                    .let { returnValue ->
                        // When the 'returnValue' is value, then it may be 'ScriptExport'.
                        (returnValue as? ResultValue.Value)
                            ?.let(ResultValue.Value::value)
                            ?.let { value ->
                                // When value type is 'ScriptExport', then export it to conium contexts.
                                (value as? ScriptExport)?.let {
                                    // The 'ofCode' only allow types import can be import to scripts.
                                    this.exportedScript[it.name] = it.ofCode(content)
                                }
                            }

                        // Calls callback to processes next script.
                        resultCallback()
                    }
            }
            // Not succeed, ignored result of this script, directly load next.
            is ResultWithDiagnostics.Failure -> resultCallback()
        }

        println(result)

        return result
    }
}
