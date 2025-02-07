@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*

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
