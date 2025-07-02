@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*
import net.minecraft.resource.featuretoggle.ToggleableFeature

/**
 * See the mapping [ToggleableFeature](https://mappings.dev/1.21.4/net/minecraft/world/flag/FeatureElement.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val ToggleableFeature.requiredFeatures: FeatureSet by ToggleableFeature::requiredFeatures