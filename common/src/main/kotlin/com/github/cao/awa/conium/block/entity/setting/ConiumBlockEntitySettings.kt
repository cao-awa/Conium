package com.github.cao.awa.conium.block.entity.setting

import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.entity.template.ConiumBlockEntityTemplate
import com.github.cao.awa.conium.block.setting.ConiumAbstractBlockSettings
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.setting.ConiumSettings
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType

class ConiumBlockEntitySettings(val blockSettings: ConiumAbstractBlockSettings<*>) : ConiumSettings<ConiumBlockEntitySettings, ConiumBlockEntitySettings>() {
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

    var registeredData: Map<String, ConiumNbtDataSerializer<*>>
        get() {
            if (this._registeredData == null) {
                this._registeredData = CollectionFactor.hashMap()
            }
            return this._registeredData!!
        }
        set(value) {
            this._registeredData = value
        }

    // The delegate.
    private var _registeredData: Map<String, ConiumNbtDataSerializer<*>>? = null

    var defaultData: Map<String, Any>
        get() {
            if (this._defaultData == null) {
                this._defaultData = CollectionFactor.hashMap()
            }
            return this._defaultData!!
        }
        set(value) {
            this._defaultData = value
        }

    // The delegate.
    private var _defaultData: Map<String, Any>? = null

    /**
     * Setting the templates that will apply to the block entity.
     *
     * Default is empty set.
     *
     * @see BlockEntity
     * @see ConiumBlockEntity
     * @see ConiumBlockEntityTemplate
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var blockEntityTemplates: MutableSet<ConiumBlockEntityTemplate>
        get() {
            if (this._blockEntityTemplates == null) {
                this._blockEntityTemplates = CollectionFactor.hashSet()
            }
            return this._blockEntityTemplates!!
        }
        set(value) {
            this._blockEntityTemplates = value
        }

    // The delegate.
    private var _blockEntityTemplates: MutableSet<ConiumBlockEntityTemplate>? = null

    override fun migrateTo(settings: ConiumBlockEntitySettings): ConiumBlockEntitySettings {
        return settings.also {
            // Apply settings (only configured, no default).
            this.type?.apply { it.type = this }
            this._registeredData?.apply { it.registeredData = this }
            this._defaultData?.apply { it.defaultData = this }
            this._blockEntityTemplates?.apply { it.blockEntityTemplates = this }
        }
    }

    override fun newInstance(): ConiumBlockEntitySettings = ConiumBlockEntitySettings(this.blockSettings)
}
