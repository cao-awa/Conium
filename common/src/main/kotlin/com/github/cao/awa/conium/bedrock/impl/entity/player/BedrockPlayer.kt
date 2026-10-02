package com.github.cao.awa.conium.bedrock.impl.entity.player

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.impl.entity.BedrockEntity
import com.github.cao.awa.conium.bedrock.impl.entity.player.screen.BedrockOnScreenDisplay
import net.minecraft.world.entity.player.Player

@BedrockScriptApi
@BedrockScriptApiFacade("Player")
class BedrockPlayer(private val delegate: Player) : BedrockEntity(delegate) {
    @ScriptReadonly
    @BedrockScriptApiFacade("Player", "#onScreenDisplay")
    val onScreenDisplay: BedrockOnScreenDisplay = BedrockOnScreenDisplay(this)
}

val Player.bedrockPlayer: BedrockPlayer
    get() = BedrockPlayer(this)