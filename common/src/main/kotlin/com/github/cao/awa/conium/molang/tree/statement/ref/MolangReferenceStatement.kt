package com.github.cao.awa.conium.molang.tree.statement.ref

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangReferenceStatement(parent: StructuringAst) : MolangReturnableStatement(parent) {
    private var name: String? = null

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "reference"
        json["reference"] = this.name
    }

    override fun preprocess() {
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }

    fun name(): String? {
        return this.name
    }

    fun name(name: String): MolangReferenceStatement {
        this.name = name
        return this
    }
}
