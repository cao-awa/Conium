package com.github.cao.awa.conium.molang.tree.program.statement

import com.alibaba.fastjson2.JSONObject
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst

abstract class MolangStatement(parent: StructuringAst): StructuringAst(parent) {
    override fun preprocess() {
    }

    override fun postprocess() {
    }

    override fun consequence() {
    }
}