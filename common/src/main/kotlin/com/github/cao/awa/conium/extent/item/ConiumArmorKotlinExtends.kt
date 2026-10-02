package com.github.cao.awa.conium.kotlin.extent.item

import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.resources.Identifier

val ArmorType.identifier: Identifier get() = Identifier.withDefaultNamespace("armor." + serializedName)
