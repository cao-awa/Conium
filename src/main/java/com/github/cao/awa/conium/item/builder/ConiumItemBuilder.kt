package com.github.cao.awa.conium.item.builder

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.item.Item

interface ConiumItemBuilder {
    val templates: MutableList<ConiumItemTemplate> get() = templates()

    fun templates(): MutableList<ConiumItemTemplate>

    fun addTemplates(templates: List<ConiumItemTemplate>): ConiumItemBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(settings: Item.Settings): Item {
        return ConiumItem.create(this, settings)
    }
}
