package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for double.
 *
 * @see Double
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 * @see ConiumNbtFloatSerializer
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumNbtDoubleSerializer : ConiumNbtDataSerializer<Double>() {
    /**
     * Deserialize a double value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Double
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Double = nbt.getDouble(key)
    /**
     * Serialize a double value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     * @param value the value of data
     *
     * @see Double
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Double) = nbt.putDouble(key, value)
    /**
     * Deserialize a double value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Double
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Double = json[key].asDouble
}