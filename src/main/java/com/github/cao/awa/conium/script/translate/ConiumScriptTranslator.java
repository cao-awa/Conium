package com.github.cao.awa.conium.script.translate;

import com.github.cao.awa.conium.script.translate.kts.file.object.anonymous.ConiumTypescriptAnonymousObjectTranslator;
import com.github.cao.awa.conium.script.translate.kts.file.statement.variable.ConiumTypescriptDefineVariableTranslator;
import com.github.cao.awa.language.translator.builtin.typescript.translate.element.TypescriptTranslateElement;
import com.github.cao.awa.language.translator.translate.LanguageTranslator;
import com.github.cao.awa.language.translator.translate.lang.TranslateTarget;
import com.github.cao.awa.language.translator.translate.tree.LanguageAst;

public abstract class ConiumScriptTranslator<T extends LanguageAst> extends LanguageTranslator<T> {
    public static void postRegister() {
        registerKotlinScript("conium", TypescriptTranslateElement.ANONYMOUS_OBJECT, new ConiumTypescriptAnonymousObjectTranslator());
        registerKotlinScript("conium", TypescriptTranslateElement.DEFINE_VARIABLE, new ConiumTypescriptDefineVariableTranslator());
    }

    @Override
    public TranslateTarget target() {
        return TranslateTarget.KOTLIN_SCRIPT;
    }
}
