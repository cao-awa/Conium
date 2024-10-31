package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import net.minecraft.block.Block

class ConiumBlock(settings: Settings) : Block(settings) {
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

}
