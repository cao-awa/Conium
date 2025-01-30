package com.github.cao.awa.conium.intermediary.mixin.block

import com.github.cao.awa.conium.block.event.breaking.ConiumBreakBlockEvent
import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEvent
import com.github.cao.awa.conium.block.event.use.ConiumUsedBlockEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary
import com.github.cao.awa.conium.kotlin.extent.block.invokeOnUse
import com.github.cao.awa.conium.mixin.block.BlockStateMixin
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBlockEventMixinIntermediary {
    companion object {
        /**
         * Trigger the block breaking event when the block starts breaking.
         *
         * @see Block.onBlockBreakStart
         * @see ConiumEventType.BREAK_BLOCK
         * @see ConiumBreakBlockEvent
         * @see BlockStateMixin.breakingBlock
         *
         * @param eventType the dye event type
         * @param state the breaking block state
         * @param world the world where the block is
         * @param player the miner
         * @param pos the position where the block is
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireBlockBreakingEvent(eventType: ConiumEventType<Block>, state: AbstractBlockState, world: World, player: PlayerEntity, pos: BlockPos): Boolean {
            return ConiumEventMixinIntermediary.fireEventCancelable(
                eventType,
                state.block
            ) { breakingContext ->
                // Fill the context args.
                breakingContext[ConiumEventArgTypes.WORLD] = world
                breakingContext[ConiumEventArgTypes.PLAYER] = player
                breakingContext[ConiumEventArgTypes.BLOCK_POS] = pos
                breakingContext[ConiumEventArgTypes.BLOCK_STATE] = state
            }
        }

        /**
         * Trigger the block use events when the block uses.
         *
         * @see Block
         * @see BlockState
         * @see ConiumEventType#USE_BLOCK
         * @see ConiumEventType#USED_BLOCK
         * @see ConiumUseBlockEvent
         * @see ConiumUsedBlockEvent
         * @see BlockStateMixin.useBlock
         *
         * @param state the usage block state
         * @param world the world where the block is
         * @param player the user
         * @param pos the position where the block is
         * @param hitResult the block hit context
         *
         * @return the action result
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireBlockUsageEvent(state: BlockState, world: World, player: PlayerEntity, pos: BlockPos, hitResult: BlockHitResult): ActionResult {
            val block: Block = state.block

            return ConiumEventMixinIntermediary.fireInheritedCascadedResultEvent(
                ConiumEventType.USE_BLOCK,
                ConiumEventType.USED_BLOCK,
                block,
                { usingContext ->
                    // Fill the context args.
                    usingContext[ConiumEventArgTypes.WORLD] = world
                    usingContext[ConiumEventArgTypes.PLAYER] = player
                    usingContext[ConiumEventArgTypes.BLOCK_POS] = pos
                    usingContext[ConiumEventArgTypes.BLOCK_STATE] = state
                    usingContext[ConiumEventArgTypes.BLOCK_HIT_RESULT] = hitResult
                },
                { result, usedContext ->
                    // The 'USED_BLOCK' context has an action result to acquire the result,
                    // this result is not cancelable or modifiable.
                    usedContext[ConiumEventArgTypes.ACTION_RESULT] = result
                },
                {
                    // To get the block used action result, give to 'USED_BLOCK' event context.
                    block.invokeOnUse(
                        state,
                        world,
                        pos,
                        player,
                        hitResult
                    )
                },
                // Return 'FAIL' when event 'USE_BLOCK' presaging was rejected the event.
                ActionResult.FAIL
            )
        }
    }
}