package com.github.cao.awa.conium.nbt.data

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.nbt.NbtCompound
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.lang.NullPointerException

/**
 * A registration restrictions NBT data delegate, it can write data to NBT compound and read data from NBT compound using registered keys.
 *
 * The registry is confirmed in constructing, keys cannot dynamically change in the registrable NBT lifecycle.
 *
 * Registered keys is avoided appearing unnecessary data or unexpected types it should consider as fields in an object instead of an arbitrary map.
 *
 * @param registries the data keys and type serializers
 * @param modifyCallback the callback before set data
 *
 * @see NbtCompound
 * @see ConiumNbtDataSerializer
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
class RegistrableNbt(
    private val registries: Map<String, ConiumNbtDataSerializer<*>>,
    private val modifyCallback: () -> Unit = { }
) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("RegistrableNbt")
    }

    private val values: MutableMap<String, Any> = CollectionFactor.hashMap()

    /**
     * To get a non-null data using key name, should ensure it already registered.
     *
     * @see NbtCompound
     *
     * @param key the data name
     *
     * @return the data value
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    operator fun <X> get(key: String): X = this.values[key].doCast()

    /**
     * To set the data value using key name, should ensure it already registered and won't be input a null value.
     *
     * @see NbtCompound
     *
     * @param key the data name
     * @param value the data value
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    operator fun set(key: String, value: Any?) {
        if (value == null) {
            throw NullPointerException("Registrable doesn't accepts the null value")
        }

        // Set data.
        this.values[key] = value
        // Only warning when enable debugs.
        // Normally registrable NBT will ignore all not registered data automatically.
        if (!this.registries.containsKey(key)) {
            Conium.debug(
                "A data named '{}' set to registrable NBT but this key doesn't registered, the data will be ignored when serializing: {}",
                { key },
                { value },
                LOGGER::warn
            )
        }
        this.modifyCallback()
    }

    /**
     * Write registrable NBT data to NBT compound, only includes registered data.
     *
     * @see NbtCompound
     * @see ConiumNbtDataSerializer
     *
     * @param nbt the NBT compound that will be writing to
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    fun writeData(writeView: WriteView) {
        // Using registry to serialize data, only registered data can write, doesn't write others data anymore.
        for ((name: String, serializer: ConiumNbtDataSerializer<*>) in this.registries) {
            // Write data to NBT compound.
            serializer.write(writeView, name, Manipulate.cast(this.values[name]))
        }
    }

    /**
     * Read registrable NBT data from NBT compound, only includes registered data.
     *
     * @see ReadView
     *
     * @param readView the 'read view' that should be reading from
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    fun readData(readView: ReadView) {
        // Using registry to deserialize data, only registered data can be read, doesn't read others data anymore.
        for ((name: String, serializer: ConiumNbtDataSerializer<*>) in this.registries) {
            // Read and set data to delegate from NBT compound.
            this[name] = serializer.read(readView, name)
        }
    }
}