@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.component

import com.github.cao.awa.conium.component.value.ConiumValueCreator
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.mojang.serialization.Codec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import java.util.*
import java.util.function.UnaryOperator

object ConiumComponentTypes {
    private val types: MutableMap<Identifier, ConiumComponentType<*>> = CollectionFactor.hashMap()

    val TEST: ConiumComponentType<Int> = register(
        "test",
        { builder: ConiumComponentTypeBuilder<Int> -> builder.codec(Codec.INT).packetCodec(PacketCodecs.INTEGER) },
        JsonElement::getAsInt
    )

    fun <T> register(path: String, builderOperator: UnaryOperator<ConiumComponentTypeBuilder<T>>, valueCreator: ConiumValueCreator<T>): ConiumComponentType<T> {
        return register(Identifier.of("conium", path), builderOperator, valueCreator)
    }

    fun <T> register(id: Identifier, builderOperator: UnaryOperator<ConiumComponentTypeBuilder<T>>, valueCreator: ConiumValueCreator<T>): ConiumComponentType<T> {
        val type: ConiumComponentType<T> = builderOperator.apply(ConiumComponentTypeBuilder(id.toString(), valueCreator)).build()
        Registry.register(Registries.DATA_COMPONENT_TYPE, id, type)
        this.types[id] = type
        return type
    }

    fun init() {
    }

    fun types(): Map<Identifier, ConiumComponentType<*>> = Collections.unmodifiableMap(this.types)

    fun get(path: String): ConiumComponentType<*> {
        return this.types[Identifier.of("conium", path)]!!
    }

    fun get(identifier: Identifier): ConiumComponentType<*> {
        return this.types[identifier]!!
    }

    fun <T> createValue(path: String, element: JsonElement): T {
        return createValue(Identifier.of("conium", path), element)
    }

    fun <T> createValue(type: Identifier, element: JsonElement): T {
        return this.types[type]!!.valueCreator.createValue(element) as T
    }
}
