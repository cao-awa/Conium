package com.github.cao.awa.conium.kotlin.extent.world

import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

fun ServerWorld.executeCommand(player: ServerPlayerEntity, command: String) {
    val commandSource: ServerCommandSource = player.commandSource

    this.server.commandManager.executeWithPrefix(
        commandSource,
        command
    )
}
