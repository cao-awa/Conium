package com.github.cao.awa.conium.event.type;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;

public record ConiumEventArgType<T>(String key) {
    public static ConiumEventArgType<ServerWorld> SERVER_WORLD = new ConiumEventArgType<>("server_world");

    // Items
    public static ConiumEventArgType<ItemUsageContext> ITEM_USAGE_CONTEXT = new ConiumEventArgType<>("item_usage_context");

}
