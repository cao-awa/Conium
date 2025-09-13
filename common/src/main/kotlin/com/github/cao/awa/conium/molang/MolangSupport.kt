package com.github.cao.awa.conium.molang

import com.github.cao.awa.conium.molang.antlr.MolangLexer
import com.github.cao.awa.conium.molang.antlr.MolangParser
import com.github.cao.awa.conium.molang.query.MolangQuery
import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.tree.MolangProgram
import com.github.cao.awa.conium.molang.visitor.LanguageMolangVisitor
import com.github.cao.awa.translator.structuring.builtin.typescript.antlr.TypescriptLexer
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.TokenStream

val query: MolangQuery = MolangQuery()

class MolangSupport {
    companion object {
        fun test() {
            MolangKotlinScriptTranslator.postTranslate()
            readMolang(
                """
        fun();
        var.xxx = 123;
    """.trimIndent()
            ).also { molangProgram: MolangProgram ->
                println(molangProgram.statements)
            }
        }
    }
}

fun main() {
    MolangSupport.test()
}

private fun readMolang(content: String): MolangProgram {
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
            e: RecognitionException?
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