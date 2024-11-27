package com.github.cao.awa.conium.bedrock.script

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.type.DynamicArgTypeBuilder.arg
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

@BedrockScriptApi
@BedrockScriptApiFacade("anonymous object")
open class BedrockScriptAnonymousObjectMap : Iterable<MutableMap.MutableEntry<String, Any?>> {
    private val map = CollectionFactor.hashMap<String, Any?>()

    fun add(key: String, value: Any?): BedrockScriptAnonymousObjectMap {
        this.map[key] = value
        return this
    }

    operator fun get(key: String): Any? = this.map[key]

    override fun iterator(): Iterator<MutableMap.MutableEntry<String, Any?>> = this.map.iterator()
}

fun BedrockScriptAnonymousObjectMap.toDynamicArgs(): MutableMap<DynamicArgType<*>, Any?> {
    val map: MutableMap<DynamicArgType<*>, Any?> = CollectionFactor.hashMap()
    for ((key: String, value: Any?) in this) {
        map[arg(key, key.javaClass)] = value
    }
    return map
}