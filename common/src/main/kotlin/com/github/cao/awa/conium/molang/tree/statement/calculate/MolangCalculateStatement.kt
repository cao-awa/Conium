package com.github.cao.awa.conium.molang.tree.statement.calculate

import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.conium.molang.tree.statement.calculate.sign.MolangCalculateSymbol
import com.github.cao.awa.conium.molang.tree.statement.returnable.MolangReturnableStatement
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import java.util.*

class MolangCalculateStatement(parent: StructuringAst) : MolangReturnableStatement(parent) {
    private var left: MolangReturnableStatement? = null
    private var symbol: MolangCalculateSymbol? = null
    private var right: MolangReturnableStatement? = null
    private var rights: LinkedList<MolangCalculateStatement> = CollectionFactor.linkedList()
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
        return right
    }

    fun right(right: MolangReturnableStatement): MolangCalculateStatement {
        this.right = right
        return this
    }

    fun rights(): LinkedList<MolangCalculateStatement> {
        return rights
    }

    fun rights(rights: LinkedList<MolangCalculateStatement>): MolangCalculateStatement {
        this.rights = rights
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
        return rightWithParen
    }

    fun rightWithParen(rightWithParen: Boolean): MolangCalculateStatement {
        this.rightWithParen = rightWithParen
        return this
    }

    fun totalWithParen(): Boolean {
        return totalWithParen
    }

    fun totalWithParen(totalWithParen: Boolean): MolangCalculateStatement {
        this.totalWithParen = totalWithParen
        return this
    }

    fun doNotGroup(): Boolean {
        return doNotGroup
    }

    fun doNotGroup(doNotGroup: Boolean): MolangCalculateStatement {
        this.doNotGroup = doNotGroup
        return this
    }

    override fun generateStructure(json: JSONObject) {
        if (this.left == null || this.right == null || this.symbol == null) {
            throw IllegalStateException("Calculate statement is not complete")
        }

        json["statement_type"] = "calculate"

        val left = JSONObject()
        this.left!!.generateStructure(left)
        json.put("left", left)

        json.put("symbol", this.symbol!!.symbol)

        val rights = JSONArray()
        rights.add(this.right)

        for (right in this.rights) {
            val theRight = JSONObject()
            right.generateStructure(theRight)
            rights.add(theRight)
        }
        json.put("rights", rights)
    }

    override fun print(indent: String?) {
    }

    override fun preprocess() {
        if (this.left == null || this.right == null || this.symbol == null) {
            throw IllegalStateException("Calculate statement is not complete")
        }

        this.left!!.preprocess()

        this.right!!.preprocess()

        for (typescriptCalculate in this.rights) {
            typescriptCalculate.preprocess()
        }
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }
}
