package com.github.cao.awa.conium.nbt.data.color

import com.github.cao.awa.conium.kotlin.extent.innate.byte
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.kotlin.extent.json.ifString
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.DyeColor

/**
 * NBT serializer for dye color
 */
class ConiumNbtDyeColorSerializer : ConiumNbtDataSerializer<DyeColor>() {
    companion object {
        private val COLORS: Map<Int, DyeColor> = CollectionFactor.hashMap<Int, DyeColor>().also{
            it[0] = DyeColor.WHITE
            it[1] = DyeColor.ORANGE
            it[2] = DyeColor.MAGENTA
            it[3] = DyeColor.LIGHT_BLUE
            it[4] = DyeColor.YELLOW
            it[5] = DyeColor.LIME
            it[6] = DyeColor.PINK
            it[7] = DyeColor.GRAY
            it[8] = DyeColor.LIGHT_GRAY
            it[9] = DyeColor.CYAN
            it[10] = DyeColor.PURPLE
            it[11] = DyeColor.BLUE
            it[12] = DyeColor.BROWN
            it[13] = DyeColor.GREEN
            it[14] = DyeColor.RED
            it[15] = DyeColor.BLACK
        }
    }

    override fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): DyeColor = COLORS[nbt.getByte(key).int]!!

    override fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): DyeColor {
        return json[key].ifInt({ i ->
            COLORS[i]
        }) {
            it.ifString ({  name ->
                when (name) {
                    DyeColor.WHITE.name -> DyeColor.WHITE
                    DyeColor.ORANGE.name -> DyeColor.ORANGE
                    DyeColor.MAGENTA.name -> DyeColor.MAGENTA
                    DyeColor.LIGHT_BLUE.name -> DyeColor.LIGHT_BLUE
                    DyeColor.YELLOW.name -> DyeColor.YELLOW
                    DyeColor.LIME.name -> DyeColor.LIME
                    DyeColor.PINK.name -> DyeColor.PINK
                    DyeColor.GRAY.name -> DyeColor.GRAY
                    DyeColor.LIGHT_GRAY.name -> DyeColor.LIGHT_GRAY
                    DyeColor.CYAN.name -> DyeColor.CYAN
                    DyeColor.PURPLE.name -> DyeColor.PURPLE
                    DyeColor.BLUE.name -> DyeColor.BLUE
                    DyeColor.BROWN.name -> DyeColor.BROWN
                    DyeColor.GREEN.name -> DyeColor.GREEN
                    DyeColor.RED.name -> DyeColor.RED
                    DyeColor.BLACK.name -> DyeColor.BLACK
                    else -> throw IllegalArgumentException("Dye color $name is not allowed")
                }
            }) {
                throw IllegalArgumentException("Dye color data must be color id or name")
            }
        }!!
    }

    override fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: DyeColor) = nbt.putByte(key, value.id.byte)
}
