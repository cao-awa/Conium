package com.github.cao.awa.conium.mixin.entity;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    private LivingEntity cast() {
        return (LivingEntity) (Object) this;
    }

    @Inject(
            method = "damage",
            at = @At("HEAD"),
            cancellable = true
    )
    public void damage(ServerWorld world, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        // Request the entity damaging event.
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.ENTITY_DAMAGE);

        LivingEntity self = cast();

        EntityType<?> type = self.getType();

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.DAMAGE_SOURCE, damageSource)
                .put(ConiumEventArgTypes.LIVING_ENTITY, self)
                .put(ConiumEventArgTypes.FLOAT, amount);

        if (eventContext.presaging(type)) {
            // Only presaging state is true can be continued.
            eventContext.arising(type);
        } else {
            // Cancel this event when presaging was rejected the event.
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "applyDamage",
            at = @At("RETURN")
    )
    public void damaged(ServerWorld world, DamageSource damageSource, float amount, CallbackInfo ci) {
        // Request the entity damaged event.
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.ENTITY_DAMAGED);

        LivingEntity self = cast();

        EntityType<?> type = self.getType();

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.DAMAGE_SOURCE, damageSource)
                .put(ConiumEventArgTypes.LIVING_ENTITY, self)
                .put(ConiumEventArgTypes.FLOAT, amount);

        // The entity damaged event is not cancelable, only arising the context.
        if (eventContext.presaging(type)) {
            eventContext.arising(type);
        }
    }

    @Inject(
            method = "onDeath",
            at = @At("HEAD"),
            cancellable = true
    )
    public void dying(DamageSource damageSource, CallbackInfo ci) {
        // Request the entity dying event.
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.ENTITY_DIE);

        LivingEntity self = cast();

        EntityType<?> type = self.getType();

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.DAMAGE_SOURCE, damageSource)
                .put(ConiumEventArgTypes.LIVING_ENTITY, self);

        if (eventContext.presaging(type)) {
            // Only presaging state is true can be continued.
            eventContext.arising(type);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "onDeath",
            at = @At("RETURN")
    )
    public void dead(DamageSource damageSource, CallbackInfo ci) {
        // Request the entity dead event.
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.ENTITY_DEAD);

        LivingEntity self = cast();

        EntityType<?> type = self.getType();

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.DAMAGE_SOURCE, damageSource)
                .put(ConiumEventArgTypes.LIVING_ENTITY, self);

        // The entity dead event is not cancelable, only arising the context.
        if (eventContext.presaging(type)) {
            eventContext.arising(type);
        }
    }

    @Inject(
            method = "sleep",
            at = @At("HEAD"),
            cancellable = true
    )
    public void sleep(BlockPos pos, CallbackInfo ci) {
        // Request the entity sleep event.
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.ENTITY_SLEEP);

        LivingEntity self = cast();

        EntityType<?> type = self.getType();

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos)
                .put(ConiumEventArgTypes.LIVING_ENTITY, self)
                .put(ConiumEventArgTypes.WORLD, self.getWorld());

        if (eventContext.presaging(type)) {
            // Only presaging state is true can be continued.
            eventContext.arising(type);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "wakeUp",
            at = @At("HEAD"),
            cancellable = true
    )
    public void wakeUp(CallbackInfo ci) {
        LivingEntity self = cast();

        BlockPos sleepPos = self.getSleepingPosition().orElse(null);

        // Only handle event when entity sleeping.
        if (sleepPos != null) {
            // Request the entity sleep event.
            ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.ENTITY_SLEEP);

            EntityType<?> type = self.getType();

            // Fill the context args.
            eventContext.put(ConiumEventArgTypes.BLOCK_POS, sleepPos)
                    .put(ConiumEventArgTypes.LIVING_ENTITY, self)
                    .put(ConiumEventArgTypes.WORLD, self.getWorld());

            if (eventContext.presaging(type)) {
                // Only presaging state is true can be continued.
                eventContext.arising(type);
            } else {
                // Cancel this event when presaging was rejected the event.
                ci.cancel();
            }
        }
    }
}
