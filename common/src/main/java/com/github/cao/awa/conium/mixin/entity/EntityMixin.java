package com.github.cao.awa.conium.mixin.entity;

import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.conium.sprint.SprintMovementEntity;
import com.github.cao.awa.translator.structuring.cast.Caster;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements SprintMovementEntity {
    @Shadow
    public abstract boolean isSprinting();

    @Shadow
    protected abstract void setSharedFlag(int index, boolean value);

    @Shadow
    private int remainingFireTicks;

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
    private Entity asEntity() {
        return Caster.cast(this);
    }

    @Inject(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;fireImmune()Z"
            )
    )
    public void onFireTick(CallbackInfo ci) {
        // Trigger entity on fire event.
        ConiumEntityEventMixinIntermediary.fireOnFireEvent(asEntity());
    }

    @Inject(
            method = "setRemainingFireTicks",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onExtinguish(int newFireTicks, CallbackInfo ci) {
        if (this.remainingFireTicks > 0 && newFireTicks <= 0) {
            // Trigger entity fire extinguish event.
            if (ConiumEntityEventMixinIntermediary.fireExtinguishEvent(asEntity())) {
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
            if (ConiumEntityEventMixinIntermediary.fireEntitySprintEvent(asEntity())) {
                setSharedFlag(3, false);

                conium$setCanStartSprint(false);

                ci.cancel();
            }
        } else {
            // Trigger entity stop sprint event.
            if (ConiumEntityEventMixinIntermediary.fireEntityStopSprintEvent(asEntity())) {
                setSharedFlag(3, true);
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
            if (ConiumEntityEventMixinIntermediary.fireEntitySprintingEvent(asEntity())) {
                // Cancel this event when presaging was rejected the event.
                ci.cancel();
            }
        }
    }
}
