package com.github.cao.awa.conium.molang.tree.program

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.MolangStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangProgram(parent: StructuringAst?): StructuringAst(parent) {
    val statements = mutableListOf<MolangStatement>()

    override fun generateStructure(json: JSONObject) {
        json["statement_type"] = "molang_program"

        val statements = JSONArray()

        for (statement in this.statements) {
            val theStatement = JSONObject()
            statement.generateStructure(theStatement)
            statements.add(theStatement)
        }

        json["statements"] = statements
    }

    override fun preprocess() {
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }
}