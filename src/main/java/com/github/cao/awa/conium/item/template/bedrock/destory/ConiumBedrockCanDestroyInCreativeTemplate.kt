package com.github.cao.awa.conium.item.template.bedrock.destory

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(ConiumTemplates.BEDROCK_CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockCanDestroyInCreativeTemplate {
            return if (element.isJsonObject) {
                // Bedrock schema is:
                // "can_destroy_in_creative": {
                //     "value": <bool>
                // }
                ConiumBedrockCanDestroyInCreativeTemplate(element.asJsonObject["value"].asBoolean)
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "can_destroy_in_creative": <bool>
                ConiumBedrockCanDestroyInCreativeTemplate(element.asBoolean)
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }

    override fun attach(item: ConiumItem) {

    }

    override fun complete(item: ConiumItem) {
        item.canMinePredicate = { _, _, _, _, player -> !player.isCreative || this.canDestroy}
    }

    override fun settings(settings: Item.Settings) {

    }
}
