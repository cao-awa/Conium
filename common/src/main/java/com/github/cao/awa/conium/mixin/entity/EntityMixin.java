package com.github.cao.awa.conium.mixin.entity;

import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.mixin.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @see Entity
 * @see Entity#baseTick()
 * @see Entity#setFireTicks(int)
 * @see Entity#extinguish
 * @see ConiumEntityEventMixinIntermediary#fireOnFireEvent(Entity)
 * @see ConiumEntityEventMixinIntermediary#fireExtinguishEvent(Entity)
 * @see ConiumEventType#ENTITY_ON_FIRE
 * @see ConiumEventType#ENTITY_EXTINGUISH_FIRE
 * @see ConiumEventType#ENTITY_EXTINGUISHED_FIRE
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
@Mixin(Entity.class)
public class EntityMixin {
    /**
     * Trigger entity the on fire event when entity ticking during fire ticks has at least 1.
     *
     * @see Entity#baseTick()
     * @see Entity#setFireTicks(int)
     * @see Entity#extinguish
     * @see ConiumEntityEventMixinIntermediary#fireOnFireEvent(Entity)
     * @see ConiumEventType#ENTITY_ON_FIRE
     *
     * @param ci the callback info
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @Inject(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;isFireImmune()Z"
            )
    )
    public void onFireTick(CallbackInfo ci) {
        // Trigger entity on fire event.
        ConiumEntityEventMixinIntermediary.fireOnFireEvent(Manipulate.cast(this));
    }

    /**
     * Trigger entity the fire extinguishes events when extinguishing.
     *
     * @see Entity#extinguish
     * @see ConiumEntityEventMixinIntermediary#fireExtinguishEvent(Entity)
     * @see ConiumEventType#ENTITY_EXTINGUISH_FIRE
     * @see ConiumEventType#ENTITY_EXTINGUISHED_FIRE
     *
     * @param ci the callback info
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @Inject(
            method = "extinguish",
            at = @At("HEAD"),
            cancellable = true
    )
    public void extinguish(CallbackInfo ci) {
        // Trigger entity fire extinguish event.
        if (ConiumEntityEventMixinIntermediary.fireExtinguishEvent(Manipulate.cast(this))) {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }
}
