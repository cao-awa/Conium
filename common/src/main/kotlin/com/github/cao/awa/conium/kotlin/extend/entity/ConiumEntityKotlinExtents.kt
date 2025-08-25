package com.github.cao.awa.conium.kotlin.extend.entity

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry
import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.mixin.entity.EntityAccessor
import com.github.cao.awa.conium.raycast.ConiumRaycast
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity.createLivingAttributes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d

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

private fun keyOf(id: Identifier): RegistryKey<EntityType<*>> = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)

fun <T : Entity> registerEntity(id: Identifier, type: EntityType.Builder<T>): EntityType<T> = registerEntity(keyOf(id), type)

fun <T : Entity> registerEntity(key: RegistryKey<EntityType<*>>, type: EntityType.Builder<T>): EntityType<T> = Registry.register(Registries.ENTITY_TYPE, key, type.build(key))

fun Entity.teleportWithRaycast(serverWorld: ServerWorld, maxDistance: Double, tickDelta: Float, includeFluid: Boolean, includePassableBlocks: Boolean) {
    // Do raycast.
    ConiumRaycast.raycast(
        this,
        maxDistance,
        tickDelta,
        includeFluid,
        includePassableBlocks
    ).let { hitResult ->
        // Don't do teleport if hit missing.
        if ( hitResult.type == HitResult.Type.MISS) {
            return@let
        }

        // Do teleport.
        val pos: Vec3d = hitResult.pos
        this.teleport(
            serverWorld,
            pos.x,
            pos.y,
            pos.z,
            mutableSetOf(),
            this.yaw,
            this.pitch,
            false
        )
    }
}