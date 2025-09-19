package com.github.cao.awa.conium.molang.tree.program.constant.bool

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.constant.MolangConstant
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangBoolean(parent: StructuringAst): MolangConstant<Boolean>(parent) {
    override fun generateStructure(json: JSONObject) {
        super.generateStructure(json)
        json["statement_type"] = "constant_boolean"
    }
}