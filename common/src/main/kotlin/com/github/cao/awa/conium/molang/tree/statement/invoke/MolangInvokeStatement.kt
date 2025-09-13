package com.github.cao.awa.conium.molang.tree.statement.invoke

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.invoke.param.MolangInvokeParam
import com.github.cao.awa.conium.molang.tree.statement.invoke.param.MolangInvokeParams
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangInvokeStatement(parent: StructuringAst): MolangReturnableStatement(parent) {
    private val params: MutableList<MolangInvokeParam> = mutableListOf()

    fun addParam(param: MolangInvokeParam) {
        this.params.add(param)
    }

    fun addParams(params: MolangInvokeParams) {
        this.params.addAll(params.params)
    }

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "invoke"

        val params = JSONArray()
        for (param in this.params) {
            val theParam = JSONObject()
            param.generateStructure(theParam)
            params.add(theParam)
        }

        json["params"] = params
    }

    override fun preprocess() {
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }
}