package com.github.cao.awa.conium.kotlin.extent.item

import net.minecraft.item.equipment.EquipmentType
import net.minecraft.util.Identifier

val EquipmentType.identifier: Identifier get() = Identifier.ofVanilla("armor." + asString())
