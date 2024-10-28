package com.github.cao.awa.conium.datapack.script

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.script.kts.ConiumScript
import com.github.cao.awa.conium.script.kts.clearDuplicateImports
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
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.StringScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate
import kotlin.script.experimental.jvmhost.createJvmEvaluationConfigurationFromTemplate

class ConiumScriptManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    SinglePreparationResourceReloader<MutableMap<Identifier, Resource>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumScriptManager")
        private val DATA_TYPE = RegistryKeys.getPath(ConiumRegistryKeys.SCRIPTS)
        private val kotlinEngine: ScriptEngine =
            ScriptEngineManager(Thread.currentThread().contextClassLoader).getEngineByExtension("kts")
        private val defaultCommons = IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.commons.kts"))
        private val defaultBedrockCommons =
            IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.bedrock.commons.kts"))
        private val kotlinHost = BasicJvmScriptingHost()
        private val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<ConiumScript> {
            for (line in defaultCommons.reader().readLines()) {
                defaultImports.append(line)
            }
            isStandalone(false)
            jvm {
                dependenciesFromCurrentContext(wholeClasspath = true)
            }
        }
        private val evaluationConfiguration = createJvmEvaluationConfigurationFromTemplate<ConiumScript> {
            enableScriptsInstancesSharing()
            jvm {

            }
        }
    }

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

        for (script in prepared) {
            val resource = script.value
            val identifier = script.key

            val content = resource.reader.readText()

            identifier.path.also { path ->
                if (path.endsWith(".kts")) {
                    println(evalKotlin(defaultCommons + defaultBedrockCommons + content))
                } else if (path.endsWith(".ts")) {
                    if (Conium.allowBedrock) {
                        val tsFile = readTypescript(content)

                        tsFile.prepares()

                        val translated = LanguageTranslator.translate(
                            "conium",
                            TranslateTarget.KOTLIN_SCRIPT,
                            TypescriptTranslateElement.FILE,
                            tsFile
                        )

                        println(translated)

                        println("----------")

                        println(evalKotlin(defaultCommons + defaultBedrockCommons + buildBedrockRequest(translated)))
                    } else {
                        LOGGER.warn("Conium are disabled bedrock script, ignored '$identifier'")
                    }
                }
            }
        }

        BedrockEventContext.newSystem()

        ConiumEventContextBuilder.request(
            ConiumEventType.SERVER_TICK,
            ConiumEventArgTypes.SERVER
        ).arise { _, server ->
            BedrockEventContext.system.tick(server)

            true
        }
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

    private fun evalKotlin(source: String): ResultWithDiagnostics<EvaluationResult> {
        val content = clearDuplicateImports(source)

        println("Eval: \n${content}")

        val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<ConiumScript> {
            jvm {
                dependenciesFromCurrentContext(wholeClasspath = true)
            }
        }
        return BasicJvmScriptingHost().eval(
            StringScriptSource(content),
            compilationConfiguration,
            null
        )
    }
}
