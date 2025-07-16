package com.github.cao.awa.conium.item.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import net.minecraft.item.Item

abstract class ConiumItemEvent< P : ParameterSelective, M: ConiumEventMetadata<Item>>(
    eventType: ConiumEventType<Item, M>
) : ConiumEvent<Item, P, M>(eventType)
