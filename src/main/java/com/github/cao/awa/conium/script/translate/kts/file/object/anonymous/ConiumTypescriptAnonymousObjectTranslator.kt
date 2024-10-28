package com.github.cao.awa.conium.script.translate.kts.file.`object`.anonymous

import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.language.translator.builtin.typescript.translate.base.file.`object`.anonymous.TypescriptAnonymousObjectTranslator
import com.github.cao.awa.language.translator.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.language.translator.builtin.typescript.tree.`object`.anonymous.TypescriptAnonymousObject

class ConiumTypescriptAnonymousObjectTranslator : ConiumScriptTranslator<TypescriptAnonymousObject>(),
    TypescriptAnonymousObjectTranslator {
    override fun translate(builder: StringBuilder, ast: TypescriptAnonymousObject) {
        builder.append("BedrockScriptAnonymousObjectMap()")
        ast.params().values().forEach { (key, value) ->
            builder.append(".add(\"$key\",")
            postTranslate(TypescriptTranslateElement.STATEMENT, value)
            builder.append(")")
        }
    }
}
