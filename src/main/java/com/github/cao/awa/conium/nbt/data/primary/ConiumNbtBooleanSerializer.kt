package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for boolean
 */
class ConiumNbtBooleanSerializer : ConiumNbtDataSerializer<Boolean>() {
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Boolean = nbt.getBoolean(key)
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Boolean = json[key].asBoolean
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Boolean) = nbt.putBoolean(key, value)
} 