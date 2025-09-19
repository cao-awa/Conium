package com.github.cao.awa.conium.molang.tree.program.constant.number

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.constant.MolangConstant
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import java.math.BigDecimal

class MolangNumber(parent: StructuringAst): MolangConstant<BigDecimal>(parent) {
    override fun generateStructure(json: JSONObject) {
        super.generateStructure(json)
        json["statement_type"] = "constant_number"
    }
}