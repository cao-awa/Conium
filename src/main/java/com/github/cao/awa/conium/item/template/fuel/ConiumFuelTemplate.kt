package com.github.cao.awa.conium.item.template.fuel

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.FUEL
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumFuelTemplate(private val duration: Int, name: String = FUEL) : ConiumItemTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumFuelTemplate = ConiumFuelTemplate(element.asInt)
    }

    override fun complete(item: ConiumItem) {
        Conium.coniumItemManager!!.addFuel(item, this.duration)
    }
}
