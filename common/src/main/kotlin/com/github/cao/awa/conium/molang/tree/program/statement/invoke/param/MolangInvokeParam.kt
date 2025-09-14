package com.github.cao.awa.conium.molang.tree.program.statement.invoke.param

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangInvokeParam(parent: StructuringAst) : StructuringAst(parent) {
    var param: MolangReturnableStatement? = null

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "invoke_param"

        val theParam = JSONObject()
        this.param!!.generateStructure(theParam)
        json["param"] = theParam
    }

    override fun preprocess() {

    }

    override fun postprocess() {

    }

    override fun consequence() {

    }
}