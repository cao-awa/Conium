package com.github.cao.awa.conium.script.translate

import com.github.cao.awa.conium.script.translate.kts.file.ConiumTypescriptFileTranslator
import com.github.cao.awa.conium.script.translate.kts.file.obj.anonymous.ConiumTypescriptAnonymousObjectTranslator
import com.github.cao.awa.conium.script.translate.kts.file.statement.importing.ConiumTypescriptImportTranslator
import com.github.cao.awa.conium.script.translate.kts.file.statement.variable.ConiumTypescriptDefineVariableTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

abstract class ConiumScriptTranslator<T : StructuringAst> : StructuringTranslator<T>() {
    companion object {
        fun postRegister() {
            registerKotlinScript(
                "conium",
                TypescriptTranslateElement.FILE,
                ConiumTypescriptFileTranslator()
            )

            // Most of the cases, generic provider is great,
            // but generic doesn't translate anonymous object, conium will impls it.
            registerKotlinScript(
                "conium",
                TypescriptTranslateElement.ANONYMOUS_OBJECT,
                ConiumTypescriptAnonymousObjectTranslator()
            )

            // The generic doesn't translate define variable correctly when pattern is:
            //   var xxx = callback(() => {
            //       something(xxx)
            //   });
            //
            // where the callback returns a result.
            //
            // Conium will translate it to:
            //   var xxx = Receptacle<Any?>(null)
            //   xxx.set(callback {
            //       something(xxx)
            //   })
            //
            // the arg type of something will impls as Receptacle<*> at conium kotlin library (when matched to present conium kotlin API).
            registerKotlinScript(
                "conium",
                TypescriptTranslateElement.DEFINE_VARIABLE,
                ConiumTypescriptDefineVariableTranslator()
            )

            registerKotlinScript(
                "conium",
                TypescriptTranslateElement.IMPORT,
                ConiumTypescriptImportTranslator()
            )
        }
    }

    override fun target(): LanguageTranslateTarget = LanguageTranslateTarget.KOTLIN_SCRIPT
}
