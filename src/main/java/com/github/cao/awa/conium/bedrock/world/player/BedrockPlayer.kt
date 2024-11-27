package com.github.cao.awa.conium.bedrock.world.player

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.world.player.screen.BedrockOnScreenDisplay
import net.minecraft.entity.player.PlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("Player")
class BedrockPlayer(private val delegate: PlayerEntity) {
    val onScreenDisplay: BedrockOnScreenDisplay = BedrockOnScreenDisplay(this)
}

fun PlayerEntity.toBedrock(): BedrockPlayer {
    return BedrockPlayer(
        this
    )
}