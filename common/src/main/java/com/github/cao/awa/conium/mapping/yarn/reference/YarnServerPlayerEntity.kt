@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.advancement.PlayerAdvancementTracker
import net.minecraft.entity.Entity
import net.minecraft.network.message.ChatVisibility
import net.minecraft.network.packet.c2s.common.SyncedClientOptions
import net.minecraft.server.command.CommandOutput
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ChunkFilter
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

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
