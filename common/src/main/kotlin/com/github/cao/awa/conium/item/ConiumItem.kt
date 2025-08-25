package com.github.cao.awa.conium.item

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.bedrock.destory.BedrockCanDestroyInCreativeComponent
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.item.template.tool.mining.ConiumForceMiningSpeedTemplate
import com.github.cao.awa.conium.kotlin.extend.component.acquire
import com.github.cao.awa.conium.kotlin.extend.item.components
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
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.consume.UseAction
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext
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

        @JvmStatic
        fun doRaycast(world: World, player: PlayerEntity, fluidHandling: RaycastContext.FluidHandling): BlockHitResult {
            val eyePos: Vec3d = player.eyePos
            val endPos: Vec3d = eyePos.add(
                player.getRotationVector(
                    player.pitch,
                    player.yaw
                ).multiply(player.blockInteractionRange)
            )
            return world.raycast(
                RaycastContext(
                    eyePos,
                    endPos,
                    RaycastContext.ShapeType.OUTLINE,
                    fluidHandling,
                    player
                )
            )
        }
    }

    var useAction: UseAction = UseAction.NONE
    var consumeOnUsed: Boolean = false
    var consumeOnUsedOnBlock: (BlockState) -> Boolean = { false }
    var consumeOnUsedOnEntity: (LivingEntity) -> Boolean = { false }
    val useOnBlockHandlers: MutableList<(context: ItemUsageContext) -> Boolean> = ArrayList()
    val useHandlers: MutableList<(world: World, user: PlayerEntity, hand: Hand)  -> Boolean> = ArrayList()
    val useOnEntityHandlers: MutableList<(stack: ItemStack, user: PlayerEntity, target: LivingEntity, hand: Hand) -> Boolean> = ArrayList()

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
     * @see BedrockCanDestroyInCreativeComponent
     *
     * @author cao_awa
     *
     * @since 1.0.0
     *
     * @return whether a player can break a block while holding the item
     */
    override fun canMine(stack: ItemStack, state: BlockState, world: World, pos: BlockPos, miner: LivingEntity): Boolean = this.settings.canMinePredicate(stack, state, world, pos, miner)

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
     * @see Item.postMine
     * @see ItemStack.damage
     * @see AbstractBlock.AbstractBlockState.getHardness
     *
     * @author cao_awa
     *
     * @since 1.0.0
     *
     * @return whether the item's use stat should be incremented
     */
    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        // Rolling chance using world random.
        // If damage chance is present, then try to roll a chance, or else directly allow to damage the item.
        val canDamage: Boolean = ConiumRandom.tryChance(this.settings.durabilityDamageChance, world.random)

        // If can damage, then post mine to super.
        return canDamage && super.postMine(stack, world, state, pos, miner)
    }

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

    override fun getUseAction(stack: ItemStack): UseAction {
        return this.useAction
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): ActionResult {
        if (
            !this.useHandlers.isEmpty() && this.useHandlers.any {
                !it(world, user, hand)
            }
        ) {
            return ActionResult.FAIL
        }

        return if (this.consumeOnUsed) {
            user.getStackInHand(hand).decrementUnlessCreative(1, user)
            ActionResult.CONSUME
        } else {
            ActionResult.SUCCESS
        }
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (
            !this.useOnBlockHandlers.isEmpty() && this.useOnBlockHandlers.any {
                !it(context)
            }
        ) {
            return ActionResult.PASS
        }

        val world: World = context.world
        val stack: ItemStack = context.stack
        val blockPos: BlockPos = context.blockPos
        val blockState: BlockState = world.getBlockState(blockPos)

        return if (this.consumeOnUsedOnBlock(blockState)) {
            stack.decrementUnlessCreative(1, context.player)
            ActionResult.CONSUME
        } else {
            ActionResult.SUCCESS
        }
    }

    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, target: LivingEntity, hand: Hand): ActionResult {
        if (
            !this.useOnEntityHandlers.isEmpty() && this.useOnEntityHandlers.any {
                !it(stack, user, target, hand)
            }
        ) {
            return ActionResult.PASS
        }

        return if (this.consumeOnUsedOnEntity(target)) {
            stack.decrementUnlessCreative(1, user)
            ActionResult.CONSUME
        } else {
            ActionResult.SUCCESS
        }
    }
}
