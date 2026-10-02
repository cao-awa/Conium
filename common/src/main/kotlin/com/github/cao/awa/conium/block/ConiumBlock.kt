package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.blockentity.ConiumBlockEntity
import com.github.cao.awa.conium.blockentity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.path.through.ConiumBlockPathFindThroughTemplate
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.item.ItemStack
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level

class ConiumBlock(val setting: ConiumBlockSettings) : Block(setting.vanillaSettings), EntityBlock {
    companion object {
        fun create(builder: ConiumBlockBuilder, settings: ConiumBlockSettings): ConiumBlock {
            // Remove duplicated templates.
            builder.distinct()

            // Build block.
            return ConiumBlock(settings).apply {
                // Attach block.
                builder.forEachTemplate { it.attach(this) }

                // Complete block.
                builder.forEachTemplate { it.complete(this) }

                // Build block entity.
                settings.blockEntity.also { blockEntitySettings: ConiumBlockEntitySettings ->
                    // Only do build if block has block entity templates,
                    for (blockEntityTemplate in blockEntitySettings.blockEntityTemplates) {
                        // Prepare block entity.
                        blockEntityTemplate.prepare(blockEntitySettings)

                        // Attach block entity.
                        blockEntityTemplate.attach(this)

                        // Complete block entity.
                        blockEntityTemplate.complete(this)
                    }
                }
            }
        }
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape = this.setting.outlineShape

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return when (type) {
            PathComputationType.LAND -> this.setting.landPathThrough
            PathComputationType.WATER -> this.setting.waterPathThrough
            PathComputationType.AIR -> this.setting.airPathThrough
        }
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        // Do not create the block entity when the flag is not enabled.
        if (!this.setting.enableBlockEntity) {
            return null
        }

        // Create block entity using parsed settings.
        return ConiumBlockEntity(
            this.setting.blockEntity,
            pos,
            state
        )
    }

    override fun isSignalSource(state: BlockState): Boolean = this.setting.emitsRedstonePower

    override fun getSignal(state: BlockState, world: BlockGetter, pos: BlockPos, direction: Direction): Int = this.setting.redstoneWeakPowerProvider(state, world, pos, direction)

    override fun getDirectSignal(state: BlockState, world: BlockGetter, pos: BlockPos, direction: Direction): Int = this.setting.redstoneStrongPowerProvider(state, world, pos, direction)

    override fun setPlacedBy(world: Level, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
        if (this.setting.emitsRedstonePower) {
            updateDirections(state, world, pos) { direction: Direction ->
                this.setting.redstoneStrongPowerProvider(state, world, pos, direction) > 0
            }
        }
    }

    fun updateDirections(state: BlockState, world: Level, pos: BlockPos, predicate: (Direction) -> Boolean) {
        Direction.entries.forEach { direction ->
            if (predicate(direction)) {
                world.updateNeighborsAt(pos.relative(direction), this)
            }
        }
    }
}
