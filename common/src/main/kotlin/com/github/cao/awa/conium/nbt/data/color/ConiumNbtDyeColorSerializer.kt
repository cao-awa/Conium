package com.github.cao.awa.conium.nbt.data.color

import com.github.cao.awa.conium.kotlin.extend.innate.byte
import com.github.cao.awa.conium.kotlin.extend.innate.int
import com.github.cao.awa.conium.kotlin.extend.json.ifInt
import com.github.cao.awa.conium.kotlin.extend.json.ifString
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import net.minecraft.util.DyeColor
import java.util.function.Supplier

/**
 * NBT serializer for dye color.
 *
 * @see Byte
 * @see DyeColor
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
class ConiumNbtDyeColorSerializer : ConiumNbtDataSerializer<DyeColor>() {
    companion object {
        private val COLORS: Map<Int, DyeColor> = CollectionFactor.hashMap<Int, DyeColor>().also {
            // Create all dye colors.
            for (dyeColor in DyeColor.entries) {
                it[dyeColor.index] = dyeColor
            }
        }
        private val COLORS_BY_NAME: Map<String, DyeColor> = CollectionFactor.hashMap<String, DyeColor>().also {
            // Create all dye colors.
            for (dyeColor in DyeColor.entries) {
                it[dyeColor.name] = dyeColor
            }
        }
    }

    /**
     * Deserialize a dye color from a data view.
     *
     * @param readView the 'read view'
     * @param key the key of data
     * @param fallback the fallback when the data view got null
     *
     * @return the deserialize result
     *
     * @see Byte
     * @see DyeColor
     * @see ReadView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(readView: ReadView, key: String, fallback: Supplier<DyeColor>): DyeColor {
        // Read id from NBT compound.
        val id = readView.getByte(key, fallback.get().index.byte).int
        // Get color using color id.
        return COLORS[id]?: throw IllegalArgumentException("Dye color $id is not allowed")
    }

    /**
     * Serialize a dye color to a data view.
     *
     * @param writeView the 'write view'
     * @param key the key of data
     * @param value the value of data
     *
     * @see Byte
     * @see DyeColor
     * @see WriteView
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(writeView: WriteView, key: String, value: DyeColor) {
        // Write id to NBT compound.
        writeView.putByte(key, value.index.byte)
    }

    /**
     * Deserialize a dye color from JSON object.
     *
     * @param json the JSON object
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see DyeColor
     * @see JsonObject
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readFromJson(json: JsonObject, key: String): DyeColor {
        return json[key].ifInt(
            // If is integer, try to get color using color id.
            { id ->
                COLORS[id] ?: throw IllegalArgumentException("Dye color $id is not allowed")
            }
        ) {
            it.ifString(
                // If is string, try to get color using color id.
                { name ->
                    COLORS_BY_NAME[name] ?: throw IllegalArgumentException("Dye color $name is not allowed")
                }
            ) {
                // Otherwise is not supported.
                throw IllegalArgumentException("Dye color data must be color id or name")
            }
        }!!
    }
}
