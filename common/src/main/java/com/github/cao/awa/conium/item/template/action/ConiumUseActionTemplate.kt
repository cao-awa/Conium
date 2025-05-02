package com.github.cao.awa.conium.item.template.action

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponentProvides
import com.github.cao.awa.conium.kotlin.extent.component.withComputeUseAction
import com.github.cao.awa.conium.kotlin.extent.component.withCreateConsumable
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.template.ConiumTemplates.Item.USE_ACTION
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.consume.UseAction
import net.minecraft.registry.RegistryWrapper

class ConiumUseActionTemplate(private val useAction: UseAction) : ConiumItemTemplate(name = USE_ACTION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumUseActionTemplate = ConiumUseActionTemplate(createUseAction(element.asString))
    }

    override fun settings(settings: Item.Settings) {
        // Set use action.
        settings.components.withComponentProvides(
            DataComponentTypes.CONSUMABLE,
            withCreateConsumable(),
            withComputeUseAction(),
            this::useAction
        )
    }

    override fun complete(target: ConiumItem) {
        target.useAction = this.useAction
    }
}
