package com.github.cao.awa.conium.nbt.data.string

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import java.util.function.Supplier

/**
 * NBT serializer for string.
 *
 * @see String
 * @see ValueInput
 * @see ValueOutput
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
     * Deserialize a string value from a data view.
     *
     * @param readView the read view
     * @param key the key of data
     * @param fallback the fallback data when reading data got null
     *
     * @return the deserialize result
     *
     * @see String
     * @see ValueInput
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(readView: ValueInput, key: String, fallback: Supplier<String>): String = readView.getStringOr(key, fallback.get())

    /**
     * Serialize a string value to a data view.
     *
     * @param writeView the 'write view'
     * @param key the key of data
     * @param value the value of data
     *
     * @see String
     * @see ValueOutput
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(writeView: ValueOutput, key: String, value: String) = writeView.putString(key, value)

    /**
     * Deserialize a string value from JSON object.
     *
     * @param json the JSON object
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
    override fun readFromJson(json: JsonObject, key: String): String = json[key].asString
}
