package com.github.cao.awa.conium.nbt.data.primary

import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.google.gson.JsonObject
import net.minecraft.nbt.CompoundTag
import net.minecraft.core.HolderLookup
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import java.util.function.Supplier

/**
 * NBT serializer for short.
 *
 * @see Short
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
     * @see ValueInput
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(readView: ValueInput, key: String, fallback: Supplier<Short>): Short = readView.getShortOr(key, fallback.get()).toShort()

    /**
     * Serialize a short value to a data view.
     *
     * @param writeView the 'write view'
     * @param key the key of data
     * @param value the value of data
     *
     * @see Short
     * @see ValueOutput
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(writeView: ValueOutput, key: String, value: Short) = writeView.putShort(key, value)

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