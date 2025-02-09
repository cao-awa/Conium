package com.github.cao.awa.conium.bedrock.entity.player

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.entity.BedrockEntity
import com.github.cao.awa.conium.bedrock.entity.player.screen.BedrockOnScreenDisplay
import net.minecraft.entity.player.PlayerEntity

@BedrockScriptApi
@BedrockScriptApiFacade("Player")
class BedrockPlayer(private val delegate: PlayerEntity): BedrockEntity(delegate) {
    @ScriptReadonly
    @BedrockScriptApiFacade("Player", "#onScreenDisplay")
    val onScreenDisplay: BedrockOnScreenDisplay = BedrockOnScreenDisplay(this)
}

fun PlayerEntity.toBedrock(): BedrockPlayer = BedrockPlayer(this)