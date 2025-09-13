package com.github.cao.awa.conium.molang.translator.program

import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.molang.translator.element.program.MolangProgramElementTranslator
import com.github.cao.awa.conium.molang.tree.MolangProgram
import java.lang.StringBuilder

class MolangKotlinScriptProgramTranslator: MolangKotlinScriptTranslator<MolangProgram>(), MolangProgramElementTranslator {
    override fun translate(builder: StringBuilder, ast: MolangProgram) {
        translateEach(this)
    }
}