package com.github.cao.awa.conium.item.template.fuel

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.FUEL
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

class ConiumFuelTemplate(private val duration: Int, name: String = FUEL) : ConiumItemTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumFuelTemplate = ConiumFuelTemplate(element.asInt)
    }

    override fun complete(target: ConiumItem) {
        Conium.coniumItemManager!!.addFuel(target, this.duration)
    }
}
