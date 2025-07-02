package com.github.cao.awa.conium.bedrock.entity.player.delegate

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.bedrockPlayer
import com.github.cao.awa.conium.script.javascript.std.collection.array.Array
import net.minecraft.server.MinecraftServer

@BedrockScriptApi
@BedrockScriptApiFacade("Player[]")
class BedrockPlayerDelegate(
    private val server: MinecraftServer
): Array<BedrockPlayer>() {
    override operator fun get(index: Int): BedrockPlayer {
        return this.server.playerManager.playerList[index].bedrockPlayer
    }
}
