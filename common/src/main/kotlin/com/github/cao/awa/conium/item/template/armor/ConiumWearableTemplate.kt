package com.github.cao.awa.conium.item.template.armor

import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.component.wearable.BedrockWearableComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.component.withCreateAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.kotlin.extent.item.identifier
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.EquippableComponent
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.util.Identifier

/**
 * The template used to make an item can wear to slots and provides the features.
 *
 * @author cao_awa
 *
 * @see ConiumArmorTemplate
 * @see BedrockWearableComponent
 *
 * @since 1.0.0
 */
abstract class ConiumWearableTemplate(
    private val equipment: EquipmentType,
    private val defense: Double,
    name: String
) : ConiumItemTemplate(name = name) {
    companion object {
        fun createEquipment(name: String): EquipmentType {
            return when (name) {
                // By bedrock keys.
                "slot.armor.head" -> EquipmentType.HELMET
                "slot.armor.chest" -> EquipmentType.CHESTPLATE
                "slot.armor.legs" -> EquipmentType.LEGGINGS
                "slot.armor.feet" -> EquipmentType.BOOTS
                "slot.weapon.offhand" -> illegalArgument("Not supporting equipment slot '$name' now")
                // By java keys.
                "helmet" -> EquipmentType.HELMET
                "chestplate" -> EquipmentType.CHESTPLATE
                "chest_plate" -> EquipmentType.CHESTPLATE
                "leggings" -> EquipmentType.LEGGINGS
                "boots" -> EquipmentType.BOOTS
                "body" -> EquipmentType.BODY
                // By shortname of bedrock keys.
                "head" -> EquipmentType.HELMET
                "chest" -> EquipmentType.CHESTPLATE
                "legs" -> EquipmentType.LEGGINGS
                "feet" -> EquipmentType.BOOTS
                else -> illegalArgument("Unknown equipment: '$name'")
            }
        }
    }

    override fun settings(settings: Item.Settings) {
        // Compute the attribute, add armor attribute.
        settings.components.withComponent(
            DataComponentTypes.ATTRIBUTE_MODIFIERS,
            withCreateAttributeModifiers(),
            withComputeAttributeModifiers()
        ) {
            // Get slot and identifier then compute the attributes.
            computeAttributes(
                AttributeModifierSlot.forEquipmentSlot(this.equipment.equipmentSlot),
                this.equipment.identifier,
                it
            )
        }

        // Let it can be equipment in target slot.
        settings.component(
            DataComponentTypes.EQUIPPABLE,
            EquippableComponent.builder(this.equipment.equipmentSlot).build()
        )
    }

    open fun computeAttributes(slot: AttributeModifierSlot, identifier: Identifier, attributes: MutableList<AttributeModifiersComponent.Entry>) {
        // Add armor attribute.
        attributes.add(
            AttributeModifiersComponent.Entry(
                EntityAttributes.ARMOR,
                EntityAttributeModifier(
                    identifier,
                    this.defense,
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                slot
            )
        )
    }
}