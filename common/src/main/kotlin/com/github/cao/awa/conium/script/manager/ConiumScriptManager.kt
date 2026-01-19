package com.github.cao.awa.conium.script.manager

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.config.ConiumConfig
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.script.ScriptExport
import com.github.cao.awa.conium.script.eval.ScriptEval
import com.github.cao.awa.conium.script.interaction.NamedInteractionScript
import com.github.cao.awa.conium.script.kts.ConiumScript
import com.github.cao.awa.sinuatum.resource.loader.ResourceLoader
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.sinuatum.util.io.IOUtil
import com.github.cao.awa.translator.structuring.builtin.typescript.antlr.TypescriptLexer
import com.github.cao.awa.translator.structuring.builtin.typescript.antlr.TypescriptParser
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.TypescriptFile
import com.github.cao.awa.translator.structuring.builtin.typescript.visitor.LanguageTypescriptVisitor
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceFinder
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.SinglePreparationResourceReloader
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.TokenStream
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import kotlin.collections.iterator
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.jvm.BasicJvmScriptEvaluator
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvmhost.JvmScriptCompiler
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

/**
 * A dedicated script manager for conium that translates and compiles scripts then evaluates.
 *
 * ConiumScriptManager is script dynamic loadable.
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
class ConiumScriptManager(var registryLookup: RegistryWrapper.WrapperLookup) :
    SinglePreparationResourceReloader<MutableMap<Identifier, Resource>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumScriptManager")
        private val DATA_TYPE: String = ConiumRegistryKeys.SCRIPT.value.path

        // Commons script here, all scripts use these scripts.
        private val defaultCommons: String = IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.commons.kts"))
        private val defaultClientCommons: String =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.client.kts"))
        private val defaultBedrockScriptInit: String =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.script.init.kts"))
        private val defaultBedrockCommons: String =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.commons.kts"))

        /**
         * The 'host' is kotlin scripting host that used to compile and evaluate the kotlin scripts.
         *
         * Don't use JSR223 API to evaluate scripts, it will cause unexpected produce env problems!
         *
         * @see JvmScriptCompiler
         * @see BasicJvmScriptEvaluator
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        private val scriptingHost: BasicJvmScriptingHost = BasicJvmScriptingHost()
    }

    private val compilationConfiguration: ScriptCompilationConfiguration =
        createJvmCompilationConfigurationFromTemplate<ConiumScript> {
            jvm {
                dependenciesFromCurrentContext(wholeClasspath = true)
            }
        }

    /**
     * The 'exportedScript' is shared script context when the script return a '[com.github.cao.awa.conium.script.ScriptExport]'
     *
     * Other scripts can use comment to import these contexts where they are marked as:
     *
     * '// IMPORT: ExportName' or '// IMPORT: ExportName, OtherExportName'
     */
    private val exportedScript: MutableMap<String, ScriptExport> = CollectionFactor.hashMap()

    /**
     * The 'exportedInteraction' is conditions or actions that referenced in data-driven.
     *
     * The data-driven framework will acquire and invoke the ParameterSelective to dynamically produce runtime variables.
     */
    private val exportedInteraction: MutableMap<String, NamedInteractionScript<*>> = CollectionFactor.hashMap()

    /**
     * Prepares the intermediate object.
     *
     * This method is called in the prepared executor in a reload.
     *
     * @param profiler the prepared profiler
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

    fun export(name: String, context: ConiumArisingEventContext<*, *>, result: (Any) -> Any) {
        this.exportedInteraction[name] = NamedInteractionScript(
            name,
            context,
            result
        )
    }

    fun acquire(name: String): ConiumArisingEventContext<*, *> {
        return this.exportedInteraction[name]!!.context
    }

    fun acquireResult(name: String): ((Any) -> Any?) {
        return this.exportedInteraction[name]!!.result
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
        // There is 3 types finder need to load.
        val kotlinFinder = ResourceFinder(DATA_TYPE, ".kts")
        val javascriptFinder = ResourceFinder(DATA_TYPE, ".js")
        val typescriptFinder = ResourceFinder(DATA_TYPE, ".ts")

        // Load kotlin scripts.
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
     * This method is called in the applied executor, or the game engine, in a reload.
     *
     * @param manager the resource manager
     * @param profiler the profiler
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

        ConiumEvent.attach()

        // Clear old exported script, scripts will export to here again in next step loading
        this.exportedScript.clear()

        // Add the script for next step ordered loading.
        CollectionFactor.linkedList<ScriptEval>().let { scripts ->
            // Load commons.
            scripts.add(ScriptEval(defaultCommons, "ConiumCommons"))

            if (Conium.isClient) {
                scripts.add(ScriptEval(defaultClientCommons, "ConiumClientCommons"))
            }

            // Bedrock common is only load when conium allows bedrock.
            if (ConiumConfig.enableBedrockScript) {
                scripts.add(ScriptEval(defaultBedrockCommons, "ConiumBedrockCommons", "ConiumCommons"))
                scripts.add(
                    ScriptEval(
                        defaultBedrockScriptInit,
                        "ConiumBedrockScriptInit",
                        "ConiumCommons",
                        "ConiumBedrockCommons"
                    )
                )
            }

            for ((identifier: Identifier, resource: Resource) in prepared) {
                // Read script source code.
                val content: String = resource.reader.readText()

                identifier.path.also { path: String ->
                    if (path.endsWith(".kts")) {
                        // Load script data after.
                        scripts.add(ScriptEval(content, path, "ConiumCommons"))
                    } else if (!ConiumConfig.enableBedrockScript) {
                        // If not TypeScript or JavaScript file, then not bedrock script, do not notice bedrock script support status.
                        if (path.endsWith(".ts") || path.endsWith(".js")) {
                            // When bedrock scripting allows is disabled, then the script won't be load.
                            LOGGER.warn("Conium are disabled bedrock script, ignored '$identifier'")
                        }
                    } else if (path.endsWith(".ts")) {
                        // Load script data after translate typescript to kotlin.
                        runCatching {
                            translateBedrockTypescript(content).also { translated: String ->
                                // Debug write.
                                if (ConiumConfig.Companion.debugs) {
                                    IOUtil.write(
                                        File("debug/conium-debug/translated/${identifier.namespace}/${identifier.path}.kts").also {
                                            it.parentFile.mkdirs()
                                            println(it.absolutePath)
                                        }.also(File::createNewFile).writer(),
                                        translated
                                    )
                                }
                            }.let { translated: String ->
                                scripts.add(
                                    ScriptEval(
                                        translated,
                                        path,
                                        "ConiumCommons",
                                        "ConiumBedrockCommons"
                                    )
                                )
                            }
                        }.exceptionOrNull()?.let {
                            LOGGER.warn("Failed to translate the script: {}", content, it)
                        }
                    } else if (path.endsWith(".js")) {
                        // JavaScript supporting is not done.
                        TODO("Javascript translator are not implemented yet.")
                    }
                }
            }

            // Scripts will compile and executions in order.
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

        // Use the visitor to build typescript AST after context parsed.
        return LanguageTypescriptVisitor().visitProgram(parser.program())
    }

    private fun translateBedrockTypescript(source: String): String {
        return readTypescript(source).let { typescriptFile: TypescriptFile ->
            // Prepares the typescript AST for next step translating.
            typescriptFile.prepares()

            // Translate typescript to the conium script (kotlin script with conium API).
            val translated: String = StructuringTranslator.translate(
                // Use conium provider to processes something additional features.
                // See the package 'com.github.cao.awa.conium.script.translate'
                "conium",
                // Translate to kotlin script.
                LanguageTranslateTarget.KOTLIN_SCRIPT,
                // Whole file to translate.
                TypescriptTranslateElement.FILE,
                typescriptFile
            )

            // Bedrock script needs post the script instance to 'BedrockEventContext',
            // because the 'world' or 'system' or others variables may uniques in different context.
            // The bedrock commons use 'get() = access(this)' to access current context,
            // when context created, it will push to the 'contexts' in 'BedrockEventContext',
            // so 'access' can get current context for this script.
            return@let translated
        }
    }

    /**
     * Post the kotlin scripts to ordering eval function.
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
     * The callback will be called after a script runs done.
     *
     * @param scripts scripts data
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    private fun evalKotlin(scripts: Iterator<ScriptEval>) {
        // Do not continue to load when scripts already not has next.
        while (scripts.hasNext()) {
            // Get the next script and process it.
            evalKotlin(scripts.next())
        }
    }

    /**
     * Compile and evaluate the kotlin script.
     *
     * Use callback let script processes ordered, callback will be calls after a script runs done.
     *
     * @param scriptEval script source code and imports
     * @param resultCallback callback that let scripts be processes ordered
     *
     * @see BasicJvmScriptingHost
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    private fun evalKotlin(
        scriptEval: ScriptEval,
        resultCallback: () -> Unit = { }
    ): ResultWithDiagnostics<EvaluationResult> {
        // Import scripts to this script.
        val content: String = ScriptExport.import(this.exportedScript, scriptEval.codes, *scriptEval.defaultImports)

        LOGGER.info(
            "Compiling script '{}'",
            scriptEval.source
        )

        Conium.debug(
            "Compiling script '{}': {}",
            scriptEval::source,
            { content },
            LOGGER::info
        )

        // The 'eval' in host will compile and evaluate the script.
        val result: ResultWithDiagnostics<EvaluationResult> = scriptingHost.eval(
            StringScriptSource(content),
            // Create compilation configuration that dependencies whole java classpath.
            // Dependencies whole classpath is necessary, otherwise the kotlin script may not be executing anything,
            // like unable to load class 'java.lang.Object' or ETC.
            this.compilationConfiguration,
            // No evaluation configuration, script contexts have maintained by conium.
            null
        )

        // Processes export and calls callback to load the next script.
        when (result) {
            is ResultWithDiagnostics.Success -> {
                // When the result is 'Success', then 'returnValue' must not be null.
                result.value
                    .returnValue
                    .let { returnValue: ResultValue ->
                        // When the 'returnValue' is value, then it may be 'ScriptExport'.
                        (returnValue as? ResultValue.Value)
                            ?.let(ResultValue.Value::value)
                            ?.let { value ->
                                // When the value type is 'ScriptExport', then export it to conium contexts.
                                (value as? ScriptExport)?.let {
                                    // The 'ofCode' only allow types import can be import to scripts.
                                    this.exportedScript[it.name] = it.ofCode(content)
                                }
                            }
                    }
                LOGGER.info(
                    "Succeed executed script '{}'",
                    scriptEval.source
                )
            }
            // Not succeed, ignored result of this script, directly load next.
            is ResultWithDiagnostics.Failure -> {
                LOGGER.error(
                    "Error running script: {}",
                    result.reports
                )
            }
        }

        // Calls callback to processes next script.
        resultCallback()

        return result
    }
}