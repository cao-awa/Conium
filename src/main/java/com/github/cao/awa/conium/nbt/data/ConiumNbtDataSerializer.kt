package com.github.cao.awa.conium.nbt.data

import com.github.cao.awa.conium.nbt.data.color.ConiumNbtDyeColorSerializer
import com.github.cao.awa.conium.nbt.data.primary.*
import com.github.cao.awa.conium.nbt.data.str.ConiumNbtStringSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper.WrapperLookup

/**
 * The serializer for registrable NBT data.
 * 
 * @param X the type of data
 *
 * @since 1.0.0
 */
abstract class ConiumNbtDataSerializer<X> {
    companion object {
        val STRING: ConiumNbtStringSerializer = ConiumNbtStringSerializer()
        val INT: ConiumNbtIntSerializer = ConiumNbtIntSerializer()
        val LONG: ConiumNbtLongSerializer = ConiumNbtLongSerializer()
        val SHORT: ConiumNbtShortSerializer = ConiumNbtShortSerializer()
        val BYTE: ConiumNbtByteSerializer = ConiumNbtByteSerializer()
        val DOUBLE: ConiumNbtDoubleSerializer = ConiumNbtDoubleSerializer()
        val FLOAT: ConiumNbtFloatSerializer = ConiumNbtFloatSerializer()
        val BOOLEAN: ConiumNbtBooleanSerializer = ConiumNbtBooleanSerializer()
        val DYE_COLOR: ConiumNbtDyeColorSerializer = ConiumNbtDyeColorSerializer()

        fun getSerializer(type: String): ConiumNbtDataSerializer<*> = when (type) {
            "string" -> STRING
            "int" -> INT
            "long" -> LONG
            "short" -> SHORT
            "byte" -> BYTE
            "double" -> DOUBLE
            "float" -> FLOAT
            "boolean" -> BOOLEAN
            "dye_color" -> DYE_COLOR
            else -> throw IllegalArgumentException("Unknown serializer type: $type")
        }
    }

    /**
     * Read data from NBT compound.
     * 
     * @param nbt NBT compound
     * @param registries Registry wrapper lookup
     * @param key key of the data
     * @return the data read
     */
    abstract fun read(nbt: NbtCompound, registries: WrapperLookup, key: String): X

    /**
     * Read data from JSON object.
     *
     * @param json JSON object
     * @param registries Registry wrapper lookup
     * @param key key of the data
     * @return the data read
     */
    abstract fun readFromJson(json: JsonObject, registries: WrapperLookup, key: String): X

    /**
     * Write data to NBT compound.
     * 
     * @param nbt NBT compound
     * @param registries Registry wrapper lookup
     * @param key key of the data
     * @param value data to write
     */
    abstract fun write(nbt: NbtCompound, registries: WrapperLookup, key: String, value: X)
}
