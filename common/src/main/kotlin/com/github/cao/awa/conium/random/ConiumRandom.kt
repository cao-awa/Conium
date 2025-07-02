package com.github.cao.awa.conium.random

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import java.util.*
import net.minecraft.util.math.random.Random as MinecraftRandom

object ConiumRandom {
    val RANDOM: Random = Random()

    @JvmStatic
    fun tryChance(chance: IntRange, random: MinecraftRandom): Boolean = chance === ConiumDurabilityTemplate.defaultChance || random.nextBetween(chance.first, chance.last) == 0

    @JvmStatic
    fun nextBoolean(): Boolean = this.RANDOM.nextBoolean()
}
