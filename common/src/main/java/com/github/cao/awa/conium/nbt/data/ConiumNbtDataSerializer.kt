package com.github.cao.awa.conium.nbt.data

import com.github.cao.awa.conium.nbt.data.color.ConiumNbtDyeColorSerializer
import com.github.cao.awa.conium.nbt.data.primary.*
import com.github.cao.awa.conium.nbt.data.string.ConiumNbtStringSerializer
import com.google.gson.JsonObject
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

/**
 * The serializer for NBT data.
 * 
 * @param X the type of data
 *
 * @see JsonObject
 * @see NbtCompound
 * @see RegistrableNbt
 *
 * @author cao_awa
 * @author 草二号机
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

        /**
         * Get preset serialize using name.
         *
         * @param type the name of type
         *
         * @return the serializer
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
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
     *
     * @return the data read
     *
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    abstract fun read(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String): X

    /**
     * Write data to NBT compound.
     * 
     * @param nbt NBT compound
     * @param registries Registry wrapper lookup
     * @param key key of the data
     * @param value data to write
     *
     * @see NbtCompound
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    abstract fun write(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup, key: String, value: X)

    /**
     * Read data from JSON object.
     *
     * @param json JSON object
     * @param registries Registry wrapper lookup
     * @param key key of the data
     *
     * @return the data read
     *
     * @see JsonObject
     *
     * @author 草二号机
     *
     * @since 1.0.0
     */
    abstract fun readFromJson(json: JsonObject, registries: RegistryWrapper.WrapperLookup, key: String): X
}
