package com.github.cao.awa.conium.bedrock.world.player.delegate

import com.github.cao.awa.conium.bedrock.world.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.world.player.bedrockPlayer
import net.minecraft.world.World

class BedrockPlayerDelegate(val delegate: World) {
    operator fun get(index: Int): BedrockPlayer {
        return this.delegate.players[index].bedrockPlayer()
    }
}
