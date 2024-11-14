import net.minecraft.entity.LivingEntity
import net.minecraft.server.network.ServerPlayerEntity
import java.util.*
import net.minecraft.util.math.Vec3d

val random = Random()

request(
    SERVER_TICK,
    SERVER
) { _, server ->
    server.worlds.forEach { world ->
        world.iterateEntities().forEach { entity ->
            if (entity != null && entity !is ServerPlayerEntity) {
                entity.updatePositionAndAngles(
                    entity.x + random.nextDouble(-0.1, 0.1),
                    entity.y + random.nextDouble(0.0, 0.5),
                    entity.z + random.nextDouble(-0.1, 0.1),
                    random.nextFloat(0F, 360F),
                    random.nextFloat(0F, 360F)
                )

                if (entity is LivingEntity) {
                    entity.health = entity.maxHealth

                    if (random.nextBoolean() && random.nextBoolean()) {
                        entity.setOnFireForTicks(2)
                    }
                }
            }
        }
    }

    true
}

request(
    PLACE_BLOCK,
    SERVER_WORLD
) { _, world ->
    println(world)

    true
}

// Let blue bed explosion!
request(
    USE_BLOCK,
    SERVER_WORLD,
    BLOCK_POS,
    BLOCK_STATE
) { _, world, pos, state ->
    if (state.block === Blocks.BLUE_BED) {
        val vec3d: Vec3d = pos.toCenterPos()
        world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, 200.0f, true, World.ExplosionSourceType.BLOCK)
    }

    true
}
