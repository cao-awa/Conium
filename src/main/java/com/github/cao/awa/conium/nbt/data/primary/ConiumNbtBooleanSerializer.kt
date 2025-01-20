package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for boolean.
 *
 * @see Boolean
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumNbtBooleanSerializer : ConiumNbtDataSerializer<Boolean>() {
    /**
     * Deserialize a boolean value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Boolean
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Boolean = nbt.getBoolean(key)
    /**
     * Serialize a boolean value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     * @param value the value of data
     *
     * @see Boolean
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Boolean) = nbt.putBoolean(key, value)
    /**
     * Deserialize a boolean value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Boolean
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Boolean = json[key].asBoolean
}