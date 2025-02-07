@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*

/**
 * See the mapping [Entity](https://mappings.dev/1.21.4/net/minecraft/world/entity/Entity.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val Entity.world: World by Entity::world
val Entity.server: MinecraftServer? by Entity::server
val Entity.isAlive: Boolean
    get() = this.isAlive
val Entity.isOnFire: Boolean
    get() = this.isOnFire
val Entity.isSpectator: Boolean
    get() = this.isSpectator
val Entity.movement: Vec3d by Entity::movement
val Entity.air: Int by Entity::air
val Entity.maxAir: Int by Entity::maxAir
val Entity.attachments: EntityAttachments by Entity::attachments
val Entity.blockPos: BlockPos by Entity::blockPos
val Entity.blockStateAtPos: BlockState by Entity::blockStateAtPos
val Entity.blockX: Int by Entity::blockX
val Entity.blockY: Int by Entity::blockY
val Entity.blockZ: Int by Entity::blockZ
val Entity.bodyYaw: Float by Entity::bodyYaw
val Entity.boundingBox: Box by Entity::boundingBox
val Entity.chunkPos: ChunkPos by Entity::chunkPos
val Entity.commandTags: Set<String> by Entity::commandTags
val Entity.controllingPassenger: LivingEntity? by Entity::controllingPassenger
val Entity.controllingVehicle: Entity? by Entity::controllingVehicle
val Entity.customName: Text? by Entity::customName
val Entity.damageSources: DamageSources by Entity::damageSources
val Entity.defaultPortalCooldown: Int by Entity::defaultPortalCooldown
val Entity.displayName: Text? by Entity::displayName
val Entity.styledDisplayName: Text? by Entity::styledDisplayName
val Entity.entityWorld: World by Entity::entityWorld
val Entity.eyePos: Vec3d by Entity::eyePos
val Entity.eyeY: Double by Entity::eyeY
val Entity.facing: Direction by Entity::facing
val Entity.fireTicks: Int by Entity::fireTicks
val Entity.firstPassenger: Entity? by Entity::firstPassenger
val Entity.freezingScale: Float by Entity::freezingScale
val Entity.frozenTicks: Int by Entity::frozenTicks
val Entity.minFreezeDamageTicks: Int by Entity::minFreezeDamageTicks
val Entity.finalGravity: Double by Entity::finalGravity
val Entity.id: Int by Entity::id
val Entity.vehicle: Entity? by Entity::vehicle
val Entity.velocity: Vec3d by Entity::velocity
