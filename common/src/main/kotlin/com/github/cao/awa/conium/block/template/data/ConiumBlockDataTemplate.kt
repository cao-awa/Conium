package com.github.cao.awa.conium.block.template.data

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.jsonObject
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement

/**
 * Setting the block entity data of the block with registered key and default values.
 *
 * The registrable NBT will ignore all data where there are not registered here, all no-registered data cannot be serialized.
 *
 * @see ConiumBlock
 * @see ConiumBlockEntity
 * @see ConiumBlockSettings
 * @see ConiumBlockEntitySettings
 * @see ConiumNbtDataSerializer
 * @see RegistrableNbt
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumBlockDataTemplate(
    private val registered: Map<String, ConiumNbtDataSerializer<*>>,
    private val defaultValues: Map<String, Any>
): ConiumBlockTemplate(name = ConiumTemplates.Block.DATA) {
    companion object {
        /**
         * Create the template with registered data and default values.
         *
         * @see ConiumBlock
         * @see ConiumBlockEntity
         * @see ConiumBlockSettings
         * @see ConiumBlockEntitySettings
         * @see ConiumNbtDataSerializer
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockDataTemplate {
            return element.jsonObject!!.let { data ->
                // Create data delegates.
                val registered = CollectionFactor.hashMap<String, ConiumNbtDataSerializer<*>>()
                val defaultValues = CollectionFactor.hashMap<String, Any>()

                // Build all data.
                data.keySet().forEach { key ->
                    val value = data[key].jsonObject!!
                    // Get the type and serializer.
                    val serializer = ConiumNbtDataSerializer.getSerializer(value["type"].asString)
                    // Register serializer and default value.
                    registered[key] = serializer
                    defaultValues[key] = serializer.readFromJson(value, "value")
                }

                // Create the block data template.
                ConiumBlockDataTemplate(
                    registered,
                    defaultValues
                )
            }
        }
    }

    /**
     * Setting the block entity registered data and default values with advanced prepared when parsing data.
     *
     * @see ConiumBlock
     * @see ConiumBlockEntity
     * @see ConiumBlockSettings
     * @see ConiumBlockEntitySettings
     * @see ConiumNbtDataSerializer
     *
     * @param settings the block settings
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun settings(settings: ConiumBlockSettings) {
        // Setting registered data and default values.
        settings.blockEntity.registeredData = this.registered
        settings.blockEntity.defaultData = this.defaultValues
    }
}
