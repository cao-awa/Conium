package com.github.cao.awa.conium.item.template.bedrock.destory

import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.google.gson.JsonElement

class BedrockCanDestroyInCreativeComponent(canDestroy: Boolean) : ConiumCanDestroyInCreativeTemplate(canDestroy) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockCanDestroyInCreativeComponent = element.objectOrBoolean(
            {
                // Bedrock schema is:
                // "minecraft:can_destroy_in_creative": {
                //     "value": <bool>
                // }
                BedrockCanDestroyInCreativeComponent(it["value"].asBoolean)
            },
            // Conium additional supporting schema:
            // "minecraft:can_destroy_in_creative": <bool>
            ::BedrockCanDestroyInCreativeComponent
        )!!
    }
}
