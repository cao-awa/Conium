package com.github.cao.awa.conium.item.builder

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumBuilderWithTemplates
import net.minecraft.util.Identifier

abstract class ConiumItemBuilder(val identifier: Identifier) : ConiumBuilderWithTemplates<
        ConiumItemBuilder,
        ConiumItemSettings,
        ConiumItem,
        ConiumItemTemplate>(
    ConiumItem::create
)
