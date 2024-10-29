package com.github.cao.awa.conium.bedrock.world.player.delegate

import com.github.cao.awa.conium.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.world.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.world.player.toBedrock
import net.minecraft.world.World

@BedrockScriptApi
@BedrockScriptApiFacade("Player[]")
class BedrockPlayerDelegate(private val delegate: World) {
    operator fun get(index: Int): BedrockPlayer {
        return this.delegate.players[index].toBedrock()
    }
}
