package com.github.cao.awa.conium.kotlin.extent.item

import net.minecraft.core.component.PatchedDataComponentMap
import net.minecraft.world.item.ItemStack

val ItemStack.mergedComponents: PatchedDataComponentMap? get() = this.components as? PatchedDataComponentMap
