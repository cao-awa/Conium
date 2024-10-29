package com.github.cao.awa.conium.script.translate

import com.github.cao.awa.conium.script.translate.kts.file.`object`.anonymous.ConiumTypescriptAnonymousObjectTranslator
import com.github.cao.awa.conium.script.translate.kts.file.statement.variable.ConiumTypescriptDefineVariableTranslator
import com.github.cao.awa.language.translator.builtin.typescript.translate.element.TypescriptTranslateElement
import com.github.cao.awa.language.translator.translate.LanguageTranslator
import com.github.cao.awa.language.translator.translate.lang.TranslateTarget
import com.github.cao.awa.language.translator.translate.tree.LanguageAst

abstract class ConiumScriptTranslator<T : LanguageAst?> : LanguageTranslator<T>() {
    companion object {
        fun postRegister() {
            // Most of the cases, generic provider is great,
            // but generic doesn't translating anonymous object, conium will impls it.
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
        }
    }

    override fun target(): TranslateTarget = TranslateTarget.KOTLIN_SCRIPT
}
