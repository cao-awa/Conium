package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for byte
 */
class ConiumNbtByteSerializer : ConiumNbtDataSerializer<Byte>() {
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Byte = nbt.getByte(key)
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Byte = json[key].asByte
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Byte) = nbt.putByte(key, value)
} 