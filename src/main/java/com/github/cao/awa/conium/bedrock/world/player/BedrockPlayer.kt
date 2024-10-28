package com.github.cao.awa.conium.bedrock.world.player

import com.github.cao.awa.conium.bedrock.world.player.screen.BedrockOnScreenDisplay
import net.minecraft.entity.player.PlayerEntity

class BedrockPlayer(val delegate: PlayerEntity) {
    val onScreenDisplay = BedrockOnScreenDisplay(this)
}

fun PlayerEntity.bedrockPlayer(): BedrockPlayer {
    return BedrockPlayer(
        this
    )
}