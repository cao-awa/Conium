package com.github.cao.awa.conium.item.template.glint

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.item.mergedComponents
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.parameter.ParameterSelective2
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GLINT
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumGlintTemplate(private val glint: Boolean, private val scriptName: String? = null) : ConiumItemTemplate(name = GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumGlintTemplate = element.ifJsonObject({ json: JsonObject ->
            ConiumGlintTemplate(json["default"].asBoolean, json["script"].asString)
        }) {
            ConiumGlintTemplate(it.asBoolean)
        }!!
    }

    override fun settings(settings: Item.Settings) {
        // Set glint override.
        settings.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, this.glint)
    }

    override fun complete(target: ConiumItem) {
        if (this.scriptName != null) {
            val changer: (Any) -> Any? = Conium.scriptManager!!.acquireResult(this.scriptName)

            val dynamicGlint: ConiumEventContext<ParameterSelective2<Boolean, Any, ItemStack>> = ConiumEventContextBuilder.unnamed(
                ConiumEventArgTypes.ITEM_STACK
            ) { identity: Any, stack: ItemStack ->
                if (stack.item != target) {
                    return@unnamed
                }
                stack.mergedComponents?.apply {
                    this[DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE] = changer(identity) as Boolean
                }
            }

            Conium.scriptManager!!.acquire(this.scriptName).attach(dynamicGlint)
        }
    }
}
