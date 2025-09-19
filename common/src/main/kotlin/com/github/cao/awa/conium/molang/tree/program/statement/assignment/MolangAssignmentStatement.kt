package com.github.cao.awa.conium.molang.tree.program.statement.assignment

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.reference.MolangReference
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangAssignmentStatement(parent: StructuringAst): MolangReturnableStatement(parent) {
    var target: MolangReference? = null
    var value: MolangReturnableStatement? = null

    override fun generateStructure(json: JSONObject) {
        if (this.target == null || this.value == null) {
            throw IllegalStateException("Assignment statement must have a target and a value")
        }

        json["statement_type"] = "assignment"

        val theTarget = JSONObject()
        this.target!!.generateStructure(theTarget)
        json["target"] = theTarget

        val theValue = JSONObject()
        this.value!!.generateStructure(theValue)
        json["value"] = theValue
    }

    override fun preprocess() {
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }
}