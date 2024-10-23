package com.github.cao.awa.conium.block

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import net.minecraft.block.Block

class ConiumBlock(settings: Settings) : Block(settings) {
    companion object {
        fun create(builder: ConiumBlockBuilder): ConiumBlock {
            val settings = Settings.create()

            builder.templates.forEach {
                it.settings(settings)
            }

            val item = ConiumBlock(settings)

            builder.templates.forEach {
                it.attach(item)
            }

            builder.templates.forEach {
                it.complete(item)
            }

            return item
        }
    }

}
