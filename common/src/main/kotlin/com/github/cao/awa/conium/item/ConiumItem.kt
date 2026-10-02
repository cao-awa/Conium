package com.github.cao.awa.conium.item

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.component.destory.BedrockCanDestroyInCreativeComponent
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.item.template.tool.mining.ConiumForceMiningSpeedTemplate
import com.github.cao.awa.conium.kotlin.extent.component.acquire
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.random.ConiumRandom
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.component.Tool
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionHand
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
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

        private fun forceOverrideSettings(settings: Item.Properties) {
            settings.components.acquire(DataComponents.MAX_DAMAGE, settings::durability) {
                LOGGER.warn("Found template 'max_damage' in item, force overriding max stack to 1")
            }
        }

        @JvmStatic
        fun doRaycast(world: Level, player: Player, fluidHandling: ClipContext.Fluid): BlockHitResult {
            val eyePos: Vec3 = player.eyePosition
            val endPos: Vec3 = eyePos.add(
                player.getViewVector(1.0f).scale(player.blockInteractionRange())
            )
            return world.clip(
                ClipContext(
                    eyePos,
                    endPos,
                    ClipContext.Block.OUTLINE,
                    fluidHandling,
                    player
                )
            )
        }
    }

    var displayName: Component? = this.settings.displayName
    var useAction: ItemUseAnimation = ItemUseAnimation.NONE
    var consumeOnUsed: Boolean = false
    var consumeOnUsedOnBlock: (BlockState) -> Boolean = { false }
    var consumeOnUsedOnEntity: (LivingEntity) -> Boolean = { false }
    val useOnBlockHandlers: MutableList<(context: UseOnContext) -> Boolean> = ArrayList()
    val useHandlers: MutableList<(world: Level, user: Player, hand: InteractionHand) -> Boolean> = ArrayList()
    val useOnEntityHandlers: MutableList<(stack: ItemStack, user: Player, target: LivingEntity, hand: InteractionHand) -> Boolean> = ArrayList()

    override fun canDestroyBlock(stack: ItemStack, state: BlockState, world: Level, pos: BlockPos, miner: LivingEntity): Boolean = this.settings.canMinePredicate(stack, state, world, pos, miner)

    override fun mineBlock(stack: ItemStack, world: Level, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        val canDamage: Boolean = ConiumRandom.tryChance(this.settings.durabilityDamageChance, world.random)
        return canDamage && super.mineBlock(stack, world, state, pos, miner)
    }

    override fun hurtEnemy(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
        stack.hurtAndBreak(
            this.settings.durabilityDamageEntityAmount,
            attacker,
            EquipmentSlot.MAINHAND
        )
    }

    override fun getDestroySpeed(stack: ItemStack, state: BlockState): Float {
        return if (this.settings.forceMiningSpeed == -1F) {
            super.getDestroySpeed(stack, state)
        } else {
            this.settings.forceMiningSpeed
        }
    }

    override fun getUseAnimation(stack: ItemStack): ItemUseAnimation {
        return this.useAction
    }

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResult {
        if (
            !this.useHandlers.isEmpty() && this.useHandlers.any {
                !it(world, user, hand)
            }
        ) {
            return InteractionResult.FAIL
        }

        return if (this.consumeOnUsed) {
            user.getItemInHand(hand).consume(1, user)
            InteractionResult.CONSUME
        } else {
            InteractionResult.SUCCESS
        }
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        if (
            !this.useOnBlockHandlers.isEmpty() && this.useOnBlockHandlers.any {
                !it(context)
            }
        ) {
            return InteractionResult.PASS
        }

        val world: Level = context.level
        val stack: ItemStack = context.itemInHand
        val blockPos: BlockPos = context.clickedPos
        val blockState: BlockState = world.getBlockState(blockPos)

        return if (this.consumeOnUsedOnBlock(blockState)) {
            val user: Player? = context.player
            if (user != null) stack.consume(1, user) else stack.shrink(1)
            InteractionResult.CONSUME
        } else {
            InteractionResult.SUCCESS
        }
    }

    override fun interactLivingEntity(stack: ItemStack, user: Player, target: LivingEntity, hand: InteractionHand): InteractionResult {
        if (
            !this.useOnEntityHandlers.isEmpty() && this.useOnEntityHandlers.any {
                !it(stack, user, target, hand)
            }
        ) {
            return InteractionResult.PASS
        }

        return if (this.consumeOnUsedOnEntity(target)) {
            stack.consume(1, user)
            InteractionResult.CONSUME
        } else {
            InteractionResult.SUCCESS
        }
    }

    override fun getName(stack: ItemStack): Component {
        return this.displayName ?: super.getName(stack)
    }
}
