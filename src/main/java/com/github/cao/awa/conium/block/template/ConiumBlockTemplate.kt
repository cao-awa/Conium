package com.github.cao.awa.conium.block.template

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.template.ConiumTemplate
import net.minecraft.block.AbstractBlock

abstract class ConiumBlockTemplate(name: String) : ConiumTemplate<ConiumBlock, AbstractBlock.Settings>(name) {
    override fun attach(target: ConiumBlock) {
        // Do nothing.
    }

    override fun complete(target: ConiumBlock) {
        // Do nothing.
    }

    override fun prepare(target: AbstractBlock.Settings) {
        settings(target)
    }

    // Do not call settings directly.
    // Use 'prepare'.
    open fun settings(settings: AbstractBlock.Settings) {
        // Do nothing.
    }
}
