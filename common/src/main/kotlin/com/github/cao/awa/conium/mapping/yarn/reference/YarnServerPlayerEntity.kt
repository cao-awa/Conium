@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.commands.CommandSourceStack
import net.minecraft.core.BlockPos
import net.minecraft.core.SectionPos
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.level.ServerPlayerGameMode
import net.minecraft.server.network.ServerGamePacketListenerImpl
import net.minecraft.server.PlayerAdvancements
import net.minecraft.stats.ServerStatsCounter
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level

/**
 * See the mapping [ServerPlayer](https://mappings.dev/1.21.4/net/minecraft/server/level/ServerPlayer.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val ServerPlayer.serverWorld: ServerLevel get() = this.level()
val ServerPlayer.commandSource: CommandSourceStack get() = this.createCommandSourceStack()
val ServerPlayer.advancementTracker: PlayerAdvancements get() = this.advancements
val ServerPlayer.cameraEntity: Entity get() = this.camera
val ServerPlayer.ip: String get() = this.ipAddress
val ServerPlayer.lastActionTime: Long get() = this.lastActionTime
val ServerPlayer.playerListName: Component? get() = this.tabListDisplayName
val ServerPlayer.playerListOrder: Int get() = this.tabListOrder
val ServerPlayer.spawnAngle: Float get() = this.respawnConfig?.respawnData()?.yaw() ?: 0.0f
val ServerPlayer.spawnPointDimension: ResourceKey<Level>? get() = this.respawnConfig?.respawnData()?.dimension()
val ServerPlayer.spawnPointPosition: BlockPos? get() = this.respawnConfig?.respawnData()?.pos()
val ServerPlayer.interactionManager: ServerPlayerGameMode get() = this.gameMode
val ServerPlayer.networkHandler: ServerGamePacketListenerImpl get() = this.connection
val ServerPlayer.statHandler: ServerStatsCounter get() = this.stats
val ServerPlayer.watchedSection: SectionPos get() = this.lastSectionPos
