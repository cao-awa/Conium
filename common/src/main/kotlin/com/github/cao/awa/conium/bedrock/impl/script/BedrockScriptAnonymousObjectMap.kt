package com.github.cao.awa.conium.bedrock.impl.script

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.index.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.script.generic.anonymous.AnonymousObject
import com.github.cao.awa.conium.parameter.dynamic.type.DynamicArgType
import com.github.cao.awa.conium.parameter.dynamic.type.builder.DynamicArgTypeBuilder.argThrowaway
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

@BedrockScriptApi
@BedrockScriptApiFacade("anonymous object")
open class BedrockScriptAnonymousObjectMap(size: Int) : AnonymousObject(size) {
    companion object {
        val EMPTY: BedrockScriptAnonymousObjectMap = BedrockScriptAnonymousObjectMap(0)
    }

    override operator fun set(key: String, element: Any?): BedrockScriptAnonymousObjectMap {
        super.set(key, element)
        return this
    }
}

fun BedrockScriptAnonymousObjectMap.toDynamicArgs(): MutableMap<DynamicArgType<*>, Any?> {
    val map: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()
    for ((key: String, value: Any?) in this) {
        map[argThrowaway(key, key.javaClass)] = value
    }
    return map
}