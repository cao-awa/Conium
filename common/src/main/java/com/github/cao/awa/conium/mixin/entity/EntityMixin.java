package com.github.cao.awa.conium.mixin.entity;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    private int fireTicks;

    @Shadow public abstract World getWorld();

    @Shadow public abstract void extinguish();

    @Unique
    private Entity cast() {
        return (Entity) (Object) this;
    }

    @Inject(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;isFireImmune()Z"
            )
    )
    public void onFireTick(CallbackInfo ci) {
        // Only trigger event when fire ticks left at least 1.
        if (this.fireTicks > 0) {
            // Create on fire context.
            ConiumEventContext<?> onFireContext = ConiumEvent.request(ConiumEventType.ENTITY_ON_FIRE);

            Entity self = cast();

            EntityType<?> type = self.getType();

            // Fill the context args.
            onFireContext.put(ConiumEventArgTypes.ENTITY, self)
                    .put(ConiumEventArgTypes.INT, this.fireTicks);

            // This event cannot cancel because it is not the fire tick event, on fire event just a notice event.
            if (onFireContext.presaging(type)) {
                onFireContext.arising(type);
            }

            if (this.fireTicks == 1) {
                extinguish();
            }
        }
    }

    @Inject(
            method = "extinguish",
            at = @At("HEAD"),
            cancellable = true
    )
    public void extinguish(CallbackInfo ci) {
        // Only trigger event when fire ticks left at least 1.
        if (getWorld() instanceof ServerWorld) {
            // Create fire extinguishing context.
            ConiumEventContext<?> extinguishingContext = ConiumEvent.request(ConiumEventType.ENTITY_EXTINGUISH_FIRE);

            Entity self = cast();

            EntityType<?> type = self.getType();

            // Fill the context args.
            extinguishingContext.put(ConiumEventArgTypes.ENTITY, self)
                    .put(ConiumEventArgTypes.INT, this.fireTicks);

            if (extinguishingContext.presaging(type)) {
                // Only presaging state is true can be continued.
                extinguishingContext.arising(type);

                // Create fire extinguished context.
                ConiumEventContext<?> extinguishedContext = ConiumEvent.request(ConiumEventType.ENTITY_EXTINGUISHED_FIRE);

                // Fill the context args.
                extinguishedContext.put(ConiumEventArgTypes.ENTITY, self);

                // This event cannot cancel because it already completed.
                if (extinguishedContext.presaging(type)) {
                    extinguishedContext.arising(type);
                }
            } else {
                // Cancel this event when presaging was rejected the event.
                ci.cancel();
            }
        }
    }
}
