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
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.*
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.antlr.v4.runtime.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvm.baseClassLoader
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

class ConiumScriptManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    SinglePreparationResourceReloader<MutableMap<Identifier, Resource>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumScriptManager")
        private val DATA_TYPE = RegistryKeys.getPath(ConiumRegistryKeys.SCRIPTS)
        private val defaultCommons = IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.commons.kts"))
        private val defaultBedrockScriptInit =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.script.init.kts"))
        private val defaultBedrockCommons =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.commons.kts"))
    }

    private val exportedScript = CollectionFactor.hashMap<String, ScriptExport>()
    private val host = BasicJvmScriptingHost()

    override fun prepare(manager: ResourceManager, profiler: Profiler): MutableMap<Identifier, Resource> {
        val scripts = CollectionFactor.hashMap<Identifier, Resource>()

        load(manager, scripts)

        return scripts
    }

    fun load(
        manager: ResourceManager,
        results: MutableMap<Identifier, Resource>
    ) {
        val kotlinFinder = ResourceFinder(DATA_TYPE, ".kts")
        val javascriptFinder = ResourceFinder(DATA_TYPE, ".js")
        val typescriptFinder = ResourceFinder(DATA_TYPE, ".ts")

        kotlinFinder.findResources(manager).entries.iterator().forEach {
            results[it.key] = it.value
        }

        javascriptFinder.findResources(manager).entries.iterator().forEach {
            results[it.key] = it.value
        }

        typescriptFinder.findResources(manager).entries.iterator().forEach {
            results[it.key] = it.value
        }
    }

    override fun apply(prepared: MutableMap<Identifier, Resource>, manager: ResourceManager, profiler: Profiler) {
        ConiumEvent.resetForever()

        val scripts = CollectionFactor.linkedList<ScriptEval>()

        scripts.add(ScriptEval(defaultCommons))

        scripts.add(ScriptEval(defaultBedrockCommons, "ConiumCommons"))

        scripts.add(ScriptEval(defaultBedrockScriptInit, "ConiumCommons", "ConiumBedrockCommons"))

        for (script in prepared) {
            val resource = script.value
            val identifier = script.key

            val content = resource.reader.readText()

            identifier.path.also { path ->
                if (path.endsWith(".kts")) {
                    scripts.add(ScriptEval(content, "ConiumCommons"))
                } else if (path.endsWith(".ts")) {
                    if (Conium.allowBedrock) {
                        scripts.add(ScriptEval(translateBedrock(content), "ConiumCommons", "ConiumBedrockCommons"))
                    } else {
                        LOGGER.warn("Conium are disabled bedrock script, ignored '$identifier'")
                    }
                }
            }
        }

        evalKotlin(scripts)
    }

    private fun buildBedrockRequest(script: String): String {
        return """
            BedrockEventContext.post(this) {
                $script
            }
            BedrockEventContext.clearPost()
        """.trimIndent()
    }

    private fun readTypescript(content: String): TypescriptFile {
        val lexer = TypescriptLexer(CharStreams.fromString(content))
        val tokens: TokenStream = CommonTokenStream(lexer)
        val parser = TypescriptParser(tokens)
        val programContext = parser.program()
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

        val visitor = LanguageTypescriptVisitor()
        return visitor.visitProgram(programContext)
    }

    private fun translateBedrock(source: String): String {
        val tsFile = readTypescript(source)

        tsFile.prepares()

        val translated = LanguageTranslator.translate(
            "conium",
            TranslateTarget.KOTLIN_SCRIPT,
            TypescriptTranslateElement.FILE,
            tsFile
        )

        println(translated)

        println("----------")

        return buildBedrockRequest(translated)
    }

    private fun evalKotlin(scripts: List<ScriptEval>) {
        evalKotlin(scripts.iterator())
    }

    private fun evalKotlin(scripts: Iterator<ScriptEval>) {
        if (scripts.hasNext()) {
            val next = scripts.next()

            evalKotlin(next.codes, *next.defaultImports) {
                evalKotlin(scripts)
            }
        }
    }

    private fun evalKotlin(source: String, vararg defaultImports: String, resultCallback: () -> Unit): ResultWithDiagnostics<EvaluationResult> {
        val content = ScriptExport.import(this.exportedScript, source, *defaultImports)

        println("Eval: \n${content}")

        val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<ConiumScript> {
            jvm {
                dependenciesFromCurrentContext(wholeClasspath = true)
            }
        }

        val result = host.eval(
            StringScriptSource(content),
            compilationConfiguration,
            null
        )

        println(result)

        if (result is ResultWithDiagnostics.Success) {
            result.value.returnValue.let { returnValue ->
                if (returnValue is ResultValue.Value) {
                    returnValue.value?.let {
                        (it as? ScriptExport)?.let { export ->
                            this.exportedScript[export.name] = export.ofCode(content)
                        }
                    }
                }
            }
            resultCallback()
        }

        return result
    }
}
