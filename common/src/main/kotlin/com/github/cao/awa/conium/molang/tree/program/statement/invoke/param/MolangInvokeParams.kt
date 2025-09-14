package com.github.cao.awa.conium.molang.tree.program.statement.invoke.param

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import kotlin.collections.set

class MolangInvokeParams(parent: StructuringAst): StructuringAst(parent) {
    val params: MutableList<MolangInvokeParam> = mutableListOf()

    fun addParam(param: MolangInvokeParam) {
        this.params.add(param)
    }

    fun addParams(params: MolangInvokeParams) {
        this.params.addAll(params.params)
    }

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "invoke_param"

        json["params"] = JSONArray().also { params ->
            for (param in this.params) {
                val theParam = JSONObject()
                param.generateStructure(theParam)
                params.add(theParam)
            }
        }
    }

    override fun preprocess() {
        TODO("Not yet implemented")
    }

    override fun postprocess() {
        TODO("Not yet implemented")
    }

    override fun consequence() {
        TODO("Not yet implemented")
    }
}