package com.github.cao.awa.conium.molang.tree.constant

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

abstract class MolangConstant<T>(parent: StructuringAst): MolangReturnableStatement(parent) {
    private var value: T? = null

    fun literal(): String {
        return "${this.value}"
    }

    fun value(): T? {
        return this.value
    }

    fun value(value: T?) {
        this.value = value
    }

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "constant"
        json["value"] = this.value
    }

    override fun preprocess() {

    }

    override fun postprocess() {

    }

    override fun consequence() {

    }
}