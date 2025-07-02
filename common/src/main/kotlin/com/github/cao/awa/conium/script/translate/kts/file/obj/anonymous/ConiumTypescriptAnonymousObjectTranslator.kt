package com.github.cao.awa.conium.script.translate.kts.file.obj.anonymous

import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.base.file.`object`.anonymous.TypescriptAnonymousObjectTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.`object`.anonymous.TypescriptAnonymousObject

class ConiumTypescriptAnonymousObjectTranslator : ConiumScriptTranslator<TypescriptAnonymousObject>(), TypescriptAnonymousObjectTranslator {
    override fun translate(builder: StringBuilder, ast: TypescriptAnonymousObject) {
        translateIdent()

        val elements = ast.params().values()

        builder.append("com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap(")

        // Fixed size.
        builder.append(elements.size)
        builder.append(")")
        builder.append(".apply {")

        translateLineWrap(this)

        pushIdent()
        pushIdent()
        var count = 0
        for (entry in elements.entries) {
            val key = entry.key
            val value = entry.value
            translateIdent()
            builder.append("set(\"")
            builder.append(key)
            builder.append("\", ")
            postTranslate(TypescriptTranslateElement.STATEMENT, value, false)
            builder.append(")")
            if (count++ != elements.size) {
                translateEnding(this)
            }
        }
        popIdent()

        translateIdent()
        popIdent()

        builder.append("}")

        translateIdent()
        popIdent()
    }
}
