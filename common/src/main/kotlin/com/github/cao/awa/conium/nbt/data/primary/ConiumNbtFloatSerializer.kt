package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import java.util.function.Supplier

/**
 * NBT serializer for float.
 *
 * @see Float
 * @see ReadView
 * @see WriteView
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
     * DesSerialize a byte value from a data view.
     *
     * @param readView the 'read view'
     * @param key the key of data
     * @param fallback the fallback when the data view got null
     *
     * @return the deserialize result
     *
     * @see Float
     * @see ReadView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(readView: ReadView, key: String, fallback: Supplier<Float>): Float = readView.getFloat(key, fallback.get())

    /**
     * Serialize a float value to a data view.
     *
     * @param writeView the 'write view'
     * @param key the key of data
     * @param value the value of data
     *
     * @see Float
     * @see WriteView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(writeView: WriteView, key: String, value: Float) = writeView.putFloat(key, value)

    /**
     * Deserialize a float value from JSON object.
     *
     * @param json the JSON object
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
    override fun readFromJson(json: JsonObject, key: String): Float = json[key].asFloat
}