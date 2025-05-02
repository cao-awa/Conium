package com.github.cao.awa.conium.item.template.bedrock.wearable

import com.github.cao.awa.conium.item.template.armor.ConiumArmorTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumWearableTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.WEARABLE
import com.google.gson.JsonElement
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryWrapper

/**
 * The template used to make an item can wear to slots and provides protections.
 *
 * This is bedrock schema template, for conium schema, see the template [ConiumArmorTemplate].
 *
 * @author cao_awa
 *
 * @see ConiumArmorTemplate
 * @see ConiumWearableTemplate
 *
 * @since 1.0.0
 */
class ConiumBedrockWearableTemplate(equipment: EquipmentType, protection: Double = 0.0) : ConiumWearableTemplate(equipment, protection, WEARABLE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBedrockWearableTemplate = element.objectOrString(
            {
                // Bedrock schema is:
                // "minecraft:wearable": {
                //     "protection": <int>
                //     "slot": <string>
                // }
                ConiumBedrockWearableTemplate(
                    createEquipment(it["slot"].asString),
                    // And conium additional supporting missing protection key.
                    // Then default is no protection value.
                    it["protection"]?.asDouble ?: 0.0
                )
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:wearable": <string>
            ConiumBedrockWearableTemplate(createEquipment(it))
        }!!
    }
}
