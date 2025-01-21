package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for float.
 *
 * @see Float
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 * @see ConiumNbtDoubleSerializer
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumNbtFloatSerializer : ConiumNbtDataSerializer<Float>() {
    /**
     * Deserialize a float value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Float
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Float = nbt.getFloat(key)
    /**
     * Serialize a float value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     * @param value the value of data
     *
     * @see Float
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Float) = nbt.putFloat(key, value)
    /**
     * Deserialize a float value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Float
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Float = json[key].asFloat
}