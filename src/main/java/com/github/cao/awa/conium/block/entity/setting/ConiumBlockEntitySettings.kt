package com.github.cao.awa.conium.block.entity.setting

import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.setting.ConiumAbstractBlockSettings
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.setting.ConiumSettings
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.block.entity.BlockEntityType

class ConiumBlockEntitySettings(private val blockSettings: ConiumAbstractBlockSettings<*>) : ConiumSettings<ConiumBlockEntitySettings, ConiumBlockEntitySettings>() {
    companion object {
        @JvmStatic
        fun create(settings: ConiumBlockSettings): ConiumBlockEntitySettings {
            return ConiumBlockEntitySettings(settings)
        }
    }

    /**
     * Setting the block entity type.
     *
     * Default is null.
     *
     * @see BlockEntityType
     * @see ConiumBlockEntity
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var type: BlockEntityType<ConiumBlockEntity>? = null

    var registeredData: Map<String, ConiumNbtDataSerializer<*>> = CollectionFactor.hashMap()

    var defaultData: Map<String, Any> = CollectionFactor.hashMap()

    override fun migrateTo(settings: ConiumBlockEntitySettings): ConiumBlockEntitySettings {
        return settings.also {
            // Apply settings (only configured, no default).
            this.type?.apply { it.type = this }
        }
    }

    override fun newInstance(): ConiumBlockEntitySettings = ConiumBlockEntitySettings(this.blockSettings)
}
