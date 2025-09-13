package com.github.cao.awa.conium.molang.tree.constant.number

import com.github.cao.awa.conium.molang.tree.constant.MolangConstant
import com.github.cao.awa.translator.structuring.translate.tree.StructuringAst
import java.math.BigDecimal

class MolangNumber(parent: StructuringAst): MolangConstant<BigDecimal>(parent) {
}