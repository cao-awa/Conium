package com.github.cao.awa.conium.nbt.data.str

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for string
 * 
 * @see ConiumNbtDataSerializer
 * 
 * @since 1.0.0
 */
class ConiumNbtStringSerializer: ConiumNbtDataSerializer<String>() {
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): String = nbt.getString(key)
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: String) = nbt.putString(key, value)
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): String = json[key].asString
}
