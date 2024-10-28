package com.github.cao.awa.conium.bedrock.world

import com.github.cao.awa.conium.bedrock.event.BedrockBeforeEvents
import com.github.cao.awa.conium.bedrock.world.player.delegate.BedrockPlayerDelegate
import net.minecraft.world.World

open class BedrockWorld(private val delegate: World): AbstractBedrockWorld() {
    companion object {
        val DUMMY = DummyBedrockWorld()
    }

    private val players: BedrockPlayerDelegate = BedrockPlayerDelegate(this.delegate)

    override fun getPlayers(): BedrockPlayerDelegate = this.players
}

class DummyBedrockWorld : AbstractBedrockWorld() {
    override fun getPlayers(): BedrockPlayerDelegate {
        TODO("Will not be implements")
    }
}

fun World.bedrockWorld(): BedrockWorld {
    return BedrockWorld(
        this
    )
}