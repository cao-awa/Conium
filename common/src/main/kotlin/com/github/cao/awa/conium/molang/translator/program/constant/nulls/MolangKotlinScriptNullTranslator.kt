package com.github.cao.awa.conium.molang.translator.program.constant.nulls

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.translator.element.program.constant.bool.MolangBooleanElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.constant.nulls.MolangNullElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.constant.string.MolangStringElementTranslator
import com.github.cao.awa.conium.molang.translator.element.program.statement.invoke.MolangInvokeStatementElementTranslator
import com.github.cao.awa.conium.molang.tree.program.constant.bool.MolangBoolean
import com.github.cao.awa.conium.molang.tree.program.constant.nulls.MolangNull
import com.github.cao.awa.conium.molang.tree.program.constant.string.MolangString
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.MolangInvokeStatement
import java.lang.StringBuilder

class MolangKotlinScriptNullTranslator: MolangKotlinScriptTranslator<MolangNull>(), MolangNullElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangNull) {
        translateConstant(builder, ast)
    }
}