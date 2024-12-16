package com.github.cao.awa.conium.script.translate.kts.file

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.base.file.TypescriptFileElementTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.kts.TypescriptKotlinScriptTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.TypescriptFile
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.TypescriptStatement
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.importing.TypescriptImportStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator

class ConiumTypescriptFileTranslator : TypescriptKotlinScriptTranslator<TypescriptFile>(), TypescriptFileElementTranslator {
    override fun translate(builder: StringBuilder, ast: TypescriptFile) {
        val imports: MutableList<TypescriptImportStatement> = CollectionFactor.linkedList()
        val statements: MutableList<TypescriptStatement> = CollectionFactor.linkedList()

        for(statement: TypescriptStatement in ast.statements()) {
            if (statement is TypescriptImportStatement) {
                imports.add(statement)
            } else {
                statements.add(statement)
            }
        }

        translator(TypescriptTranslateElement.STATEMENT) { next: StructuringTranslator<TypescriptStatement> ->
            for (importStatement: TypescriptImportStatement in imports) {
                next.postTranslate(builder, importStatement, this)
            }
            builder.append("\n")

            builder.append("BedrockEventContext.post(this){")

            for (statement: TypescriptStatement in statements) {
                next.postTranslate(builder, statement, this)
            }

            builder.append("}.also{");
            translateLineWrap(this)

            builder.append("BedrockEventContext.completePost()")
            translateLineWrap(this)
            builder.append("}")
        }
    }
}
