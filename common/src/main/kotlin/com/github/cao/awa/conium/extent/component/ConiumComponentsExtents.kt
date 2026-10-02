package com.github.cao.awa.conium.kotlin.extent.component

import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.core.Holder

val ItemAttributeModifiers.entries: MutableList<ItemAttributeModifiers.Entry>
    get() = (this.modifiers() as? MutableList<ItemAttributeModifiers.Entry>) ?: ArrayList(this.modifiers())

fun ItemAttributeModifiers.add(
    attribute: Holder<Attribute>,
    modifier: AttributeModifier,
    slot: EquipmentSlotGroup
): ItemAttributeModifiers = this.withModifierAdded(attribute, modifier, slot)
