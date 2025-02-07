@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import java.util.Optional

/**
 * See the mapping [ServerPlayerEntity](https://mappings.dev/1.21.4/net/minecraft/server/level/ServerPlayer.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val ServerPlayerEntity.serverWorld: ServerWorld by ServerPlayerEntity::serverWorld
val ServerPlayerEntity.commandSource: ServerCommandSource by ServerPlayerEntity::commandSource
val ServerPlayerEntity.commandOutput: CommandOutput by ServerPlayerEntity::commandOutput
val ServerPlayerEntity.advancementTracker: PlayerAdvancementTracker by ServerPlayerEntity::advancementTracker
val ServerPlayerEntity.cameraEntity: Entity by ServerPlayerEntity::cameraEntity
val ServerPlayerEntity.chunkFilter: ChunkFilter by ServerPlayerEntity::chunkFilter
val ServerPlayerEntity.clientChatVisibility: ChatVisibility by ServerPlayerEntity::clientChatVisibility
val ServerPlayerEntity.clientOptions: SyncedClientOptions by ServerPlayerEntity::clientOptions
val ServerPlayerEntity.enderPearls: Set<EnderPearlEntity> by ServerPlayerEntity::enderPearls
val ServerPlayerEntity.inputVelocityForMinecart: Vec3d by ServerPlayerEntity::inputVelocityForMinecart
val ServerPlayerEntity.ip: String by ServerPlayerEntity::ip
val ServerPlayerEntity.isDisconnected: Boolean
    get() = this.isDisconnected
val ServerPlayerEntity.isInTeleportationState: Boolean
    get() = this.isInTeleportationState
val ServerPlayerEntity.isSpawnForced: Boolean
    get() = this.isSpawnForced
val ServerPlayerEntity.lastActionTime: Long by ServerPlayerEntity::lastActionTime
val ServerPlayerEntity.playerInput: PlayerInput by ServerPlayerEntity::playerInput
val ServerPlayerEntity.playerListName: Text? by ServerPlayerEntity::playerListName
val ServerPlayerEntity.playerListOrder: Int by ServerPlayerEntity::playerListOrder
val ServerPlayerEntity.recipeBook: RecipeBook by ServerPlayerEntity::recipeBook
val ServerPlayerEntity.session: PublicPlayerSession? by ServerPlayerEntity::session
val ServerPlayerEntity.spawnAngle: Float by ServerPlayerEntity::spawnAngle
val ServerPlayerEntity.spawnPointDimension: RegistryKey<World> by ServerPlayerEntity::spawnPointDimension
val ServerPlayerEntity.spawnPointPosition: BlockPos? by ServerPlayerEntity::spawnPointPosition
val ServerPlayerEntity.interactionManager: ServerPlayerInteractionManager by ServerPlayerEntity::interactionManager
val ServerPlayerEntity.networkHandler: ServerPlayNetworkHandler by ServerPlayerEntity::networkHandler
val ServerPlayerEntity.notInAnyWorld: Boolean by ServerPlayerEntity::notInAnyWorld
val ServerPlayerEntity.seenCredits: Boolean by ServerPlayerEntity::seenCredits
val ServerPlayerEntity.startRaidPos: BlockPos? by ServerPlayerEntity::startRaidPos
val ServerPlayerEntity.statHandler: ServerStatHandler by ServerPlayerEntity::statHandler
val ServerPlayerEntity.textStream: TextStream by ServerPlayerEntity::textStream
val ServerPlayerEntity.viewDistance: Int by ServerPlayerEntity::viewDistance
val ServerPlayerEntity.watchedSection: ChunkSectionPos by ServerPlayerEntity::watchedSection
val ServerPlayerEntity.isOnFire: Boolean
    get() = this.isOnFire
val ServerPlayerEntity.abilities: PlayerAbilities by ServerPlayerEntity::abilities
val ServerPlayerEntity.absorptionAmount: Float by ServerPlayerEntity::absorptionAmount
val ServerPlayerEntity.activeHand: Hand by ServerPlayerEntity::activeHand
val ServerPlayerEntity.activeItem: ItemStack by ServerPlayerEntity::activeItem
val ServerPlayerEntity.activeStatusEffects: Map<RegistryEntry<StatusEffect>, StatusEffectInstance> by ServerPlayerEntity::activeStatusEffects
val ServerPlayerEntity.allArmorItems: Iterable<ItemStack> by ServerPlayerEntity::allArmorItems
val ServerPlayerEntity.armorItems: Iterable<ItemStack> by ServerPlayerEntity::armorItems
val ServerPlayerEntity.armor: Int by ServerPlayerEntity::armor
val ServerPlayerEntity.mainArm: Arm by ServerPlayerEntity::mainArm
val ServerPlayerEntity.armorVisibility: Float by ServerPlayerEntity::armorVisibility
val ServerPlayerEntity.attacker: LivingEntity? by ServerPlayerEntity::attacker
val ServerPlayerEntity.attacking: LivingEntity? by ServerPlayerEntity::attacking
val ServerPlayerEntity.attributes: AttributeContainer by ServerPlayerEntity::attributes
val ServerPlayerEntity.blockingItem: ItemStack? by ServerPlayerEntity::blockingItem
val ServerPlayerEntity.permissionLevel: Int by ServerPlayerEntity::permissionLevel
val ServerPlayerEntity.brain: Brain<*> by ServerPlayerEntity::brain
val ServerPlayerEntity.isCreative: Boolean
    get() = this.isCreative
val ServerPlayerEntity.isSpectator: Boolean
    get() = this.isSpectator
val ServerPlayerEntity.movement: Vec3d by ServerPlayerEntity::movement
val ServerPlayerEntity.movementSpeed: Float by ServerPlayerEntity::movementSpeed
val ServerPlayerEntity.air: Int by ServerPlayerEntity::air
val ServerPlayerEntity.maxAir: Int by ServerPlayerEntity::maxAir
val ServerPlayerEntity.attachments: EntityAttachments by ServerPlayerEntity::attachments
val ServerPlayerEntity.attackCooldownProgressPerTick: Float by ServerPlayerEntity::attackCooldownProgressPerTick
val ServerPlayerEntity.blockInteractionRange: Double by ServerPlayerEntity::blockInteractionRange
val ServerPlayerEntity.blockPos: BlockPos by ServerPlayerEntity::blockPos
val ServerPlayerEntity.blockStateAtPos: BlockState by ServerPlayerEntity::blockStateAtPos
val ServerPlayerEntity.blockX: Int by ServerPlayerEntity::blockX
val ServerPlayerEntity.blockY: Int by ServerPlayerEntity::blockY
val ServerPlayerEntity.blockZ: Int by ServerPlayerEntity::blockZ
val ServerPlayerEntity.bodyYaw: Float by ServerPlayerEntity::bodyYaw
val ServerPlayerEntity.boundingBox: Box by ServerPlayerEntity::boundingBox
val ServerPlayerEntity.chunkPos: ChunkPos by ServerPlayerEntity::chunkPos
val ServerPlayerEntity.climbingPos: Optional<BlockPos> by ServerPlayerEntity::climbingPos
val ServerPlayerEntity.commandTags: Set<String> by ServerPlayerEntity::commandTags
val ServerPlayerEntity.controllingPassenger: LivingEntity? by ServerPlayerEntity::controllingPassenger
val ServerPlayerEntity.controllingVehicle: Entity? by ServerPlayerEntity::controllingVehicle
val ServerPlayerEntity.customName: Text? by ServerPlayerEntity::customName
val ServerPlayerEntity.damageSources: DamageSources by ServerPlayerEntity::damageSources
val ServerPlayerEntity.damageTracker: DamageTracker by ServerPlayerEntity::damageTracker
val ServerPlayerEntity.damageTiltYaw: Float by ServerPlayerEntity::damageTiltYaw
val ServerPlayerEntity.defaultPortalCooldown: Int by ServerPlayerEntity::defaultPortalCooldown
val ServerPlayerEntity.despawnCounter: Int by ServerPlayerEntity::despawnCounter
val ServerPlayerEntity.displayName: Text? by ServerPlayerEntity::displayName
val ServerPlayerEntity.styledDisplayName: Text? by ServerPlayerEntity::styledDisplayName
val ServerPlayerEntity.enchantingTableSeed: Int by ServerPlayerEntity::enchantingTableSeed
val ServerPlayerEntity.enderChestInventory: EnderChestInventory by ServerPlayerEntity::enderChestInventory
val ServerPlayerEntity.entityInteractionRange: Double by ServerPlayerEntity::entityInteractionRange
val ServerPlayerEntity.entityWorld: World by ServerPlayerEntity::entityWorld
val ServerPlayerEntity.equippedItems: Iterable<ItemStack> by ServerPlayerEntity::equippedItems
val ServerPlayerEntity.eyePos: Vec3d by ServerPlayerEntity::eyePos
val ServerPlayerEntity.eyeY: Double by ServerPlayerEntity::eyeY
val ServerPlayerEntity.facing: Direction by ServerPlayerEntity::facing
val ServerPlayerEntity.fireTicks: Int by ServerPlayerEntity::fireTicks
val ServerPlayerEntity.firstPassenger: Entity? by ServerPlayerEntity::firstPassenger
val ServerPlayerEntity.freezingScale: Float by ServerPlayerEntity::freezingScale
val ServerPlayerEntity.frozenTicks: Int by ServerPlayerEntity::frozenTicks
val ServerPlayerEntity.minFreezeDamageTicks: Int by ServerPlayerEntity::minFreezeDamageTicks
val ServerPlayerEntity.finalGravity: Double by ServerPlayerEntity::finalGravity
val ServerPlayerEntity.glidingTicks: Int by ServerPlayerEntity::glidingTicks
val ServerPlayerEntity.isGliding: Boolean
    get() = this.isGliding
val ServerPlayerEntity.id: Int by ServerPlayerEntity::id
val ServerPlayerEntity.mainHandStack: ItemStack by ServerPlayerEntity::mainHandStack
val ServerPlayerEntity.offHandStack: ItemStack by ServerPlayerEntity::offHandStack
val ServerPlayerEntity.vehicle: Entity? by ServerPlayerEntity::vehicle
val ServerPlayerEntity.velocity: Vec3d by ServerPlayerEntity::velocity
