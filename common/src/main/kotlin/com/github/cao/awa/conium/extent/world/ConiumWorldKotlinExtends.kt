package com.github.cao.awa.conium.kotlin.extent.world

import net.minecraft.commands.CommandSourceStack
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.level.ServerLevel

fun ServerLevel.executeCommand(player: ServerPlayer, command: String) {
    val commandSource: CommandSourceStack = player.createCommandSourceStack()

    this.server.commands.performPrefixedCommand(
        commandSource,
        command
    )
}
