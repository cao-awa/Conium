package com.github.cao.awa.conium.molang.tree.statement.ret

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

class MolangReturnStatement(parent: StructuringAst): MolangStatement(parent) {
    private var returnableStatement: MolangReturnableStatement? = null

    fun statement(): MolangReturnableStatement? = this.returnableStatement

    fun statement(statement: MolangReturnableStatement) {
        this.returnableStatement = statement
    }

    override fun generateStructure(json: JSONObject) {
        if (this.returnableStatement == null) {
            throw IllegalStateException("Return statement must have a returnable statement")
        }

        json["statement_type"] = "return"

        val theStatement = JSONObject()
        this.returnableStatement!!.generateStructure(theStatement)

        json["statement"] = theStatement
    }

    override fun preprocess() {

    }

    override fun postprocess() {

    }

    override fun consequence() {

    }

}