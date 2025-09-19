package com.github.cao.awa.conium.molang.tree.program.constant.nulls

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.constant.MolangConstant
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangNull(parent: StructuringAst): MolangConstant<Any>(parent) {
    override fun generateStructure(json: JSONObject) {
        super.generateStructure(json)
        json["statement_type"] = "constant_null"
        json.remove("value")
    }
}