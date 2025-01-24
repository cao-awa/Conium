package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.path.through.ConiumBlockPathFindThroughTemplate
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class ConiumBlock(val setting: ConiumBlockSettings) : Block(setting.vanillaSettings), BlockEntityProvider {
    companion object {
        fun create(builder: ConiumBlockBuilder, settings: ConiumBlockSettings): ConiumBlock {
            builder.distinct()

            builder.forEachTemplate { it.prepare(settings) }

            return ConiumBlock(settings).apply {
                builder.forEachTemplate { it.attach(this) }

                builder.forEachTemplate { it.complete(this) }
            }
        }
    }

    /**
     * Make block outline shape.
     *
     * @param state the block state of this block
     * @param world the world where this block at
     * @param pos the block pos of this block
     * @param context the shape context of this block
     *
     * @see AbstractBlock.getOutlineShape
     * @see VoxelShape
     *
     * @return the outline shape of this block
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape = this.setting.outlineShape

    /**
     * When an entity pathing a way, return the through-able state of matched navigate type.
     *
     * Default through-able state is all ``false``.
     *
     * @param type the navigate type
     * @param state the block state
     *
     * @see AbstractBlock.AbstractBlockState.canPathfindThrough
     * @see ConiumBlockPathFindThroughTemplate
     *
     * @author cao_awa
     *
     * @return if an entity using navigation type ``type`` can navigate through this block
     *
     * @since 1.0.0
     */
    override fun canPathfindThrough(state: BlockState, type: NavigationType): Boolean {
        return when (type) {
            NavigationType.LAND -> this.setting.landPathThrough
            NavigationType.WATER -> this.setting.waterPathThrough
            NavigationType.AIR -> this.setting.airPathThrough
        }
    }

    /**
     * Create a Conium block entity with parsed settings, and got null when the block is not enabled block entity.
     *
     * @param pos the block pos of this block entity
     * @param state the block state of this block entity
     *
     * @see BlockEntity
     * @see ConiumBlockEntity
     * @see ConiumBlockEntitySettings
     *
     * @author cao_awa
     *
     * @return the block entity with parsed settings
     *
     * @since 1.0.0
     */
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
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
}
