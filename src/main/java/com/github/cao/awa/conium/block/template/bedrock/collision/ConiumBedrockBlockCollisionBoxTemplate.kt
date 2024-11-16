package com.github.cao.awa.conium.block.template.bedrock.collision

import com.github.cao.awa.conium.block.template.collision.ConiumBlockCollisionTemplate
import com.github.cao.awa.conium.block.template.collision.ConiumBlockCollisionTemplate.Companion.createWithCubed
import com.github.cao.awa.conium.kotlin.extent.json.eachInt
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockBlock.COLLISION_BOX
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

object ConiumBedrockBlockCollisionBoxTemplate {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBlockCollisionTemplate = element.objectOrBoolean(
            {
                // Create the custom cubed block.
                createWithCubed(COLLISION_BOX) { cubed ->
                    // Push all int to cubed array.
                    it["origin"].asJsonArray.let {
                        // Bedrock base offset is '-8', make it be conium offset '0'.
                        cubed(it[0].asInt + 8)
                        cubed(it[1].asInt)
                        cubed(it[2].asInt + 8)
                    }
                    it["size"].asJsonArray.eachInt(cubed)
                }
            },
            // Create the full cubed block when origin and size not specified.
            { ConiumBlockCollisionTemplate(it, 0, 0, 0, 16, 16, 16, COLLISION_BOX) }
        )!!
}