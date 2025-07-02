package com.github.cao.awa.conium.script.translate.kts.file.statement.variable

import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.base.file.statement.variable.TypescriptDefineVariableTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.invoke.TypescriptInvoke
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.invoke.access.TypescriptInvokeAccessElement
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.variable.TypescriptDefineVariable

class ConiumTypescriptDefineVariableTranslator : ConiumScriptTranslator<TypescriptDefineVariable>(), TypescriptDefineVariableTranslator {
    override fun translate(builder: StringBuilder, ast: TypescriptDefineVariable) {
        if (ast.isDefine) {
            if (ast.isFinal) {
                builder.append("val ")
            } else {
                builder.append("var ")
            }
        }
        builder.append(ast.name())

        ast.assigment().let { assigment ->
            if (assigment is TypescriptInvoke) {
                assigment.invokeTarget().accesses()[0].let { access ->
                    if (access is TypescriptInvokeAccessElement) {
                        when (access.target()) {
                            "system.runInterval" -> {
                                builder.append("=IntegerReceptacle(0)")
                                translateEnding(this)
                                builder.append(ast.name())
                                builder.append(".set(")
                                postTranslate(TypescriptTranslateElement.STATEMENT, ast.assigment())
                                builder.append(")")
                                translateEnding(this)
                                return
                            }
                        }
                    }
                }
            }
        }

        if (ast.assigment() != null) {
            builder.append("=")
            postTranslate(TypescriptTranslateElement.STATEMENT, ast.assigment())
        }

        translateEnding(this)
    }
}
