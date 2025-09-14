package com.github.cao.awa.conium.molang.tree.program.statement.invoke

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParam
import com.github.cao.awa.conium.molang.tree.program.statement.invoke.param.MolangInvokeParams
import com.github.cao.awa.conium.molang.tree.program.statement.reference.MolangReference
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangInvokeStatement(parent: StructuringAst): MolangReturnableStatement(parent) {
    val params: MolangInvokeParams = MolangInvokeParams(parent)
    var reference: MolangReference? = null

    fun addParams(params: MolangInvokeParams) {
        this.params.addParams(params)
    }

    override fun generateStructure(json: JSONObject) {
        if (this.reference == null) {
            throw IllegalStateException("Invoke statement must have a target")
        }

        json["statement_type"] = "invoke"

        val params = JSONArray()
        for (param in this.params.params) {
            val theParam = JSONObject()
            param.generateStructure(theParam)
            params.add(theParam)
        }

        val theReference = JSONObject()
        this.reference!!.generateStructure(theReference)
        json["target"] = theReference
        json["params"] = params
    }

    override fun preprocess() {
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }
}