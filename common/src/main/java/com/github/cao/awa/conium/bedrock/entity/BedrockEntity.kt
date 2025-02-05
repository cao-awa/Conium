package com.github.cao.awa.conium.bedrock.entity

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.kotlin.extent.world.executeCommand
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import org.apache.logging.log4j.LogManager

@BedrockScriptApi
@BedrockScriptApiFacade("Entity")
open class BedrockEntity(private val delegate: Entity) {
    companion object {
        private val LOGGER = LogManager.getLogger("BedrockEntity")
    }

    @BedrockScriptApiFacade("Entity", "teleport")
    fun teleport(location: BedrockScriptAnonymousObjectMap, teleportOption: BedrockScriptAnonymousObjectMap) {
        ifServerEntity { serverWorld: ServerWorld ->
            this.delegate.teleport(
                serverWorld,
                (location["x"] as Number).toDouble(),
                (location["y"] as Number).toDouble(),
                (location["z"] as Number).toDouble(),
                CollectionFactor.hashSet(),
                this.delegate.yaw,
                this.delegate.pitch,
                false
            )
        }
    }

    @BedrockScriptApiFacade("Entity", "runCommand")
    fun runCommand(command: String) {
        ifServerEntity { serverWorld: ServerWorld ->
            if (this.delegate is ServerPlayerEntity) {
                serverWorld.executeCommand(
                    this.delegate,
                    command
                )
            } else{
                LOGGER.warn("Unable to execute command '$command' as entity that are not player")
            }
        }
    }

    private fun ifServerEntity(action: (ServerWorld) -> Unit) {
        (this.delegate.world as? ServerWorld)?.let(action)
    }
}

fun Entity.toBedrock(): BedrockEntity = BedrockEntity(this)