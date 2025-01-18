package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for float
 */
class ConiumNbtFloatSerializer : ConiumNbtDataSerializer<Float>() {
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Float = nbt.getFloat(key)
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Float = json[key].asFloat
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Float) = nbt.putFloat(key, value)
} 