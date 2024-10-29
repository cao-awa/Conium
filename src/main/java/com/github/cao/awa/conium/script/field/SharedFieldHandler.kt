package com.github.cao.awa.conium.script.field

import com.github.cao.awa.conium.parameter.ParameterSelective0
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.script.ScriptExport
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import java.util.HashMap
import kotlin.reflect.KClass

class SharedFieldHandler {
    val fields: HashMap<String, SharedField> = CollectionFactor.hashMap()

    operator fun <T : Any> invoke(name: String, type: KClass<T>, value: ParameterSelective1<T, Any>) {
        this.fields[name] = SharedFieldByJvm(type, name, value)
    }

    operator fun invoke(name: String, statement:String) {
        this.fields[name] = SharedFieldByStatement(statement)
    }

    operator fun invoke(export: ScriptExport) {
        export.fields = this.fields
    }
}
