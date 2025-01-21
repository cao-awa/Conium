package com.github.cao.awa.conium.nbt.data.color

import com.github.cao.awa.conium.kotlin.extent.innate.byte
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.kotlin.extent.json.ifString
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.DyeColor

/**
 * NBT serializer for dye color.
 *
 * @see Byte
 * @see DyeColor
 * @see NbtCompound
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
                it[dyeColor.id] = dyeColor
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
     * Deserialize a dye color from NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     *
     * @return the deserialize result
     *
     * @see Byte
     * @see DyeColor
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): DyeColor {
        // Read id from NBT compound.
        val id = nbt.getByte(key).int
        // Get color using color id.
        return COLORS[id]?: throw IllegalArgumentException("Dye color $id is not allowed")
    }

    /**
     * Serialize a dye color to NBT compound.
     *
     * @param nbt the NBT compound
     * @param registries game registry
     * @param key the key of data
     * @param value the value of data
     *
     * @see Byte
     * @see DyeColor
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: DyeColor) {
        // Write id to NBT compound.
        nbt.putByte(key, value.id.byte)
    }

    /**
     * Deserialize a dye color from JSON object.
     *
     * @param json the JSON object
     * @param registries game registry
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
    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): DyeColor {
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
