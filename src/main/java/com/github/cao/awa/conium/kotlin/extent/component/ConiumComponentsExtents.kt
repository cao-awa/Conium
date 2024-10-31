package com.github.cao.awa.conium.kotlin.extent.component

import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.registry.entry.RegistryEntry

/**
 * See [AttributeModifiersComponentBuilderMixin][com.github.cao.awa.conium.mixin.component.attribute.AttributeModifiersComponentBuilderMixin].
 */
val AttributeModifiersComponent.entries: MutableList<AttributeModifiersComponent.Entry> get() = this.modifiers as MutableList<AttributeModifiersComponent.Entry>

fun AttributeModifiersComponent.add(attribute: RegistryEntry<EntityAttribute>, modifier: EntityAttributeModifier, slot: AttributeModifierSlot) {
    this.entries.add(AttributeModifiersComponent.Entry(attribute, modifier, slot))
}
