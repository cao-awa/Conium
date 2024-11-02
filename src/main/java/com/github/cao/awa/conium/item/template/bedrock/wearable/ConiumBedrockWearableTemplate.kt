package com.github.cao.awa.conium.item.template.bedrock.wearable

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumArmorTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumWearableTemplate
import com.github.cao.awa.conium.kotlin.extent.component.*
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.kotlin.extent.item.identifier
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.EquippableComponent
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryWrapper.WrapperLookup

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
class ConiumBedrockWearableTemplate(equipment: EquipmentType, protection: Double = 0.0) : ConiumWearableTemplate(ConiumTemplates.BEDROCK_WEARABLE, equipment, protection) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockWearableTemplate {
            return if (element.isJsonObject) {
                // Bedrock schema is:
                // "minecraft:wearable": {
                //     "protection": <int>
                //     "slot": <string>
                // }
                return element.asJsonObject.let {
                    ConiumBedrockWearableTemplate(
                        createEquipment(it["slot"].asString),
                        // And conium additional supporting missing protection key.
                        // Then default is no protection value.
                        it["protection"]?.asDouble ?: 0.0
                    )
                }
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:wearable": <string>
                ConiumBedrockWearableTemplate(createEquipment(element.asString))
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }
}
