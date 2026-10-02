package com.github.cao.awa.conium.kotlin.extent.entity

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry
import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.mixin.entity.EntityAccessor
import com.github.cao.awa.conium.raycast.ConiumRaycast
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity.createLivingAttributes
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.resources.Identifier
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3

var Entity.dimensions: EntityDimensions
    get() = this.accessor.dimensions()
    set(value) = this.accessor.dimensions(value)

val Entity.accessor: EntityAccessor get() = this as EntityAccessor

fun ConiumEntityBuilder.register(callback: (ConiumEntityMetadata) -> Unit = { }) {
    build().also { builder: EntityType.Builder<ConiumEntity> ->
        val type: EntityType<ConiumEntity> = registerEntity(this.identifier, builder)
        callback(
            ConiumEntityMetadata(type, this.entitySettings)
        )
        ConiumEntityAttributeRegistry.attributes.computeIfAbsent(type) {
            createLivingAttributes().build()
        }
    }
}

private fun keyOf(id: Identifier): ResourceKey<EntityType<*>> = ResourceKey.create(Registries.ENTITY_TYPE, id)

fun <T : Entity> registerEntity(id: Identifier, type: EntityType.Builder<T>): EntityType<T> = registerEntity(keyOf(id), type)

fun <T : Entity> registerEntity(key: ResourceKey<EntityType<*>>, type: EntityType.Builder<T>): EntityType<T> = Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key))

fun Entity.teleportWithRaycast(serverWorld: ServerLevel, maxDistance: Double, tickDelta: Float, includeFluid: Boolean, includePassableBlocks: Boolean) {
    // Do raycast.
    ConiumRaycast.raycast(
        this,
        maxDistance,
        tickDelta,
        includeFluid,
        includePassableBlocks
    ).let { hitResult ->
        // Don't do teleport if hit missing.
        if (hitResult.type == HitResult.Type.MISS) {
            return@let
        }

        // Do teleport.
        val pos: Vec3 = hitResult.location
        this.teleportTo(
            serverWorld,
            pos.x,
            pos.y,
            pos.z,
            emptySet(),
            this.yRot,
            this.xRot,
            false
        )
    }
}
