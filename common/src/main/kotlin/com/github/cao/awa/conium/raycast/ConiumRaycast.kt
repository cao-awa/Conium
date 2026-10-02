package com.github.cao.awa.conium.raycast

import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.level.ClipContext

class ConiumRaycast {
    companion object {
        fun raycast(entity: Entity, maxDistance: Double, tickDelta: Float, includeFluids: Boolean, includePassableBlocks: Boolean): HitResult {
            val start: Vec3 = entity.getEyePosition(tickDelta)
            val delta: Vec3 = entity.getViewVector(tickDelta)
            val end = start.add(delta.x * maxDistance, delta.y * maxDistance, delta.z * maxDistance)

            val blockShapeType: ClipContext.Block = if (includePassableBlocks) {
                ClipContext.Block.COLLIDER
            } else {
                ClipContext.Block.OUTLINE
            }

            val fluidHandling: ClipContext.Fluid = if (includeFluids) ClipContext.Fluid.ANY else ClipContext.Fluid.NONE

            return entity.level().clip(
                ClipContext(
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