package com.github.cao.awa.conium.item.template.bedrock.entity.placer

import com.github.cao.awa.conium.item.template.entity.placer.ConiumEntityPlacerTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import java.util.Collections

class ConiumBedrockEntityPlacerTemplate(
    entity: EntityType<*>,
    useOn: MutableList<Block> = Collections.emptyList(),
    dispenseOn: MutableList<Block> = Collections.emptyList()
) : ConiumEntityPlacerTemplate(
    entity,
    useOn,
    dispenseOn
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBedrockEntityPlacerTemplate = element.objectOrString(
            { obj: JsonObject ->
                // Bedrock schema is:
                // "minecraft:entity_placer": {
                //     "entity": <string>,
                //     "use_on": [],
                //     "dispense_on": []
                // }
                ConiumBedrockEntityPlacerTemplate(
                    getEntityType(obj["entity"].asString),
                    obj["allowed_blocks"]?.let(ConiumEntityPlacerTemplate::getAllowedBlocks) ?: Collections.emptyList(),
                    obj["allowed_dispenser_blocks"]?.let(ConiumEntityPlacerTemplate::getAllowedBlocks) ?: Collections.emptyList()
                )
            }
        ) { identifier: String ->
            // Conium additional supporting schema:
            // "minecraft:entity_placer": <string>
            ConiumBedrockEntityPlacerTemplate(getEntityType(identifier))
        }!!
    }
}
