package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import java.util.function.Supplier

/**
 * NBT serializer for short.
 *
 * @see Short
 * @see ReadView
 * @see WriteView
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
     * @param readView the 'read view'
     * @param key the key of data
     * @param fallback the fallback when the data view got null
     *
     * @return the deserialize result
     *
     * @see Short
     * @see ReadView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(readView: ReadView, key: String, fallback: Supplier<Short>): Short = readView.getShort(key, fallback.get()).toShort()

    /**
     * Serialize a short value to a data view.
     *
     * @param writeView the 'write view'
     * @param key the key of data
     * @param value the value of data
     *
     * @see Short
     * @see WriteView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(writeView: WriteView, key: String, value: Short) = writeView.putShort(key, value)

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
    override fun readFromJson(json: JsonObject, key: String): Short = json[key].asShort
}