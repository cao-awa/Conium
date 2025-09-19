package com.github.cao.awa.conium.molang.tree.program.statement.calculate

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.program.statement.calculate.sign.MolangCalculateSymbol
import com.github.cao.awa.conium.molang.tree.program.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import java.util.*

class MolangCalculateStatement(parent: StructuringAst) : MolangReturnableStatement(parent) {
    private var left: MolangReturnableStatement? = null
    private var symbol: MolangCalculateSymbol? = null
    private var right: MolangReturnableStatement? = null
    private var extraRights: LinkedList<MolangReturnableStatement> = CollectionFactor.linkedList()
    private var leftWithParen = false
    private var rightWithParen = false
    private var totalWithParen = false
    private var doNotGroup = false

    fun left(): MolangReturnableStatement? {
        return this.left
    }

    fun left(left: MolangReturnableStatement?): MolangCalculateStatement {
        this.left = left
        return this
    }

    fun symbol(): MolangCalculateSymbol? {
        return this.symbol
    }

    fun symbol(symbol: MolangCalculateSymbol?): MolangCalculateStatement {
        this.symbol = symbol
        return this
    }

    fun right(): MolangReturnableStatement? {
        return this.right
    }

    fun right(right: MolangReturnableStatement): MolangCalculateStatement {
        this.right = right
        return this
    }

    fun extraRights(): LinkedList<MolangReturnableStatement> {
        return this.extraRights
    }

    fun extraRights(rights: LinkedList<MolangReturnableStatement>): MolangReturnableStatement {
        this.extraRights = rights
        return this
    }

    fun leftWithParen(): Boolean {
        return leftWithParen
    }

    fun leftWithParen(leftWithParen: Boolean): MolangCalculateStatement {
        this.leftWithParen = leftWithParen
        return this
    }

    fun rightWithParen(): Boolean {
        return this.rightWithParen
    }

    fun rightWithParen(rightWithParen: Boolean): MolangCalculateStatement {
        this.rightWithParen = rightWithParen
        return this
    }

    fun totalWithParen(): Boolean {
        return this.totalWithParen
    }

    fun totalWithParen(totalWithParen: Boolean): MolangCalculateStatement {
        this.totalWithParen = totalWithParen
        return this
    }

    fun doNotGroup(): Boolean {
        return this.doNotGroup
    }

    fun doNotGroup(doNotGroup: Boolean): MolangCalculateStatement {
        this.doNotGroup = doNotGroup
        return this
    }

    fun noticeOperator(): String {
        return if (this.symbol == null) "missing" else this.symbol!!.sign
    }

    override fun generateStructure(json: JSONObject) {
//        if (this.right == null && this.extraRights.isEmpty()) {
//            throw IllegalStateException("Calculate statement is not complete: 'right', operator: '${noticeOperator()}'")
//        }
//
//        if (this.symbol == null) {
//            throw IllegalStateException("Calculate missing the operator")
//        }

        json["statement_type"] = "calculate"

        json["left_with_paren"] = this.leftWithParen
        json["right_with_paren"] = this.rightWithParen
        json["total_with_paren"] = this.totalWithParen
        json["do_not_group"] = this.doNotGroup

        if (this.left != null) {
            val theLeft = JSONObject()
            this.left!!.generateStructure(theLeft)
            json.put("left", theLeft)
        }

        if (this.symbol != null) {
            val theSymbol = JSONObject()
            this.symbol!!.generateStructure(theSymbol)
            json.put("symbol", theSymbol)
        }

        if (this.right != null) {
            val theRight = JSONObject()
            this.right!!.generateStructure(theRight)
            json["right"] = theRight
        }

        if (this.extraRights.isNotEmpty()) {
            val rights = JSONArray()

            for (right in this.extraRights) {
                val theExtraRight = JSONObject()
                right.generateStructure(theExtraRight)
                rights.add(theExtraRight)
            }
            json.put("extra_rights", rights)
        }
    }

    override fun preprocess() {
        if (this.right == null) {
            throw IllegalStateException("Calculate statement is not complete: 'right', operator: '${noticeOperator()}'")
        }

        if (this.symbol == null) {
            throw IllegalStateException("Calculate missing the operator")
        }

        this.left?.preprocess()

        this.right!!.preprocess()

        for (typescriptCalculate in this.extraRights) {
            typescriptCalculate.preprocess()
        }
    }
}
