package com.github.cao.awa.conium.raycast

import net.minecraft.entity.Entity
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.RaycastContext

class ConiumRaycast {
    companion object {
        fun raycast(entity: Entity, maxDistance: Double, tickDelta: Float, includeFluids: Boolean, includePassableBlocks: Boolean): HitResult {
            val start: Vec3d = entity.getCameraPosVec(tickDelta)
            val delta: Vec3d = entity.getRotationVec(tickDelta)
            val end = start.add(delta.x * maxDistance, delta.y * maxDistance, delta.z * maxDistance)

            val blockShapeType: RaycastContext.ShapeType = if (includePassableBlocks) {
                RaycastContext.ShapeType.COLLIDER
            } else {
                RaycastContext.ShapeType.OUTLINE
            }

            val fluidHandling: RaycastContext.FluidHandling = if (includeFluids) RaycastContext.FluidHandling.ANY else RaycastContext.FluidHandling.NONE

            return entity.entityWorld.raycast(
                RaycastContext(
                    start,
                    end,
                    blockShapeType,
                    fluidHandling,
                    entity
                )
            )
        }
    }
}