package com.github.cao.awa.conium.bedrock.entity.player.delegate

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.bedrockPlayer
import net.minecraft.server.MinecraftServer

@BedrockScriptApi
@BedrockScriptApiFacade("Player[]")
class BedrockPlayerDelegate(private val delegate: MinecraftServer) {
    operator fun get(index: Int): BedrockPlayer {
        return this.delegate.playerManager.playerList[index].bedrockPlayer()
    }
}
