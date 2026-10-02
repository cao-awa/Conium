@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import java.util.Optional

val BlockBehaviour.translationKey: String get() = this.descriptionId
val BlockBehaviour.defaultMapColor: MapColor get() = this.defaultMapColor()
val BlockBehaviour.hardness: Float get() = this.defaultDestroyTime()
val BlockBehaviour.lootTableKey: Optional<ResourceKey<LootTable>> get() = this.lootTable
val BlockBehaviour.settings: BlockBehaviour.Properties get() = this.properties()
