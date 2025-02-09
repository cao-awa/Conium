package com.github.cao.awa.conium.bedrock.entity

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.block.state.bedrock
import com.github.cao.awa.conium.bedrock.raycast.hit.BlockRaycastHit
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.innate.orGetAuto
import com.github.cao.awa.conium.kotlin.extent.world.executeCommand
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.entity.Entity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext
import net.minecraft.world.World
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
            } else {
                LOGGER.warn("Unable to execute command '$command' as entity that are not player")
            }
        }
    }

    @BedrockScriptApiFacade("Entity", "getBlockFromViewDirection")
    fun getBlockFromViewDirection(options: BedrockScriptAnonymousObjectMap? = BedrockScriptAnonymousObjectMap.EMPTY): BlockRaycastHit? {
        val maxDistance: Double = options.orGetAuto(20.0) { it["maxDistance"] }
        val includeLiquidBlocks: Boolean = options.orGetAuto(false) { it["includeLiquidBlocks"] }
        val includePassableBlocks: Boolean = options.orGetAuto(false) { it["includePassableBlocks"] }

        raycast(
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

    private fun raycast(maxDistance: Double, tickDelta: Float, includeFluids: Boolean, includePassableBlocks: Boolean): HitResult {
        val entity: Entity = this.delegate

        val start: Vec3d = entity.getCameraPosVec(tickDelta)
        val delta: Vec3d = entity.getRotationVec(tickDelta)
        val end = start.add(delta.x * maxDistance, delta.y * maxDistance, delta.z * maxDistance)

        val blockShapeType: RaycastContext.ShapeType = if (includePassableBlocks) {
            RaycastContext.ShapeType.COLLIDER
        } else {
            RaycastContext.ShapeType.OUTLINE
        }

        val fluidHandling: RaycastContext.FluidHandling = if (includeFluids) RaycastContext.FluidHandling.ANY else RaycastContext.FluidHandling.NONE

        return entity.world.raycast(
            RaycastContext(
                start,
                end,
                blockShapeType,
                fluidHandling,
                entity
            )
        )
    }

    private fun ifServerEntity(action: (ServerWorld) -> Unit) {
        (this.delegate.world as? ServerWorld)?.let(action)
    }
}

fun Entity.toBedrock(): BedrockEntity = BedrockEntity(this)