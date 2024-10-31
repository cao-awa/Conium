package com.github.cao.awa.conium.item.template

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.mixin.item.setting.ItemSettingsAccessor
import com.github.cao.awa.conium.template.ConiumTemplate
import net.minecraft.component.ComponentMap
import net.minecraft.item.Item

abstract class ConiumItemTemplate(name: String) : ConiumTemplate<ConiumItem>(name) {
    abstract fun settings(settings: Item.Settings)
}
