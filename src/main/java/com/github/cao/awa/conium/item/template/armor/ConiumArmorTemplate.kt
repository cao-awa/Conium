package com.github.cao.awa.conium.item.template.armor

import com.github.cao.awa.conium.item.template.bedrock.wearable.ConiumBedrockWearableTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.ConiumTemplates.Item.ARMOR
import com.google.gson.JsonElement
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Identifier

/**
 * The template used to make an item can wear to slots and provides protections.
 *
 * This is conium schema template, for bedrock schema, see the template [ConiumArmorTemplate].
 *
 * @author cao_awa
 *
 * @see ConiumBedrockWearableTemplate
 * @see ConiumWearableTemplate
 *
 * @since 1.0.0
 */
class ConiumArmorTemplate(
    equipment: EquipmentType,
    defense: Double = 0.0,
    private val toughness: Double = 0.0,
    private val knockbackResistance: Double = 0.0,
    private val enchantmentValue: Int = 0
) : ConiumWearableTemplate(ARMOR, equipment, defense) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumArmorTemplate = element.objectOrString(
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

    override fun settings(settings: Item.Settings) {
        // Add default settings.
        super.settings(settings)

        if (this.enchantmentValue > 0) {
            settings.enchantable(this.enchantmentValue)
        }
    }

    override fun computeAttributes(slot: AttributeModifierSlot, identifier: Identifier, attributes: MutableList<AttributeModifiersComponent.Entry>) {
        // Add default attributes.
        super.computeAttributes(slot, identifier, attributes)

        // Add toughness attribute.
        attributes.add(
            AttributeModifiersComponent.Entry(
                EntityAttributes.ARMOR_TOUGHNESS,
                EntityAttributeModifier(
                    identifier,
                    this.toughness,
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                slot
            )
        )

        // Add knockback resistance attribute.
        attributes.add(
            AttributeModifiersComponent.Entry(
                EntityAttributes.KNOCKBACK_RESISTANCE,
                EntityAttributeModifier(
                    identifier,
                    this.knockbackResistance,
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                slot
            )
        )
    }
}
