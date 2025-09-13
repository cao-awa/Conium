package com.github.cao.awa.conium.molang.tree.statement.invoke.param

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangInvokeParam(parent: StructuringAst): StructuringAst(parent) {
    var value: String = ""

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "invoke_param"

        json["param"] = this.value
    }

    override fun preprocess() {

    }

    override fun postprocess() {

    }

    override fun consequence() {

    }
}