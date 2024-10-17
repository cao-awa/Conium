package com.github.cao.awa.conium.item

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgType
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult

class ConiumItem(settings: Settings) : Item(settings) {
    companion object {
        fun create(builder: ConiumItemBuilder): ConiumItem {
            val item = ConiumItem(Settings())

            builder.templates?.forEach {
                it.attach(item)
            }

            return item
        }
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient) {
            return ActionResult.PASS
        }

        ConiumEvent.fire(ConiumEventType.ITEM_USE_ON_BLOCK).let {
            it.put(ConiumEventArgType.SERVER_WORLD, context.world as ServerWorld)
            it.put(ConiumEventArgType.ITEM_USAGE_CONTEXT, context)

            if (it.trigger(this)) {
                return ActionResult.SUCCESS
            }
        }
        return ActionResult.PASS
    }
}
