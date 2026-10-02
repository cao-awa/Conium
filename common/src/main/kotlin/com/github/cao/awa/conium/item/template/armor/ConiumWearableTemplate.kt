package com.github.cao.awa.conium.item.template.armor

import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.component.wearable.BedrockWearableComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.component.withCreateAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.item.components
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.Equippable
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.Item
import net.minecraft.resources.Identifier

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
    private val equipment: ArmorType,
    private val defense: Double,
    name: String
) : ConiumItemTemplate(name = name) {
    companion object {
        fun createEquipment(name: String): ArmorType {
            return when (name) {
                // By bedrock keys.
                "slot.armor.head" -> ArmorType.HELMET
                "slot.armor.chest" -> ArmorType.CHESTPLATE
                "slot.armor.legs" -> ArmorType.LEGGINGS
                "slot.armor.feet" -> ArmorType.BOOTS
                "slot.weapon.offhand" -> illegalArgument("Not supporting equipment slot '$name' now")
                // By java keys.
                "helmet" -> ArmorType.HELMET
                "chestplate" -> ArmorType.CHESTPLATE
                "chest_plate" -> ArmorType.CHESTPLATE
                "leggings" -> ArmorType.LEGGINGS
                "boots" -> ArmorType.BOOTS
                "body" -> ArmorType.BODY
                // By shortname of bedrock keys.
                "head" -> ArmorType.HELMET
                "chest" -> ArmorType.CHESTPLATE
                "legs" -> ArmorType.LEGGINGS
                "feet" -> ArmorType.BOOTS
                else -> illegalArgument("Unknown equipment: '$name'")
            }
        }
    }

    override fun settings(settings: Item.Properties) {
        // Compute the attribute, add armor attribute.
        settings.components.withComponent(
            DataComponents.ATTRIBUTE_MODIFIERS,
            withCreateAttributeModifiers(),
            withComputeAttributeModifiers()
        ) {
            // Get slot and identifier then compute the attributes.
            computeAttributes(
                EquipmentSlotGroup.bySlot(this.equipment.slot),
                Identifier.withDefaultNamespace("armor." + this.equipment.serializedName),
                it
            )
        }

        // Let it can be equipment in target slot.
        settings.component(
            DataComponents.EQUIPPABLE,
            Equippable.builder(this.equipment.slot).build()
        )
    }

    open fun computeAttributes(slot: EquipmentSlotGroup, identifier: Identifier, attributes: MutableList<ItemAttributeModifiers.Entry>) {
        // Add armor attribute.
        attributes.add(
            ItemAttributeModifiers.Entry(
                Attributes.ARMOR,
                AttributeModifier(
                    identifier,
                    this.defense,
                    AttributeModifier.Operation.ADD_VALUE
                ),
                slot
            )
        )
    }
}
