package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.path.through.ConiumBlockPathFindThroughTemplate
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class ConiumBlock(private val setting: ConiumBlockSettings) : Block(setting.vanillaSettings) {
    companion object {
        fun create(builder: ConiumBlockBuilder, settings: ConiumBlockSettings): ConiumBlock {
            builder.templates.forEach { it.prepare(settings) }

            return ConiumBlock(settings).apply {
                builder.templates.forEach { it.attach(this) }

                builder.templates.forEach { it.complete(this) }
            }
        }
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape = this.setting.outlineShape

    /**
     * When an entity pathing a way, return the through-able state of matched navigate type.
     *
     * Default through-able state is all ``false``.
     *
     * @param type the navigate type
     * @param state the block state
     *
     * @see AbstractBlockState.canPathfindThrough
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
}
