package com.github.cao.awa.conium.item.component.damage

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.component.withCreateAttributeModifiers
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.DAMAGE
import com.google.gson.JsonElement
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.Item

class BedrockDamageComponent(private val damage: Double) : ConiumItemTemplate(name = DAMAGE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockDamageComponent = BedrockDamageComponent(element.asDouble)
    }

    override fun settings(settings: Item.Properties) {
        settings.components.withComponent(
            DataComponents.ATTRIBUTE_MODIFIERS,
            withCreateAttributeModifiers(),
            withComputeAttributeModifiers()
        ) {
            // Add attack damage entry without base bound.
            it.add(
                ItemAttributeModifiers.Entry(
                    Attributes.ATTACK_DAMAGE,
                    AttributeModifier(
                        Item.BASE_ATTACK_DAMAGE_ID,
                        this.damage,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
            )
        }
    }
}
