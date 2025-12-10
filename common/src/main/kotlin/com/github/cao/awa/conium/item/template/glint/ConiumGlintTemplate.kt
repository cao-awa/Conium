package com.github.cao.awa.conium.item.template.glint

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.item.mergedComponents
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.GLINT
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ConiumGlintTemplate(private val glint: Boolean, private val scriptName: String? = null) : ConiumItemTemplate(name = GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumGlintTemplate = element.ifJsonObject({ json: JsonObject ->
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
        // Only can be interaction when script defined.
        if (this.scriptName != null) {
            // Script manager must now have been initialized.
            Conium.scriptManager!!.also { scriptManager ->
                // The result acquirer.
                val acquirer: (Any) -> Any? = scriptManager.acquireResult(this.scriptName)

                // Acquire exported script and attach dynamic glint context.
                scriptManager.acquire(this.scriptName).attach(
                    ConiumEventContextBuilder.unnamed(
                        // Need an item stack to change glint.
                        ConiumEventArgTypes.ITEM_STACK
                    ) { identity: Any, stack: ItemStack ->
                        // The item must is current registering item, do not change other items.
                        if (stack.item == target) {
                            // Apply glint override when component map is MergedComponentMap.
                            stack.mergedComponents?.apply {
                                this[DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE] = acquirer(identity) as Boolean
                            }
                        }
                    }
                )
            }
        }
    }
}
