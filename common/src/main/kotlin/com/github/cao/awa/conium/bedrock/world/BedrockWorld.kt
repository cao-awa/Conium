package com.github.cao.awa.conium.bedrock.world

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.entity.player.delegate.BedrockPlayerDelegate
import net.minecraft.server.MinecraftServer

@BedrockScriptApi
@BedrockScriptApiFacade("World")
open class BedrockWorld(private val delegate: MinecraftServer) : AbstractBedrockWorld() {
    companion object {
        val DUMMY: DummyBedrockWorld = DummyBedrockWorld()
    }

    private val players: BedrockPlayerDelegate = BedrockPlayerDelegate(this.delegate)

    override fun getPlayers(): BedrockPlayerDelegate = this.players
}

class DummyBedrockWorld : AbstractBedrockWorld() {
    override fun getPlayers(): BedrockPlayerDelegate = throw IllegalAccessException("The 'world' of current context are not initialized, cannot access methods and members")
}

val MinecraftServer.bedrockWorld: BedrockWorld
    get() = BedrockWorld(this)