package com.github.cao.awa.conium.molang.translator.program.constant.bool

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.constant.bool.MolangBooleanElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.constant.string.MolangStringElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.constant.bool.MolangBoolean
import com.github.cao.awa.conium.molang.tree.program.constant.string.MolangString
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import java.lang.StringBuilder

class MolangKotlinScriptBooleanTranslator: MolangKotlinScriptTranslator<MolangBoolean>(), MolangBooleanElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangBoolean) {
        translateConstant(builder, ast)
    }
}