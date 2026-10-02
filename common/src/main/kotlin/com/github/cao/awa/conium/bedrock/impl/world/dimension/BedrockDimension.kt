package com.github.cao.awa.conium.bedrock.impl.world.dimension

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.impl.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.kotlin.extent.innate.orGetAuto
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level

@BedrockScriptApi
@BedrockScriptApiFacade("Dimension")
class BedrockDimension(private val delegate: Level) {
    // TODO 'allowUnderwater' not completed
    @BedrockScriptApi
    @BedrockScriptApiFacade("Dimension", "createExplosion")
    fun createExplosion(location: BedrockScriptAnonymousObjectMap, radius: Int, explosionOptions: BedrockScriptAnonymousObjectMap? = null): Boolean {
        val source: Entity? = explosionOptions.orGetAuto(null) { it["source"] }
        val createFire: Boolean = explosionOptions.orGetAuto(false) { it["causesFire"] }
        val destroyBlocks: Boolean = explosionOptions.orGetAuto(true) { it["breaksBlocks"] }

        this.delegate.explode(
            source,
            null,
            null,
            location.getAs<Number>("x").toDouble(),
            location.getAs<Number>("y").toDouble(),
            location.getAs<Number>("z").toDouble(),
            radius.toFloat(),
            createFire,
            if (destroyBlocks) Level.ExplosionInteraction.MOB else Level.ExplosionInteraction.NONE
        )

        return true
    }
}

val Level.bedrockDimension: BedrockDimension
    get() = BedrockDimension(this)
