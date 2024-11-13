package com.github.cao.awa.conium.entity.setting

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.entity.EntityType
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.LivingEntity
import net.minecraft.util.Identifier

object ConiumEntitySettingsValue {
    val clientMigrateKey = "#client_migrate"

    /**
     * Default value of ``dimensions``.
     *
     * @see ConiumEntitySettings.dimensions
     * @see Entity.dimensions
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val dimensions: EntityDimensions = EntityDimensions.changing(0.0F, 0.0F)

    /**
     * Default value of ``pushable``.
     *
     * @see ConiumEntitySettings.pushable
     * @see Entity.isPushable
     * @see LivingEntity.isPushable
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val pushable: Boolean = true

    /**
     * Default value of ``pushableByPiston``.
     *
     * @see ConiumEntitySettings.pushableByPiston
     * @see PistonBehavior
     * @see Entity.getPistonBehavior
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val pushableByPiston: Boolean = true

    /**
     * Default value of ``pushableByFluids``.
     *
     * @see ConiumEntitySettings.pushableByFluids
     * @see Entity.isPushedByFluids
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val pushableByFluids: Boolean = true

    /**
     * Default value of ``clientModel``.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see EntityRenderer
     * @see ConiumEntityRenderer
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val clientModel: (EntityRendererFactory.Context) -> ConiumEntityModel = { ConiumEntityModel.emptyModel }

    /**
     * Default value of ``clientModelTexture``.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see EntityRenderer
     * @see ConiumEntityRenderer
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val clientModelTexture: Identifier = Identifier.ofVanilla("textures/misc/white.png")
}

class ConiumEntitySettings {
    companion object {
        @JvmStatic
        fun create(templates: MutableList<ConiumEntityTemplate>, type: EntityType.Builder<ConiumEntity>): ConiumEntitySettings {
            return ConiumEntitySettings().also {
                templates.forEach { template ->
                    template.prepare(ConiumEntitySettingsWithTypeBuilder(type, it))
                }
            }
        }
    }

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

    /**
     * Setting the rendering model of the entity.
     *
     * Default is ``emptyModel`` for conium entity.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see EntityRenderer
     * @see ConiumEntityRenderer
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var clientModel: (EntityRendererFactory.Context) -> ConiumEntityModel
        get() = this._clientModel ?: ConiumEntitySettingsValue.clientModel
        set(value) {
            this._clientModel = value
        }

    // The delegate.
    private var _clientModel: ((EntityRendererFactory.Context) -> ConiumEntityModel)? = null

    /**
     * Setting the rendering model texture of the entity.
     *
     * Default is a white texture for conium entity.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see EntityRenderer
     * @see ConiumEntityRenderer
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var clientModelTexture: Identifier
        get() = this._clientModelTexture ?: ConiumEntitySettingsValue.clientModelTexture
        set(value) {
            this._clientModelTexture = value
        }

    // The delegate.
    private var _clientModelTexture: Identifier? = null

    private val migrates: MutableMap<String, ConiumEntitySettings> = CollectionFactor.hashMap()

    /**
     * Migrate settings to new settings instance.
     *
     * Do attention when calls, 'migrate' will always create a new instance for every get.
     *
     * @see migrateTo
     * @see compute
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    // Self migrate, do not call 'migrateTo'.
    val migrate get() = migrateTo(ConiumEntitySettings())

    private fun migrateTo(settings: ConiumEntitySettings): ConiumEntitySettings {
        return settings.also {
            // Apply settings(only configured, no default).
            this._dimensions?.apply { it.dimensions = this }
            this._pushable?.apply { it.pushable = this }
            this._pushableByPiston?.apply { it.pushableByPiston = this }
            this._pushableByFluids?.apply { it.pushableByFluids = this }
            this._clientModel?.apply { it.clientModel = this }
            this._clientModelTexture?.apply { it.clientModelTexture = this }
        }
    }

    fun migrate(name: String): ConiumEntitySettings {
        // Migrate this settings to new migrating instance.
        return this.migrate.also {
            // Migrate target group settings to result.
            this.migrates[name]?.migrateTo(it)
        }
    }

    fun migrate(name: String, settings: ConiumEntitySettings) {
        this.migrates[name] = settings
    }

    fun migrate(name: String, operator: (ConiumEntitySettings) -> Unit) {
        // Get and operate the settings, then set it back to migrates.
        this.migrates[name] = (this.migrates[name] ?: ConiumEntitySettings()).also(operator)
    }

    fun compute(vararg names: String): ConiumEntitySettings {
        var result = this
        for (name in names) {
            // Migrates the settings.
            result = result.migrate(name)
        }
        println(this.migrates)
        return result
    }
}

class ConiumEntitySettingsWithTypeBuilder(val builder: EntityType.Builder<ConiumEntity>, val settings: ConiumEntitySettings)