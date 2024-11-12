package com.github.cao.awa.conium.kotlin.extent.entity

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry
import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.mixin.entity.EntityAccessor
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity.createLivingAttributes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

var Entity.dimensions: EntityDimensions
    get() = this.accessor.dimensions()
    set(value) = this.accessor.dimensions(value)

val Entity.accessor get() = this as EntityAccessor

fun ConiumEntityBuilder.register(callback: (ConiumEntityMetadata) -> Unit = { }) {
    build().also { entityType ->
        val type = registerEntity(this.identifier, entityType)
        callback(
            ConiumEntityMetadata(type, this.entitySettings)
        )
        ConiumEntityAttributeRegistry.attributes.computeIfAbsent(type) {
            createLivingAttributes().build()
        }
    }
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
