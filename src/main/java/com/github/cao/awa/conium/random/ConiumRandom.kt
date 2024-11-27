package com.github.cao.awa.conium.random

import net.minecraft.util.math.random.Random

object ConiumRandom {
    @JvmStatic
    fun tryChance(chance: IntRange, random: Random): Boolean = random.nextBetween(chance.first, chance.last) == 0
}
