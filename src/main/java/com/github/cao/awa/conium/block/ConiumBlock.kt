package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockRenderView
import net.minecraft.world.BlockView

class ConiumBlock(private val setting: ConiumBlockSettings) : Block(setting.vanillaSettings), BlockColorProvider {
    companion object {
        fun create(builder: ConiumBlockBuilder, settings: ConiumBlockSettings): ConiumBlock {
            builder.templates.forEach {
                it.prepare(settings)
            }

            val block = ConiumBlock(settings)

            builder.templates.forEach {
                it.attach(block)
            }

            builder.templates.forEach {
                it.complete(block)
            }

            return block
        }
    }

    override fun getColor(state: BlockState?, world: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int = this.setting.color

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape = this.setting.outlineShape

    override fun canPathfindThrough(state: BlockState, type: NavigationType): Boolean {
        return when (type) {
            NavigationType.LAND -> this.setting.landPathThrough
            NavigationType.WATER -> this.setting.waterPathThrough
            NavigationType.AIR -> this.setting.airPathThrough
        }
    }
}
