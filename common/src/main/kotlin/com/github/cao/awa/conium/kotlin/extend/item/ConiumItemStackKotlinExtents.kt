package com.github.cao.awa.conium.kotlin.extend.item

import net.minecraft.component.MergedComponentMap
import net.minecraft.item.ItemStack

val ItemStack.mergedComponents: MergedComponentMap? get() = this.components as? MergedComponentMap