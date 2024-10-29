package com.github.cao.awa.conium.script.field

import com.github.cao.awa.conium.parameter.ParameterSelective1
import kotlin.reflect.KClass

class SharedFieldByJvm<T>(val type: KClass<*>, val name: String, val value: ParameterSelective1<T, Any>): SharedField() {
    override fun toStatement(exportName: String): String {
        val builder = StringBuilder()

        builder.append("val ")
        builder.append(this.name)
        builder.append(":")
        builder.append(this.type.simpleName)
        builder.append(" get()=")
        builder.append("Manipulate.cast(accessExportedField(this,")
        builder.append("\"${exportName}\"")
        builder.append(",")
        builder.append("\"${this.name}\"")
        builder.append("))\n")

        return builder.toString()
    }

}
