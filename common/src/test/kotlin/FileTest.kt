import com.github.cao.awa.conium.Conium.Companion.VERSION
import com.github.cao.awa.conium.Conium.Companion.debug
import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.sinuatum.util.io.IOUtil
import com.github.cao.awa.translator.structuring.builtin.typescript.antlr.TypescriptLexer
import com.github.cao.awa.translator.structuring.builtin.typescript.antlr.TypescriptParser
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.kts.TypescriptKotlinScriptTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.TypescriptFile
import com.github.cao.awa.translator.structuring.builtin.typescript.visitor.LanguageTypescriptVisitor
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.element.TranslateElementData
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import org.antlr.v4.runtime.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

private val LOGGER: Logger = LogManager.getLogger("ConiumTest")

fun main() {
    TypescriptKotlinScriptTranslator.postRegister()

    LOGGER.info("Loading conium '{}' structuring translator providers for [typescript]", VERSION)
    ConiumScriptTranslator.postRegister()

    StructuringTranslator.getTranslators("conium").let { translators ->
        LOGGER.info(
            "Loaded {} translators by conium structuring translator providers({})",
            translators.size,
            VERSION
        )
        debug(
            "Loaded {} translators by conium structuring translator providers({}): {}",
            translators::size,
            { VERSION },
            { collectTranslators(translators) },
            LOGGER::info
        )
    }

    translateBedrockTypescript(
        """
            import { world, system, DimensionLocation } from "@minecraft/server";

            function countdown(targetLocation: DimensionLocation) {
                const players = world.getPlayers();

                players[0].onScreenDisplay.setTitle("Get ready!", {
                    stayDuration: 220,
                    fadeInDuration: 2,
                    fadeOutDuration: 4,
                    subtitle: "10",
                });

                let countdown = 10;

                const intervalId = system.runInterval(() => {
                    countdown--;
                    players[0].onScreenDisplay.updateSubtitle(countdown.toString());

                    if (countdown == 0) {
                        system.clearRun(intervalId);
                    }
                }, 20);
            }
        """.trimIndent()
    ).also { translated: String ->
        IOUtil.write(
            File("debug/conium-debug/www.kts").also {
                it.parentFile.mkdirs()
                println(it.absolutePath)
            }.also(File::createNewFile).writer(),
            translated
        )
    }
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

private fun collectTranslators(translators: Map<LanguageTranslateTarget, Map<TranslateElementData<*>, StructuringTranslator<*>>>): Map<LanguageTranslateTarget, Collection<Class<*>>> {
    val result: MutableMap<LanguageTranslateTarget, Collection<Class<*>>> = CollectionFactor.hashMap()
    translators.forEach { (target: LanguageTranslateTarget, targetTranslators: Map<TranslateElementData<*>, StructuringTranslator<*>>) ->
        result[target] = targetTranslators.keys.map { it.clazz() }
    }
    return result
}