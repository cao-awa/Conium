package com.github.cao.awa.conium.intermediary.fluid

import com.github.cao.awa.conium.block.event.tick.ConiumBlockScheduleTickEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import net.minecraft.fluid.Fluid
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary
import net.minecraft.block.BlockState
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object ConiumFluidEventMixinIntermediary {
    /**
     * Trigger the fluid schedule tick event.
     *
     * @see Fluid.onScheduledTick
     * @see ConiumEventType.BLOCK_SCHEDULE_TICK
     * @see ConiumEventType.BLOCK_SCHEDULE_TICKED
     * @see ConiumBlockScheduleTickEvent
     *
     * @param state the block state
     * @param world the world where the fluid (block) is
     * @param pos the position where the fluid (block) is
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireFluidScheduleTickEvent(state: FluidState, world: World, pos: BlockPos, blockState: BlockState): Boolean {
        return ConiumEventMixinIntermediary.fireInheritedCascadedEvent(
            ConiumEventType.FLUID_SCHEDULE_TICK,
            ConiumEventType.FLUID_SCHEDULE_TICKED,
            state.fluid,
            { blockScheduledTickContext ->
                // Fill the context args.
                blockScheduledTickContext[ConiumEventArgTypes.WORLD] = world
                blockScheduledTickContext[ConiumEventArgTypes.SCHEDULED_TICK_VIEW] = world
                blockScheduledTickContext[ConiumEventArgTypes.BLOCK_POS] = pos
                blockScheduledTickContext[ConiumEventArgTypes.BLOCK_STATE] = blockState
                blockScheduledTickContext[ConiumEventArgTypes.FLUID] = state.fluid
                blockScheduledTickContext[ConiumEventArgTypes.FLUID_STATE] = state
            }
        ) { blockScheduledTickContext ->
            // Inherited from event 'BLOCK_SCHEDULE_TICKED' context has a metadata to acquire the metadata,
        }
    }
}
