package com.github.cao.awa.conium.entity.setting

import com.github.cao.awa.conium.setting.ConiumSettings
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.LivingEntity

abstract class ConiumAbstractEntitySettings<E : ConiumAbstractEntitySettings<E>> : ConiumSettings<ConiumAbstractEntitySettings<E>, E>() {
    /**
     * Setting the collision dimension of the entity.
     *
     * Default is ``0.0, 0.0`` value to ``EntityDimensions`` for conium entity.
     *
     * @see Entity.isPushable
     * @see LivingEntity.isPushable
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var dimensions: EntityDimensions
        get() = this._dimensions ?: ConiumEntitySettingsValue.dimensions
        set(value) {
            this._dimensions = value
        }

    // The delegate.
    private var _dimensions: EntityDimensions? = null

    /**
     * Setting an entity is can be pushed by other entities.
     *
     * Default is ``true`` for conium entity.
     *
     * @see Entity.isPushable
     * @see LivingEntity.isPushable
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var pushable: Boolean
        get() = this._pushable ?: ConiumEntitySettingsValue.pushable
        set(value) {
            this._pushable = value
        }

    // The delegate.
    private var _pushable: Boolean? = null

    /**
     * Setting an entity is can be pushed by pistons.
     *
     * Default is ``true`` for conium entity, correspond [PistonBehavior.NORMAL] in vanilla piston behavior [Entity.getPistonBehavior].
     *
     * @see PistonBehavior
     * @see Entity.getPistonBehavior
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var pushableByPiston: Boolean
        get() = this._pushableByPiston ?: ConiumEntitySettingsValue.pushableByPiston
        set(value) {
            this._pushableByPiston = value
        }

    // The delegate.
    private var _pushableByPiston: Boolean? = null

    /**
     * Setting an entity is can be pushed by fluids.
     *
     * Default is ``true`` for conium entity.
     *
     * @see Entity.isPushedByFluids
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var pushableByFluids: Boolean
        get() = this._pushableByFluids ?: ConiumEntitySettingsValue.pushableByFluids
        set(value) {
            this._pushableByFluids = value
        }

    // The delegate.
    private var _pushableByFluids: Boolean? = null

    open var client: ConiumClientEntitySettings
        get() = this._clientMigrate ?: let {
            this.client = ConiumClientEntitySettings()
            this._clientMigrate!!
        }
        set(value) {
            this._clientMigrate = value
        }

    // The delegate.
    private var _clientMigrate: ConiumClientEntitySettings? = null

    override fun migrateTo(settings: E): E {
        return settings.also {
            // Apply settings(only configured, no default).
            this._dimensions?.apply { it.dimensions = this }
            this._pushable?.apply { it.pushable = this }
            this._pushableByPiston?.apply { it.pushableByPiston = this }
            this._pushableByFluids?.apply { it.pushableByFluids = this }
        }
    }
}