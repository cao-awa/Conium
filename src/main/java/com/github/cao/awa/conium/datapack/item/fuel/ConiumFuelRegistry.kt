package com.github.cao.awa.conium.datapack.item.fuel

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import it.unimi.dsi.fastutil.objects.*
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import java.util.*

class ConiumFuelRegistry {
    private val fuels: Object2IntMap<Item> = Object2IntOpenHashMap()
    private var computedFuels: SequencedSet<Item>? = null
    val fuelItems: Set<Item> get() = Collections.unmodifiableSet(this.fuels.keys)

    fun add(item: Item, durability: Int) {
        this.fuels[item] = durability
    }

    fun isFuel(item: ItemStack): Boolean = this.fuels.containsKey(item.item)

    fun getFuelTicks(stack: ItemStack): Int = if (stack.isEmpty) 0 else this.fuels.getInt(stack.item)

    fun resetComputedFuels() {
        this.computedFuels = null
    }

    fun computeFuels(fuels: SequencedSet<Item>): SequencedSet<Item> {
        return this.computedFuels ?: let {
            val result = LinkedHashSet<Item>()
            result.addAll(fuels)
            result.addAll(this.fuelItems)

            this.computedFuels = result

            result
        }
    }
}
