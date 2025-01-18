package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for int
 */
class ConiumNbtIntSerializer : ConiumNbtDataSerializer<Int>() {
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Int = nbt.getInt(key)
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Int = json[key].asInt
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Int) = nbt.putInt(key, value)
}
