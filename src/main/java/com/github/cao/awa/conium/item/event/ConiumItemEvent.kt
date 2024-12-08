package com.github.cao.awa.conium.item.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import net.minecraft.item.Item

abstract class ConiumItemEvent<P : ParameterSelective>(eventType: ConiumEventType<Item>) : ConiumEvent<P>(eventType)
