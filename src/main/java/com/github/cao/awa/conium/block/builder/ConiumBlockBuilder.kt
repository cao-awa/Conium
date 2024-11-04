package com.github.cao.awa.conium.block.builder

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.Identifier

abstract class ConiumBlockBuilder(val identifier: Identifier) {
    val templates: MutableList<ConiumBlockTemplate> get() = templates()

    abstract fun templates(): MutableList<ConiumBlockTemplate>

    fun addTemplates(templates: List<ConiumBlockTemplate>): ConiumBlockBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(settings: AbstractBlock.Settings): Block = ConiumBlock.create(this, settings)
}
