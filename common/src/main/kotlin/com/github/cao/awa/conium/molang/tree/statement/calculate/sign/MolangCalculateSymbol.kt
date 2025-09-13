package com.github.cao.awa.conium.molang.tree.statement.calculate.sign

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangCalculateSymbol(val symbol: String): StructuringAst(null) {
    companion object {
        val PLUS: MolangCalculateSymbol = MolangCalculateSymbol("+")
        val MINUS: MolangCalculateSymbol = MolangCalculateSymbol("-")
        val MULTIPLY: MolangCalculateSymbol = MolangCalculateSymbol("*")
        val DIVIDE: MolangCalculateSymbol = MolangCalculateSymbol("/")
    }

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "calculate_symbol"

        json["symbol"] = this.symbol
    }

    override fun preprocess() {

    }

    override fun postprocess() {

    }

    override fun consequence() {

    }
}