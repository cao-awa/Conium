package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import java.util.function.Supplier

/**
 * NBT serializer for byte.
 *
 * @see Byte
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
class ConiumNbtByteSerializer : ConiumNbtDataSerializer<Byte>() {
    /**
     * Deserialize a byte value from a data view.
     *
     * @param readView the 'read view'
     * @param key the key of data
     * @param fallback the fallback when the data view got null
     *
     * @return the deserialize result
     *
     * @see Byte
     * @see ReadView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(readView: ReadView, key: String, fallback: Supplier<Byte>): Byte = readView.getByte(key, fallback.get())

    /**
     * Serialize a byte value to a data view.
     *
     * @param writeView the 'write view'
     * @param key the key of data
     * @param value the value of data
     *
     * @see Byte
     * @see WriteView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(writeView: WriteView, key: String, value: Byte) = writeView.putByte(key, value)

    /**
     * Deserialize a byte value from JSON object.
     *
     * @param json the JSON object
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
    override fun readFromJson(json: JsonObject, key: String): Byte = json[key].asByte
}