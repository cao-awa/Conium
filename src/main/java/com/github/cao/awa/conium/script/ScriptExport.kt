package com.github.cao.awa.conium.script

import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.script.field.SharedField
import com.github.cao.awa.conium.script.field.SharedFieldByJvm
import com.github.cao.awa.conium.script.field.SharedFieldHandler
import com.github.cao.awa.conium.script.kts.clearDuplicateImports
import com.github.cao.awa.conium.script.kts.filterImports
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

class ScriptExport(
    val name: String,
    private val sharedFields: (fieldHandler: SharedFieldHandler) -> Unit = { },
    private val imports: Set<String> = CollectionFactor.hashSet()
) {
    companion object {
        var exported: Map<String, ScriptExport>? = null

        fun import(exported: Map<String, ScriptExport>, code: String, vararg defaultImports: String): String {
            this.exported = exported

            val builder = StringBuilder()

            val importing: MutableSet<String> = CollectionFactor.hashSet()

            importing.addAll(defaultImports)

            code.lines().find {
                it.startsWith("//") && it.contains("IMPORT") && it.contains(":")
            }?.also {
                importing.addAll(it.substring(it.indexOf(":") + 1).split(",").toSet().map(String::trim))
            }

            for (sourceName in importing) {
                exported[sourceName]?.let {
                    for (import in it.imports) {
                        builder.append(import)
                        builder.append("\n")
                    }

                    val sharedFields = SharedFieldHandler()

                    it.sharedFields(sharedFields)

                    sharedFields(it)

                    for (fieldEntry in it.fields) {
                        val field = fieldEntry.value
                        builder.append(field.toStatement(sourceName))
                    }
                }
            }

            builder.append(code)

            return clearDuplicateImports(builder.toString())
        }

        @JvmStatic
        fun <T> accessExportedField(instance: Any, name: String, fieldName: String): T? {
            val result = this.exported?.get(name)?.fields?.get(fieldName)

            result ?: return null

            if (result is SharedFieldByJvm<*>) {
                return Manipulate.cast((result.value as ParameterSelective1<*, Any>).arise(instance))
            }

            return null
        }
    }

    var fields: Map<String, SharedField> = CollectionFactor.hashMap()

    fun ofCode(code: String): ScriptExport = ScriptExport(
        this.name,
        this.sharedFields,
        filterImports(code)
    )
}
