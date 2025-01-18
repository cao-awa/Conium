package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for double
 */
class ConiumNbtDoubleSerializer : ConiumNbtDataSerializer<Double>() {
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Double = nbt.getDouble(key)
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Double = json[key].asDouble
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Double) = nbt.putDouble(key, value)
} 