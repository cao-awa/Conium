package com.github.cao.awa.conium.kotlin.extent.entity

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

fun ConiumEntityBuilder.register(callback: (EntityType<ConiumEntity>) -> Unit = { }) {
    callback(registerEntity(this.identifier, build()))
}

private fun keyOf(id: Identifier): RegistryKey<EntityType<*>> {
    return RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)
}

fun <T : Entity> registerEntity(id: Identifier, type: EntityType.Builder<T>): EntityType<T> {
    return registerEntity(keyOf(id), type)
}

fun <T : Entity> registerEntity(key: RegistryKey<EntityType<*>>, type: EntityType.Builder<T>): EntityType<T> {
    return Registry.register(Registries.ENTITY_TYPE, key, type.build(key))
}
