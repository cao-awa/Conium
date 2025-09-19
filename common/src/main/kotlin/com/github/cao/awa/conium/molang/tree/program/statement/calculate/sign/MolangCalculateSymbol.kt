package com.github.cao.awa.conium.molang.tree.program.statement.calculate.sign

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangCalculateSymbol(val sign: String): StructuringAst(null) {
    companion object {
        val MORE_THAN: MolangCalculateSymbol = MolangCalculateSymbol(">")
        val LESS_THAN: MolangCalculateSymbol = MolangCalculateSymbol("<")
        val EQUALS: MolangCalculateSymbol = MolangCalculateSymbol("==")
        val NOT: MolangCalculateSymbol = MolangCalculateSymbol("!")
        val PLUS: MolangCalculateSymbol = MolangCalculateSymbol("+")
        val MINUS: MolangCalculateSymbol = MolangCalculateSymbol("-")
        val MULTIPLY: MolangCalculateSymbol = MolangCalculateSymbol("*")
        val DIVIDE: MolangCalculateSymbol = MolangCalculateSymbol("/")
    }

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "calculate_symbol"

        json["symbol"] = this.sign
    }

    override fun preprocess() {

    }

    override fun postprocess() {

    }

    override fun consequence() {

    }
}