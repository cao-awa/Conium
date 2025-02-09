@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*
import net.minecraft.block.AbstractBlock.Settings
import java.util.*

/**
 * See the mapping [AbstractBlock](https://mappings.dev/1.21.4/net/minecraft/world/level/block/state/BlockBehaviour.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val AbstractBlock.translationKey: String by AbstractBlock::translationKey
val AbstractBlock.defaultMapColor: MapColor by AbstractBlock::defaultMapColor
val AbstractBlock.hardness: Float by AbstractBlock::hardness
val AbstractBlock.lootTableKey: Optional<RegistryKey<LootTable>> by AbstractBlock::lootTableKey
val AbstractBlock.settings: Settings by AbstractBlock::settings
