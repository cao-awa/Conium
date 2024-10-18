package com.github.cao.awa.conium.event.type

import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld

@JvmRecord
data class ConiumEventArgType<T>(val key: String) {
    companion object {
        @JvmField
        var SERVER_WORLD: ConiumEventArgType<ServerWorld> = ConiumEventArgType("server_world")

        // Items
        @JvmField
        var ITEM_USAGE_CONTEXT: ConiumEventArgType<ItemUsageContext> = ConiumEventArgType("item_usage_context")
    }
}
