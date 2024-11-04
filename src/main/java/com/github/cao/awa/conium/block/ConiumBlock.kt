package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

class ConiumBlock(settings: Settings) : Block(settings), BlockColorProvider {
    companion object {
        fun create(builder: ConiumBlockBuilder, settings: Settings): ConiumBlock {
            builder.templates.forEach {
                it.settings(settings)
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

    var color: Int = 0

    override fun getColor(state: BlockState?, world: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int = this.color
}
