package com.github.cao.awa.conium.event.type

import net.minecraft.client.world.ClientWorld
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

@JvmRecord
data class ConiumEventArgType<T>(val key: String) {
    companion object {
        @JvmField
        var SERVER_WORLD: ConiumEventArgType<ServerWorld> = ConiumEventArgType("server_world")
        @JvmField
        var CLIENT_WORLD: ConiumEventArgType<ClientWorld> = ConiumEventArgType("client_world")
        @JvmField
        var WORLD: ConiumEventArgType<World> = ConiumEventArgType("world")

        // Items
        @JvmField
        var ITEM_USAGE_CONTEXT: ConiumEventArgType<ItemUsageContext> = ConiumEventArgType("item_usage_context")
    }
}
