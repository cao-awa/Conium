package com.github.cao.awa.conium.item.template.armor

import com.github.cao.awa.conium.item.component.wearable.BedrockWearableComponent
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.ARMOR
import com.google.gson.JsonElement
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.resources.Identifier

/**
 * The template used to make an item can wear to slots and provides protections.
 *
 * This is conium schema template, for bedrock schema, see the template [ConiumArmorTemplate].
 *
 * @author cao_awa
 *
 * @see BedrockWearableComponent
 * @see ConiumWearableTemplate
 *
 * @since 1.0.0
 */
class ConiumArmorTemplate(
    equipment: ArmorType,
    defense: Double = 0.0,
    private val toughness: Double = 0.0,
    private val knockbackResistance: Double = 0.0,
    private val enchantmentValue: Int = 0
) : ConiumWearableTemplate(equipment, defense, ARMOR) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumArmorTemplate = element.objectOrString(
            {
                ConiumArmorTemplate(
                    createEquipment(it["slot"].asString),
                    // And conium additional supporting missing protection key.
                    // Then default is no protection value.
                    it["defense"]?.asDouble ?: 0.0,
                    it["toughness"]?.asDouble ?: 0.0,
                    it["knockback_resistance"]?.asDouble ?: 0.0,
                    it["enchantable"]?.asInt ?: 0
                )
            }
        ) {
            ConiumArmorTemplate(createEquipment(it))
        }!!
    }

    override fun settings(settings: Item.Properties) {
        // Add default settings.
        super.settings(settings)

        if (this.enchantmentValue > 0) {
            settings.enchantable(this.enchantmentValue)
        }
    }

    override fun computeAttributes(slot: EquipmentSlotGroup, identifier: Identifier, attributes: MutableList<ItemAttributeModifiers.Entry>) {
        // Add default attributes.
        super.computeAttributes(slot, identifier, attributes)

        // Add toughness attribute.
        attributes.add(
            ItemAttributeModifiers.Entry(
                Attributes.ARMOR_TOUGHNESS,
                AttributeModifier(
                    identifier,
                    this.toughness,
                    AttributeModifier.Operation.ADD_VALUE
                ),
                slot
            )
        )

        // Add knockback resistance attribute.
        attributes.add(
            ItemAttributeModifiers.Entry(
                Attributes.KNOCKBACK_RESISTANCE,
                AttributeModifier(
                    identifier,
                    this.knockbackResistance,
                    AttributeModifier.Operation.ADD_VALUE
                ),
                slot
            )
        )
    }
}
