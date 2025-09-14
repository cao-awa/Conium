package com.github.cao.awa.conium.molang.tree.program.statement.reference

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangReference(parent: StructuringAst) : MolangReturnableStatement(parent) {
    var name: String? = null

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
}
