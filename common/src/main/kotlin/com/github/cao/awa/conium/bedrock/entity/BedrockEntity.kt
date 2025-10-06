package com.github.cao.awa.conium.bedrock.entity

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.block.state.bedrock
import com.github.cao.awa.conium.bedrock.raycast.hit.BlockRaycastHit
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.world.dimension.BedrockDimension
import com.github.cao.awa.conium.bedrock.world.dimension.bedrockDimension
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.innate.orGetAuto
import com.github.cao.awa.conium.kotlin.extent.world.executeCommand
import com.github.cao.awa.conium.raycast.ConiumRaycast
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.entity.Entity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@BedrockScriptApi
@BedrockScriptApiFacade("Entity")
open class BedrockEntity(private val delegate: Entity) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("BedrockEntity")
    }

    @ScriptReadonly
    @BedrockScriptApiFacade("Entity", "#dimension")
    val dimension: BedrockDimension = this.delegate.entityWorld.bedrockDimension

    @BedrockScriptApiFacade("Entity", "teleport")
    fun teleport(location: BedrockScriptAnonymousObjectMap, teleportOption: BedrockScriptAnonymousObjectMap) {
        ifServerEntity { serverWorld: ServerWorld ->
            this.delegate.teleport(
                serverWorld,
                location.getAs<Number>("x").toDouble(),
                location.getAs<Number>("y").toDouble(),
                location.getAs<Number>("z").toDouble(),
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
            } else {
                LOGGER.warn("Unable to execute command '$command' as entity that are not player")
            }
        }
    }

    @BedrockScriptApiFacade("Entity", "runCommandAsync")
    fun runCommandAsync(command: String) = runCommand(command)

    @BedrockScriptApiFacade("Entity", "getBlockFromViewDirection")
    fun getBlockFromViewDirection(options: BedrockScriptAnonymousObjectMap? = BedrockScriptAnonymousObjectMap.EMPTY): BlockRaycastHit? {
        val maxDistance: Double = options.orGetAuto(20.0) { it["maxDistance"] }
        val includeLiquidBlocks: Boolean = options.orGetAuto(false) { it["includeLiquidBlocks"] }
        val includePassableBlocks: Boolean = options.orGetAuto(false) { it["includePassableBlocks"] }

        ConiumRaycast.raycast(
            this.delegate,
            maxDistance,
            0F,
            includeLiquidBlocks,
            includePassableBlocks
        ).let { hitResult: HitResult ->
            val hitType: HitResult.Type = hitResult.type
            val pos: Vec3d = hitResult.pos
            val world: World = this.delegate.entityWorld

            if (hitType == HitResult.Type.MISS || hitType == HitResult.Type.ENTITY) {
                return null
            }

            val blockPos = BlockPos(
                pos.x.int,
                pos.y.int,
                pos.z.int,
            )

            return BlockRaycastHit(
                world.getBlockState(blockPos).bedrock,
                this.delegate.facing,
                blockPos
            )
        }
    }

    private fun ifServerEntity(action: (ServerWorld) -> Unit) {
        (this.delegate.entityWorld as? ServerWorld)?.let(action)
    }
}

fun Entity.bedrock(): BedrockEntity = BedrockEntity(this)