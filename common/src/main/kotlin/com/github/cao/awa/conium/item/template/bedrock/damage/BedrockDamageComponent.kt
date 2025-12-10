package com.github.cao.awa.conium.item.template.bedrock.damage

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.component.withCreateAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.DAMAGE
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item

class BedrockDamageComponent(private val damage: Double) : ConiumItemTemplate(name = DAMAGE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockDamageComponent = BedrockDamageComponent(element.asDouble)
    }

    override fun settings(settings: Item.Settings) {
        settings.components.withComponent(
            DataComponentTypes.ATTRIBUTE_MODIFIERS,
            withCreateAttributeModifiers(),
            withComputeAttributeModifiers()
        ) {
            // Add attack damage entry without base bound.
            it.add(
                AttributeModifiersComponent.Entry(
                    EntityAttributes.ATTACK_DAMAGE,
                    EntityAttributeModifier(
                        Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                        this.damage,
                        EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.MAINHAND
                )
            )
        }
    }
}
