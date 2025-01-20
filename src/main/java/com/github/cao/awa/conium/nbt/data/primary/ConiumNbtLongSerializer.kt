package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for long.
 *
 * @see Long
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumNbtLongSerializer : ConiumNbtDataSerializer<Long>() {
    /**
     * Deserialize a long value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Long
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Long = nbt.getLong(key)
    /**
     * Serialize a long value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     * @param value the value of data
     *
     * @see Long
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Long) = nbt.putLong(key, value)
    /**
     * Deserialize a long value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Long
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Long = json[key].asLong
} 