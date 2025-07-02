package com.github.cao.awa.conium.item.setting

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.setting.ConiumSettings
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object ConiumItemSettingsValue {
    val canMinePredicate: (ItemStack, BlockState, World, BlockPos, LivingEntity) -> Boolean = { _, _, _, _, _ -> true }
    val isWeapon: Boolean = false
    val shouldPostHit: Boolean = false
    val durabilityDamageChance: IntRange = ConiumDurabilityTemplate.defaultChance
    val forceMiningSpeed: Float = -1F
}

abstract class ConiumAbstractItemSettings<B : ConiumAbstractItemSettings<B>>(val vanillaSettings: Item.Settings) : ConiumSettings<ConiumAbstractItemSettings<B>, B>() {
    /**
     * The predicate to checks the block that can be mined by this item, in vanilla is always mineable if not specify.
     *
     * @author cao_awa
     *
     * @see Item.canMine
     *
     * @since 1.0.0
     */
    var canMinePredicate: (ItemStack, BlockState, World, BlockPos, LivingEntity) -> Boolean
        get() = this._canMinePredicate ?: ConiumItemSettingsValue.canMinePredicate
        set(value) {
            this._canMinePredicate = value
        }

    // The delegate.
    private var _canMinePredicate: ((ItemStack, BlockState, World, BlockPos, LivingEntity) -> Boolean)? = null

    /**
     * A mark that marked a tool item be a weapon, used in durability decrements when hitting entity.
     *
     * In vanilla has a difference to decrements durability amounts which item is weapon or non-weapon.
     *
     * @author cao_awa
     *
     * @see Item.postDamageEntity
     *
     * @since 1.0.0
     */
    var isWeapon: Boolean
        get() = this._isWeapon ?: ConiumItemSettingsValue.isWeapon
        set(value) {
            this._isWeapon = value
        }

    // The delegate.
    private var _isWeapon: Boolean? = null

    var shouldPostHit: Boolean
        get() = this._shouldPostHit ?: ConiumItemSettingsValue.shouldPostHit
        set(value) {
            this._shouldPostHit = value
        }

    // The delegate.
    private var _shouldPostHit: Boolean? = null

    var durabilityDamageChance: IntRange
        get() = this._durabilityDamageChance ?: ConiumItemSettingsValue.durabilityDamageChance
        set(value) {
            this._durabilityDamageChance = value
        }

    // The delegate.
    private var _durabilityDamageChance: IntRange? = null

    val durabilityDamageEntityAmount: Int
        get() = if (this.isWeapon) 1 else 2

    var forceMiningSpeed: Float
        get() = this._forceMiningSpeed ?: ConiumItemSettingsValue.forceMiningSpeed
        set(value) {
            this._forceMiningSpeed = value
        }

    // The delegate.
    private var _forceMiningSpeed: Float? = null

    override fun migrateTo(settings: B): B {
        return settings.also {
            // Apply settings(only configured, no default).
            this._canMinePredicate?.apply { it.canMinePredicate = this }
            this._isWeapon?.apply { it.isWeapon = this }
            this._shouldPostHit?.apply { it.shouldPostHit = this }
            this._durabilityDamageChance?.apply { it.durabilityDamageChance = this }
            this._forceMiningSpeed?.apply { it.forceMiningSpeed = this }
        }
    }
}

class ConiumItemSettings(vanillaSettings: Item.Settings) : ConiumAbstractItemSettings<ConiumItemSettings>(vanillaSettings) {
    companion object {
        @JvmStatic
        fun create(templates: MutableList<ConiumItemTemplate>, settings: Item.Settings): ConiumItemSettings {
            return ConiumItemSettings(settings).also {
                templates.forEach { template ->
                    template.prepare(it)
                }
            }
        }
    }

    override fun newInstance(): ConiumItemSettings = ConiumItemSettings(this.vanillaSettings)
}
