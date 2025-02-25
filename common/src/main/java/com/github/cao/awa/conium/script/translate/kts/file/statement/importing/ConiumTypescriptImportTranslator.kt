package com.github.cao.awa.conium.script.translate.kts.file.statement.importing

import com.github.cao.awa.conium.bedrock.index.d.IndexD.Companion.tryImport
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.base.file.statement.importing.TypescriptImportingTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.kts.TypescriptKotlinScriptTranslator
import com.github.cao.awa.translator.structuring.builtin.typescript.tree.statement.importing.TypescriptImportStatement

class ConiumTypescriptImportTranslator : TypescriptKotlinScriptTranslator<TypescriptImportStatement>(), TypescriptImportingTranslator {
    override fun translate(builder: StringBuilder, ast: TypescriptImportStatement) {
        val from: String = ast.from().replace("\"", "")
        tryImport(from, ast.imports()) { location: String ->
            builder.append("import ")
            builder.append(location)
            translateEnding(this)
        }
    }
}
