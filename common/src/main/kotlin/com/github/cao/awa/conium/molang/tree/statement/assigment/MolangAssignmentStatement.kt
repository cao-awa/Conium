package com.github.cao.awa.conium.molang.tree.statement.assigment

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.invoke.param.MolangInvokeParam
import com.github.cao.awa.conium.molang.tree.statement.invoke.param.MolangInvokeParams
import com.github.cao.awa.conium.molang.tree.statement.ref.MolangReferenceStatement
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangAssignmentStatement(parent: StructuringAst): MolangReturnableStatement(parent) {
    private var target: MolangReferenceStatement? = null
    private var value: MolangReturnableStatement? = null

    fun target(): MolangReferenceStatement? = this.target

    fun value(): MolangReturnableStatement? = this.value

    fun target(target: MolangReferenceStatement): MolangAssignmentStatement {
        this.target = target
        return this
    }

    fun value(value: MolangReturnableStatement): MolangAssignmentStatement {
        this.value = value
        return this
    }

    override fun generateStructure(json: JSONObject) {
        if (this.target == null || this.value == null) {
            throw IllegalStateException("Assignment statement must have a target and a value")
        }

        json["statement_type"] = "assignment"

        json["target"] = this.target!!.name()

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