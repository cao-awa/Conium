package com.github.cao.awa.conium.script

import com.github.cao.awa.conium.extent.manipulate.cast
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.script.field.SharedField
import com.github.cao.awa.conium.script.field.SharedFieldByJvm
import com.github.cao.awa.conium.script.field.SharedFieldHandler
import com.github.cao.awa.conium.script.kts.clearDuplicateImports
import com.github.cao.awa.conium.script.kts.filterImports

class ScriptExport(
    val name: String,
    private val sharedFields: (fieldHandler: SharedFieldHandler) -> Unit = { },
    private val imports: Set<String> = HashSet()
) {
    companion object {
        var exported: Map<String, ScriptExport>? = null

        fun import(exported: Map<String, ScriptExport>, code: String, vararg defaultImports: String): String {
            this.exported = exported

            val builder: StringBuilder = StringBuilder()

            val importing: MutableSet<String> = HashSet()

            importing.addAll(defaultImports)

            code.lines().find {
                it.startsWith("//") && it.contains("IMPORT") && it.contains(":")
            }?.also {
                importing.addAll(it.substring(it.indexOf(":") + 1).split(",").toSet().map(String::trim))
            }

            for (sourceName: String in importing) {
                exported[sourceName]?.let {
                    for (import: String in it.imports) {
                        builder.append(import)
                        builder.append("\n")
                    }

                    val sharedFields = SharedFieldHandler()

                    it.sharedFields(sharedFields)

                    sharedFields(it)

                    for ((_, value: SharedField) in it.fields) {
                        builder.append(value.toStatement(sourceName))
                    }
                }
            }

            builder.append(code)

            return clearDuplicateImports(builder.toString())
        }

        @JvmStatic
        fun <T> accessExportedField(instance: Any, name: String, fieldName: String): T? {
            val result: SharedField? = this.exported?.get(name)?.fields?.get(fieldName)

            result ?: return null

            if (result is SharedFieldByJvm<*>) {
                return (result.value as ParameterSelective1<*, Any>).arise(instance).cast()
            }

            return null
        }
    }

    var fields: Map<String, SharedField> = HashMap()

    fun ofCode(code: String): ScriptExport = ScriptExport(
        this.name,
        this.sharedFields,
        filterImports(code)
    )
}
