package com.github.cao.awa.conium.molang.translator.element.program.constant

import com.github.cao.awa.conium.molang.tree.program.constant.MolangConstant
import com.github.cao.awa.conium.molang.tree.program.constant.string.MolangString
import com.github.cao.awa.translator.structuring.translate.base.StructuringElementTranslator

interface MolangConstantElementTranslator<T: MolangConstant<*>>: StructuringElementTranslator<T> {
    fun translateConstant(builder: StringBuilder, ast: MolangConstant<*>) {
        builder.append(ast.literal())
    }
}