package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * NBT serializer for byte.
 *
 * @see Byte
 * @see NbtCompound
 * @see JsonObject
 * @see RegistrableNbt
 * @see ConiumNbtDataSerializer
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumNbtByteSerializer : ConiumNbtDataSerializer<Byte>() {
    /**
     * Deserialize a byte value from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Byte
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): Byte = nbt.getByte(key)
    /**
     * Serialize a byte value to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registries
     * @param key the key of data
     * @param value the value of data
     *
     * @see Byte
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: Byte) = nbt.putByte(key, value)
    /**
     * Deserialize a byte value from JSON object.
     *
     * @param json the JSON object
     * @param registries game registries
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Byte
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): Byte = json[key].asByte
}