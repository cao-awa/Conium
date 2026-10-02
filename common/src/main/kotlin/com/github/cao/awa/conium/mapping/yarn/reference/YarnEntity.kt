@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.server.MinecraftServer
import net.minecraft.world.damagesource.DamageSources
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityAttachments
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3

val Entity.world: Level get() = this.level()
val Entity.server: MinecraftServer? get() = this.server
val Entity.isAlive: Boolean get() = this.isAlive
val Entity.isOnFire: Boolean get() = this.isOnFire
val Entity.isSpectator: Boolean get() = this.isSpectator
val Entity.movement: Vec3 get() = this.deltaMovement
var Entity.air: Int
    get() = this.airSupply
    set(value) { this.airSupply = value }
val Entity.maxAir: Int get() = this.maxAirSupply
val Entity.attachments: EntityAttachments get() = this.attachments
val Entity.blockPos: BlockPos get() = this.blockPosition()
val Entity.blockStateAtPos: BlockState get() = this.blockStateOn
val Entity.blockX: Int get() = this.blockX
val Entity.blockY: Int get() = this.blockY
val Entity.blockZ: Int get() = this.blockZ
val Entity.bodyYaw: Float get() = this.visualRotationYInDegrees
val Entity.boundingBox: AABB get() = this.boundingBox
val Entity.chunkPos: ChunkPos get() = this.chunkPosition()
val Entity.commandTags: Set<String> get() = this.entityTags()
val Entity.controllingPassenger: LivingEntity? get() = this.controllingPassenger
val Entity.controllingVehicle: Entity? get() = this.controlledVehicle
val Entity.customName: Component? get() = this.customName
val Entity.damageSources: DamageSources get() = this.damageSources()
val Entity.defaultPortalCooldown: Int get() = this.dimensionChangingDelay
val Entity.displayName: Component? get() = this.displayName
val Entity.styledDisplayName: Component? get() = this.feedbackDisplayName
val Entity.entityWorld: Level get() = this.level()
val Entity.eyePos: Vec3 get() = this.eyePosition
val Entity.eyeY: Double get() = this.eyeY
val Entity.facing: Direction get() = this.direction
val Entity.fireTicks: Int get() = this.remainingFireTicks
val Entity.firstPassenger: Entity? get() = this.firstPassenger
val Entity.freezingScale: Float get() = this.percentFrozen
val Entity.frozenTicks: Int get() = this.ticksFrozen
val Entity.minFreezeDamageTicks: Int get() = this.ticksRequiredToFreeze
val Entity.finalGravity: Double get() = this.gravity
val Entity.id: Int get() = this.id
val Entity.vehicle: Entity? get() = this.vehicle
val Entity.velocity: Vec3 get() = this.deltaMovement
