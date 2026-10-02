package com.github.cao.awa.conium.bedrock.impl.entity

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.impl.block.state.bedrock
import com.github.cao.awa.conium.bedrock.impl.raycast.hit.BlockRaycastHit
import com.github.cao.awa.conium.bedrock.impl.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.impl.world.dimension.BedrockDimension
import com.github.cao.awa.conium.bedrock.impl.world.dimension.bedrockDimension
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.innate.orGetAuto
import com.github.cao.awa.conium.kotlin.extent.world.executeCommand
import com.github.cao.awa.conium.raycast.ConiumRaycast
import net.minecraft.world.entity.Entity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.phys.HitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.Level
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
    val dimension: BedrockDimension = this.delegate.level().bedrockDimension

    @BedrockScriptApiFacade("Entity", "teleport")
    fun teleport(location: BedrockScriptAnonymousObjectMap, teleportOption: BedrockScriptAnonymousObjectMap) {
        ifServerEntity { serverWorld: ServerLevel ->
            this.delegate.teleportTo(
                serverWorld,
                location.getAs<Number>("x").toDouble(),
                location.getAs<Number>("y").toDouble(),
                location.getAs<Number>("z").toDouble(),
                HashSet(),
                this.delegate.yRot,
                this.delegate.xRot,
                false
            )
        }
    }

    @BedrockScriptApiFacade("Entity", "runCommand")
    fun runCommand(command: String) {
        ifServerEntity { serverWorld: ServerLevel ->
            if (this.delegate is ServerPlayer) {
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
            val pos: Vec3 = hitResult.location
            val world: Level = this.delegate.level()

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
                this.delegate.direction,
                blockPos
            )
        }
    }

    private fun ifServerEntity(action: (ServerLevel) -> Unit) {
        (this.delegate.level() as? ServerLevel)?.let(action)
    }
}

fun Entity.bedrock(): BedrockEntity = BedrockEntity(this)
