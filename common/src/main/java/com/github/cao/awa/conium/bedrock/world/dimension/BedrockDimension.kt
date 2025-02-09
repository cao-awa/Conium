package com.github.cao.awa.conium.bedrock.world.dimension

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.kotlin.extent.innate.orGetAuto
import net.minecraft.entity.Entity
import net.minecraft.world.World
import net.minecraft.world.explosion.AdvancedExplosionBehavior
import java.util.*

@BedrockScriptApi
@BedrockScriptApiFacade("Dimension")
class BedrockDimension(private val delegate: World) {
    // TODO 'allowUnderwater' not completed
    @BedrockScriptApi
    @BedrockScriptApiFacade("Dimension", "createExplosion")
    fun createExplosion(location: BedrockScriptAnonymousObjectMap, radius: Int, explosionOptions: BedrockScriptAnonymousObjectMap? = null): Boolean {
        val source: Entity? = explosionOptions.orGetAuto(null) { it["source"] }
        val createFire: Boolean = explosionOptions.orGetAuto(false) { it["causesFire"] }
        val destroyBlocks: Boolean = explosionOptions.orGetAuto(true) { it["breaksBlocks"] }

        this.delegate.createExplosion(
            source,
            null,
            AdvancedExplosionBehavior(destroyBlocks, true, Optional.empty(), Optional.empty()),
            (location["x"] as Number).toDouble(),
            (location["y"] as Number).toDouble(),
            (location["z"] as Number).toDouble(),
            radius.toFloat(),
            createFire,
            World.ExplosionSourceType.NONE
        )

        return true
    }
}

val World.bedrockDimension: BedrockDimension
    get() = BedrockDimension(this)
