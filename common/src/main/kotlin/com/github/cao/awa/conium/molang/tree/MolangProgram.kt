package com.github.cao.awa.conium.molang.tree

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangProgram(parent: StructuringAst?): StructuringAst(parent) {
    val statements = mutableListOf<MolangStatement>()

    override fun generateStructure(json: JSONObject) {
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