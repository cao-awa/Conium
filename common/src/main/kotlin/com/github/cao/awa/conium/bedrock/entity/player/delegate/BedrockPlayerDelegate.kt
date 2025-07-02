package com.github.cao.awa.conium.bedrock.entity.player.delegate

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.bedrockPlayer
import com.github.cao.awa.conium.script.javascript.std.collection.array.Array
import net.minecraft.server.MinecraftServer
import net.minecraft.server.PlayerManager

@BedrockScriptApi
@BedrockScriptApiFacade("Player[]")
class BedrockPlayerDelegate(
    private val server: MinecraftServer
): Array<BedrockPlayer>() {
    private val playerManager: PlayerManager = this.server.playerManager

    override operator fun get(index: Int): BedrockPlayer {
        return this.playerManager.playerList[index].bedrockPlayer
    }
}
