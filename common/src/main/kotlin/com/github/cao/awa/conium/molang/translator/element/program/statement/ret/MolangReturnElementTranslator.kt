package com.github.cao.awa.conium.molang.translator.element.program.statement.ret

import com.github.cao.awa.conium.molang.translator.element.MolangTranslateElement
import com.github.cao.awa.conium.molang.tree.MolangProgram
import com.github.cao.awa.conium.molang.tree.statement.MolangStatement
import com.github.cao.awa.conium.molang.tree.statement.ret.MolangReturnStatement
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.base.StructuringElementTranslator

interface MolangReturnElementTranslator : StructuringElementTranslator<MolangReturnStatement> {

}