package com.github.cao.awa.conium.random

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import net.minecraft.util.math.random.Random

object ConiumRandom {
    @JvmStatic
    fun tryChance(chance: IntRange, random: Random): Boolean = chance === ConiumDurabilityTemplate.defaultChance || random.nextBetween(chance.first, chance.last) == 0
}
