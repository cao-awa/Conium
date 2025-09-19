package com.github.cao.awa.conium.molang.tree.program.constant

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

abstract class MolangConstant<T>(parent: StructuringAst): MolangReturnableStatement(parent) {
    var value: T? = null

    fun literal(): String {
        return "${this.value}"
    }

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "constant"
        json["value"] = this.value
    }
}