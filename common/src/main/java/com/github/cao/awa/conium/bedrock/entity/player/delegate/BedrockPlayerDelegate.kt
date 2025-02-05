package com.github.cao.awa.conium.bedrock.entity.player.delegate

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.toBedrock
import net.minecraft.world.World

@BedrockScriptApi
@BedrockScriptApiFacade("Player[]")
class BedrockPlayerDelegate(private val delegate: World) {
    operator fun get(index: Int): BedrockPlayer {
        return this.delegate.players[index].toBedrock()
    }
}
