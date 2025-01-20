package com.github.cao.awa.conium.item

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.bedrock.destory.ConiumBedrockCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.item.template.tool.mining.ConiumForceMiningSpeedTemplate
import com.github.cao.awa.conium.kotlin.extent.component.acquire
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.random.ConiumRandom
import net.minecraft.block.AbstractBlock
import net.minecraft.block.BlockState
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ToolComponent
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumItem(private val settings: ConiumItemSettings) : Item(settings.vanillaSettings) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumItem")

        fun create(builder: ConiumItemBuilder, settings: ConiumItemSettings): ConiumItem {
            builder.distinct()

            builder.forEachTemplate {
                it.prepare(settings)
            }

            forceOverrideSettings(settings.vanillaSettings)

            return ConiumItem(settings).apply {
                builder.forEachTemplate { it.attach(this) }

                builder.forEachTemplate { it.complete(this) }
            }
        }

        private fun forceOverrideSettings(settings: Settings) {
            settings.components.acquire(DataComponentTypes.MAX_DAMAGE, settings::maxDamage) {
                LOGGER.warn("Found template 'max_damage' in item, force overriding max stack to 1")
            }
        }
    }

    /**
     * Check the item is allowing to break blocks when player holding this item.
     *
     * @param state the block state of mining target
     * @param world the world of the block
     * @param pos the position of the block in the world
     * @param miner the miner that mining the block
     *
     * @see Item.canMine
     * @see ConiumCanDestroyInCreativeTemplate
     * @see ConiumBedrockCanDestroyInCreativeTemplate
     *
     * @author cao_awa
     *
     * @since 1.0.0
     *
     * @return whether a player can break a block while holding the item
     */
    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean = this.settings.canMinePredicate(this, state, world, pos, miner)

    /**
     * Called on the server when the item is used to break a block.
     *
     * Tools and melee weapons should override this to damage the stack, after checking if the block's hardness is larger than 0.0f.
     *
     * @param stack the stack that miner used
     * @param world the world of the block and miner
     * @param state the block state of mined block
     * @param pos the position of mined block
     * @param miner the miner that mined the block
     *
     * @author cao_awa
     *
     * @see Item.postMine
     * @see ItemStack.damage
     * @see AbstractBlock.AbstractBlockState.getHardness
     *
     * @since 1.0.0
     *
     * @return whether the item's use stat should be incremented
     */
    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        // Rolling chance using world random.
        // If damage chance present, then try to roll a chance, or else direct allow to damage the item.
        val canDamage: Boolean = ConiumRandom.tryChance(this.settings.durabilityDamageChance, world.random)

        // If can damage, then post mine to super.
        return canDamage && super.postMine(stack, world, state, pos, miner)
    }

    /**
     * Called on the server when the item is used to hit an entity.
     *
     * @param stack the stack that attacker used
     * @param target attack target
     * @param attacker the attacker
     *
     * @see ItemStack.damage
     * @see ConiumItemToolTemplate
     *
     * @author cao_awa
     *
     * @since 1.0.0
     *
     * @return whether the item's use stat should be incremented
     */
    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean = this.settings.shouldPostHit

    /**
     * Make the tool item durability decrement, for vanilla behaviors, decrement amount in weapon is 1, in non-weapon tool is 2.
     *
     * @param stack the stack that attacker used
     * @param target attack target
     * @param attacker the attacker
     *
     * @see Item.postDamageEntity
     * @see ConiumItemToolTemplate
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun postDamageEntity(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
        // Apply durability decrement for this tool stack.
        stack.damage(
            this.settings.durabilityDamageEntityAmount,
            attacker,
            EquipmentSlot.MAINHAND
        )
    }

    /**
     * Compute the mining speed of a block when using this item, force override material component when force mining speed are set.
     *
     * @param stack the stack that miner used
     * @param state the target block state
     *
     * @see Item.getMiningSpeed
     * @see ToolComponent
     * @see ConiumForceMiningSpeedTemplate
     *
     * @author cao_awa
     *
     * @since 1.0.0
     *
     * @return the mining speed
     */
    override fun getMiningSpeed(stack: ItemStack, state: BlockState): Float {
        return if (this.settings.forceMiningSpeed == -1F) {
            super.getMiningSpeed(stack, state)
        } else {
            this.settings.forceMiningSpeed
        }
    }
}
