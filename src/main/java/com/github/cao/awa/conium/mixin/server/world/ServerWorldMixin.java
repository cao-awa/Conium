package com.github.cao.awa.conium.mixin.server.world;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(
            method = "tickEntity",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/entity/Entity;tick()V"
            ),
            cancellable = true
    )
    public void tickEntity(Entity entity, CallbackInfo ci) {
        // Request the entity ticking context.
        ConiumEventContext<?> tickingContext = ConiumEvent.request(ConiumEventType.ENTITY_TICK);

        EntityType<?> type = entity.getType();

        // Fill the context args.
        tickingContext.put(ConiumEventArgTypes.WORLD, (World) (Object) this)
                .put(ConiumEventArgTypes.ENTITY, entity);

        if (tickingContext.presaging(type)) {
            // Only presaging state is true can be continued.
            tickingContext.arising(type);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "tickEntity",
            at = @At(
                    value = "RETURN",
                    target = "Lnet/minecraft/entity/Entity;tick()V"
            )
    )
    public void tickedEntity(Entity entity, CallbackInfo ci) {
        // Request the entity ticked context.
        ConiumEventContext<?> tickedContext = ConiumEvent.request(ConiumEventType.ENTITY_TICKED);

        EntityType<?> type = entity.getType();

        // Fill the context args.
        tickedContext.put(ConiumEventArgTypes.WORLD, (World) (Object) this)
                .put(ConiumEventArgTypes.ENTITY, entity);

        if (tickedContext.presaging(type)) {
            // Only presaging state is true can be continued.
            tickedContext.arising(type);
        }
    }
}
