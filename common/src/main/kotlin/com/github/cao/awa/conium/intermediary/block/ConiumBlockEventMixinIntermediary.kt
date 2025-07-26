package com.github.cao.awa.conium.intermediary.block

import com.github.cao.awa.conium.block.event.breaking.ConiumBreakBlockEvent
import com.github.cao.awa.conium.block.event.breaking.ConiumBreakingBlockEventMetadata
import com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEvent
import com.github.cao.awa.conium.block.event.place.ConiumPlacedBlockEvent
import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEvent
import com.github.cao.awa.conium.block.event.use.ConiumUsedBlockEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary
import com.github.cao.awa.conium.kotlin.extent.block.invokeOnUse
import com.github.cao.awa.conium.mixin.block.BlockItemMixin
import com.github.cao.awa.conium.mixin.block.BlockStateMixin
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
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
        fun fireBlockBreakingEvent(eventType: ConiumEventType<Block, ConiumBreakingBlockEventMetadata>, state: BlockState, world: World, player: PlayerEntity, pos: BlockPos): Boolean {
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

        /**
         * Trigger the block place event when the block placing.
         *
         * @see Block
         * @see BlockState
         * @see ConiumEventType.PLACE_BLOCK
         * @see ConiumPlaceBlockEvent
         * @see BlockItemMixin.placeBlock
         *
         * @param block the placing block
         * @param placementContext the item placement context
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         * @author 草二号机
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun firePlaceBlockEvent(block: Block, placementContext: ItemPlacementContext): Boolean {
            return ConiumEventMixinIntermediary.fireEventCancelable(
                ConiumEventType.PLACE_BLOCK,
                block
            ) { placeBlockContext ->
                // Fill the context args.
                placeBlockContext[ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT] = placementContext
            }
        }

        /**
         * Trigger the block placed event when the block placed.
         *
         * @see Block
         * @see BlockState
         * @see ConiumEventType.PLACED_BLOCK
         * @see ConiumPlacedBlockEvent
         * @see BlockItemMixin.placedBlock
         *
         * @param state the placed block state
         * @param world the world where the block is
         * @param placer the block placer
         * @param pos the position where the block is
         * @param itemStack the item stack that placing this block
         *
         * @author cao_awa
         * @author 草二号机
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun firePlacedBlockEvent(state: BlockState, world: World, placer: LivingEntity, pos: BlockPos, itemStack: ItemStack) {
            val block: Block = state.block

            ConiumEventMixinIntermediary.fireEventIntermediary(
                ConiumEventType.PLACED_BLOCK,
                block,
                { placeBlockContext ->
                    // Fill the context args.
                    placeBlockContext[ConiumEventArgTypes.WORLD] = world
                    placeBlockContext[ConiumEventArgTypes.LIVING_ENTITY] = placer
                    placeBlockContext[ConiumEventArgTypes.BLOCK_POS] = pos
                    placeBlockContext[ConiumEventArgTypes.BLOCK_STATE] = state
                    placeBlockContext[ConiumEventArgTypes.ITEM_STACK] = itemStack
                }
            ) {
                // Only presaging state is true can be continued.
                block.onPlaced(world, pos, state, placer, itemStack)
            }
        }

        /**
         * Trigger the block broken event when the block destroyed.
         *
         * @see Block
         * @see BlockState
         * @see ConiumEventType.PLACE_BLOCK
         * @see ConiumPlaceBlockEvent
         * @see BlockItemMixin.placeBlock
         *
         * @param block the placing block
         * @param world the world where the block is
         * @param pos the position where the block is
         * @param state the broken block state
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         * @author 草二号机
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireBlockBrokenEvent(block: Block, world: World, pos: BlockPos, state: BlockState) {
            ConiumEventMixinIntermediary.fireEventIntermediary(
                ConiumEventType.BROKEN_BLOCK,
                block,
                { placeBlockContext ->
                    // Fill the context args.
                    placeBlockContext[ConiumEventArgTypes.WORLD] = world
                    placeBlockContext[ConiumEventArgTypes.BLOCK_POS] = pos
                    placeBlockContext[ConiumEventArgTypes.BLOCK_STATE] = state
                }
            ) {
                block.onBroken(world, pos, state)
            }
        }
    }
}
