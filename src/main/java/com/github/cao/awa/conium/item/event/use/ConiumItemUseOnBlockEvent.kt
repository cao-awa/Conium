package com.github.cao.awa.conium.item.event.use;

import com.github.cao.awa.conium.event.type.ConiumEventArgType;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder;
import com.github.cao.awa.conium.item.event.ConiumItemEvent;
import com.github.cao.awa.conium.parameter.ParameterSelective;
import com.github.cao.awa.conium.parameter.ParameterSelective1;
import com.github.cao.awa.conium.parameter.ParameterSelective2;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class ConiumItemUseOnBlockEvent extends ConiumItemEvent<ParameterSelective2<ServerWorld, ItemUsageContext>> {
    @NotNull
    @Override
    public ConiumEventContext<? extends ParameterSelective> requirement() {
        return ConiumEventContextBuilder.requires(
                ConiumEventArgType.SERVER_WORLD,
                ConiumEventArgType.ITEM_USAGE_CONTEXT
        ).trigger(
                (identity, world, context) -> hasAny(identity, handler -> handler.trigger(world, context))
        );
    }
}
