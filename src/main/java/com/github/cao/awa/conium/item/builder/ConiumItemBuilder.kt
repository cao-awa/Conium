package com.github.cao.awa.conium.item.builder

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumBuilderWithTemplates
import net.minecraft.item.Item

abstract class ConiumItemBuilder : ConiumBuilderWithTemplates<
        ConiumItemBuilder,
        Item.Settings,
        Item,
        ConiumItemTemplate>(
    ConiumItem::create
)
