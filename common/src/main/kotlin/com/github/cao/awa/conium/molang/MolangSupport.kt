package com.github.cao.awa.conium.molang

import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.JSONWriter
import com.github.cao.awa.conium.molang.antlr.MolangLexer
import com.github.cao.awa.conium.molang.antlr.MolangParser
import com.github.cao.awa.conium.molang.query.MolangQuery
import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.tree.program.MolangProgram
import com.github.cao.awa.conium.molang.visitor.LanguageMolangVisitor
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import org.antlr.v4.runtime.*

val query: MolangQuery = MolangQuery()

class MolangSupport {
    companion object {
        fun test() {
            MolangKotlinScriptTranslator.postRegister()
            readMolang(
                """
        fun(1,2,3,4,5,6);
        var.xxx = 123 * (789 - 456 * (789 - 321) / 123 + (123 - 456)) + 123;
        return var.xxx;
    """.trimIndent()
            ).also { molangProgram: MolangProgram ->
                println(molangProgram.statements)

                val structure = JSONObject()
                molangProgram.generateStructure(structure)
                println("== Structured: Molang")
                println(structure.toString())

                molangProgram.prepares()

                val translated: String = StructuringTranslator.translate(
                    // Use conium provider to processes something additional features.
                    // See the package 'com.github.cao.awa.conium.script.translate'
                    "conium",
                    // Translate to kotlin script.
                    LanguageTranslateTarget.KOTLIN_SCRIPT,
                    // Whole file to translate.
                    MolangTranslateElement.PROGRAM,
                    molangProgram
                )

                println("== Translated: Molang to Kotlin ")
                println(translated)
            }
        }
    }
}

fun main() {
    MolangSupport.test()
}

fun readMolang(content: String): MolangProgram {
    // Build lexer, parser and prepare tokens.
    val lexer = MolangLexer(CharStreams.fromString(content))
    val tokens: TokenStream = CommonTokenStream(lexer)
    val parser = MolangParser(tokens)

    // Error handles.
    parser.addErrorListener(object : BaseErrorListener() {
        override fun syntaxError(
            recognizer: Recognizer<*, *>,
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

    // Use the visitor to build molang AST after context parsed.
    return LanguageMolangVisitor().visitProgram(parser.program())
}