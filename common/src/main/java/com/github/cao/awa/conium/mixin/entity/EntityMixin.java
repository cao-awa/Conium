package com.github.cao.awa.conium.mixin.entity;

import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.mixin.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.conium.mixin.entity.living.LivingEntityAccessor;
import com.github.cao.awa.conium.sprint.SprintMovementEntity;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author cao_awa
 * @see Entity
 * @see Entity#baseTick()
 * @see Entity#setFireTicks(int)
 * @see Entity#extinguish
 * @see ConiumEntityEventMixinIntermediary#fireOnFireEvent(Entity)
 * @see ConiumEntityEventMixinIntermediary#fireExtinguishEvent(Entity)
 * @see ConiumEventType#ENTITY_ON_FIRE
 * @see ConiumEventType#ENTITY_EXTINGUISH_FIRE
 * @see ConiumEventType#ENTITY_EXTINGUISHED_FIRE
 * @since 1.0.0
 */
@Mixin(Entity.class)
public abstract class EntityMixin implements SprintMovementEntity {
    @Shadow
    public abstract boolean isSprinting();

    @Shadow
    protected abstract void setFlag(int index, boolean value);

    @Shadow
    public abstract World getWorld();

    @Shadow
    private int fireTicks;
    @Unique
    public boolean conium$canStartSprint = true;

    public boolean conium$canStartSprint() {
        return this.conium$canStartSprint;
    }

    @Override
    public void conium$setCanStartSprint(boolean canStartSprint) {
        this.conium$canStartSprint = canStartSprint;
    }

    @Unique
    private Entity conium$asEntity() {
        return Manipulate.cast(this);
    }

    /**
     * Trigger entity the on fire event when entity ticking during fire ticks has at least 1.
     *
     * @param ci the callback info
     * @author cao_awa
     * @see Entity#baseTick()
     * @see Entity#setFireTicks(int)
     * @see Entity#extinguish
     * @see ConiumEntityEventMixinIntermediary#fireOnFireEvent(Entity)
     * @see ConiumEventType#ENTITY_ON_FIRE
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
     * @param ci the callback info
     * @author cao_awa
     * @see Entity#extinguish
     * @see ConiumEntityEventMixinIntermediary#fireExtinguishEvent(Entity)
     * @see ConiumEventType#ENTITY_EXTINGUISH_FIRE
     * @see ConiumEventType#ENTITY_EXTINGUISHED_FIRE
     * @since 1.0.0
     */
    @Inject(
            method = "setFireTicks",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onExtinguish(int newFireTicks, CallbackInfo ci) {
        if (this.fireTicks > 0 && newFireTicks <= 0) {
            // Trigger entity fire extinguish event.
            if (ConiumEntityEventMixinIntermediary.fireExtinguishEvent(Manipulate.cast(this))) {
                // Cancel this event when presaging was rejected the event.
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "setSprinting",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onSetSprint(boolean sprinting, CallbackInfo ci) {
        if (sprinting) {
            // Trigger entity sprint event.
            if (ConiumEntityEventMixinIntermediary.fireEntitySprintsEvent(ConiumEventType.ENTITY_SPRINT, Manipulate.cast(this))) {
                setFlag(3, false);

                setCanStartSprint(false);

                ci.cancel();
            }
        } else {
            // Trigger entity stop sprint event.
            if (ConiumEntityEventMixinIntermediary.fireEntitySprintsEvent(ConiumEventType.ENTITY_STOP_SPRINT, Manipulate.cast(this))) {
                setFlag(3, true);
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "baseTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onSprinting(CallbackInfo ci) {
        if (isSprinting()) {
            // Trigger entity sprinting event.
            if (ConiumEntityEventMixinIntermediary.fireEntitySprintingEvent(Manipulate.cast(this))) {
                // Cancel this event when presaging was rejected the event.
                ci.cancel();
            }
        }
    }
}
