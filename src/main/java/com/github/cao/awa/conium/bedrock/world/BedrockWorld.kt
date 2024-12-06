package com.github.cao.awa.conium.bedrock.world

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.bedrock.world.player.delegate.BedrockPlayerDelegate
import net.minecraft.world.World

@BedrockScriptApi
open class BedrockWorld(private val delegate: World) : AbstractBedrockWorld() {
    companion object {
        val DUMMY: DummyBedrockWorld = DummyBedrockWorld()
    }

    private val players: BedrockPlayerDelegate = BedrockPlayerDelegate(this.delegate)

    override fun getPlayers(): BedrockPlayerDelegate = this.players
}

class DummyBedrockWorld : AbstractBedrockWorld() {
    override fun getPlayers(): BedrockPlayerDelegate = TODO("Will not be implements")
}

fun World.toBedrock(): BedrockWorld =  BedrockWorld(this)