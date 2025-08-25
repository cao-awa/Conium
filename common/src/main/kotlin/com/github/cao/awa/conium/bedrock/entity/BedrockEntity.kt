package com.github.cao.awa.conium.bedrock.entity

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.block.state.bedrock
import com.github.cao.awa.conium.bedrock.raycast.hit.BlockRaycastHit
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.world.dimension.BedrockDimension
import com.github.cao.awa.conium.bedrock.world.dimension.bedrockDimension
import com.github.cao.awa.conium.kotlin.extend.innate.int
import com.github.cao.awa.conium.kotlin.extend.innate.orGetAuto
import com.github.cao.awa.conium.kotlin.extend.world.executeCommand
import com.github.cao.awa.conium.raycast.ConiumRaycast
import com.github.cao.awa.conium.script.generic.anonymous.getDouble
import com.github.cao.awa.conium.threadpool.ConiumThreadPool
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
        private val LOGGER: Logger = LogManager.getLogger("Bedrock#Entity")
    }

    @ScriptReadonly
    @BedrockScriptApiFacade("Entity", "#dimension")
    val dimension: BedrockDimension = this.delegate.world.bedrockDimension

    @BedrockScriptApiFacade("Entity", "teleport")
    fun teleport(location: BedrockScriptAnonymousObjectMap, teleportOption: BedrockScriptAnonymousObjectMap) {
        ifServerEntity {
            delegate.teleport(
                this,
                location.getDouble("x"),
                location.getDouble("y"),
                location.getDouble("z"),
                CollectionFactor.hashSet(),
                delegate.yaw,
                delegate.pitch,
                false
            )
        }
    }

    @BedrockScriptApiFacade("Entity", "runCommand")
    fun runCommand(command: String) {
        ifServerEntity {
            if (delegate is ServerPlayerEntity) {
                executeCommand(
                    delegate,
                    command
                )
            } else {
                LOGGER.warn("Unable to execute command '$command' as entity that are not player")
            }
        }
    }

    @BedrockScriptApiFacade("Entity", "runCommandAsync")
    fun runCommandAsync(command: String) = ConiumThreadPool.submit { runCommand(command) }

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
            val world: World = this.delegate.world

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

    private fun ifServerEntity(action: ServerWorld.()-> Unit) {
        (this.delegate.world as? ServerWorld)?.let(action)
    }
}

fun Entity.bedrock(): BedrockEntity = BedrockEntity(this)