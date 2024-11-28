package com.github.cao.awa.conium.item.template.glint

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
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

class ConiumGlintTemplate(private val glint: Boolean, private val script: (ItemStack) -> Unit = { }) : ConiumItemTemplate(name = GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumGlintTemplate = element.ifJsonObject({ json: JsonObject ->
            val glint: Boolean = json["default"].asBoolean

            val changer: (Any) -> Any? = Conium.scriptManager!!.acquireResult(json["script"].asString)

            val dynamicGlint: ConiumEventContext<ParameterSelective2<Boolean, Any, ItemStack>> = ConiumEventContextBuilder.unnamed(
                ConiumEventArgTypes.ITEM_STACK
            ) { identity: Any, stack: ItemStack ->
                println("Changing item stack: $stack")
                stack.mergedComponents[DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE] = changer(identity) as Boolean
            }

            Conium.scriptManager!!.acquire(json["script"].asString).attach(dynamicGlint)

            ConiumGlintTemplate(glint)
        }) {
            ConiumGlintTemplate(it.asBoolean)
        }!!
    }

    override fun settings(settings: Item.Settings) {
        // Set glint override.
        settings.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, this.glint)
    }
}
