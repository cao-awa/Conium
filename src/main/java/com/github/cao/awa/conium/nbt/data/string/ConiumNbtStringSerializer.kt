package com.github.cao.awa.conium.nbt.data.string

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for string.
 *
 * @see String
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 *
 * @author cao_awa
 * 
 * @since 1.0.0
 */
class ConiumNbtStringSerializer: ConiumNbtDataSerializer<String>() {
    /**
     * Deserialize a string value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see String
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): String = nbt.getString(key)
    /**
     * Serialize a string value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     * @param value the value of data
     *
     * @see String
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: String) = nbt.putString(key, value)
    /**
     * Deserialize a string value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see String
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): String = json[key].asString
}
