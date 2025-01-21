package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for short.
 *
 * @see Short
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumNbtShortSerializer : ConiumNbtDataSerializer<Short>() {
    /**
     * Deserialize a short value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Short
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Short = nbt.getShort(key)
    /**
     * Serialize a short value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     * @param value the value of data
     *
     * @see Short
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Short) = nbt.putShort(key, value)
    /**
     * Deserialize a short value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Short
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Short = json[key].asShort
}