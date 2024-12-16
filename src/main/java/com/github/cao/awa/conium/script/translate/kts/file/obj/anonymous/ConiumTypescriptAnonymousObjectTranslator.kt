package com.github.cao.awa.conium.script.translate.kts.file.obj.anonymous

import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.base.file.`object`.anonymous.TypescriptAnonymousObjectTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.`object`.anonymous.TypescriptAnonymousObject
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.result.TypescriptResultStatement

class ConiumTypescriptAnonymousObjectTranslator : ConiumScriptTranslator<TypescriptAnonymousObject>(),
    TypescriptAnonymousObjectTranslator {
    override fun translate(builder: StringBuilder, ast: TypescriptAnonymousObject) {
        builder.append("BedrockScriptAnonymousObjectMap()")
        ast.params().values().forEach { (name: String, statement: TypescriptResultStatement) ->
            builder.append(".add(\"$name\",")
            postTranslate(TypescriptTranslateElement.STATEMENT, statement)
            builder.append(")")
        }
    }
}
