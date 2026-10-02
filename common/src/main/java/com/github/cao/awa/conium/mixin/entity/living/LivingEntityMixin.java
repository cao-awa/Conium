package com.github.cao.awa.conium.mixin.entity.living;

import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.conium.mixin.entity.EntityMixin;
import com.github.cao.awa.translator.structuring.cast.Caster;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {
    @Shadow public abstract Optional<BlockPos> getSleepingPos();

    @Inject(
            method = "hurtServer",
            at = @At("HEAD"),
            cancellable = true
    )
    public void damage(ServerLevel world, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        // Trigger entity damaged event.
        if (ConiumEntityEventMixinIntermediary.fireEntityDamageEvent(
                ConiumEventType.ENTITY_DAMAGE,
                Caster.cast(this),
                damageSource,
                amount
        )) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "actuallyHurt",
            at = @At("RETURN")
    )
    public void damaged(ServerLevel world, DamageSource damageSource, float amount, CallbackInfo ci) {
        // Trigger entity damaged event.
        ConiumEntityEventMixinIntermediary.fireEntityDamagedEvent(
                ConiumEventType.ENTITY_DAMAGED,
                Caster.cast(this),
                damageSource,
                amount
        );
    }

    @Inject(
            method = "die",
            at = @At("HEAD"),
            cancellable = true
    )
    public void dying(DamageSource damageSource, CallbackInfo ci) {
        // Trigger entity dying event.
        if (ConiumEntityEventMixinIntermediary.fireEntityDieEvent(
                ConiumEventType.ENTITY_DIE,
                Caster.cast(this),
                damageSource
        )) {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "die",
            at = @At("RETURN")
    )
    public void dead(DamageSource damageSource, CallbackInfo ci) {
        // Trigger entity dead event.
        ConiumEntityEventMixinIntermediary.fireEntityDeadEvent(
                ConiumEventType.ENTITY_DEAD,
                Caster.cast(this),
                damageSource
        );
    }

    @Inject(
            method = "startSleeping",
            at = @At("HEAD"),
            cancellable = true
    )
    public void trySleep(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Trigger entity wake-up event.
        if (ConiumEntityEventMixinIntermediary.fireEntityTrySleepEvent(
                ConiumEventType.ENTITY_TRY_SLEEP,
                Caster.cast(this),
                pos
        )) {
            // Cancel this event when presaging was rejected the event.
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "startSleeping",
            at = @At("RETURN")
    )
    public void sleep(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Boolean.TRUE.equals(cir.getReturnValue())) {
            // Trigger entity wake-up event.
            ConiumEntityEventMixinIntermediary.fireEntitySleepEvent(
                    ConiumEventType.ENTITY_SLEEP,
                    Caster.cast(this),
                    pos
            );
        }
    }

    @Inject(
            method = "stopSleeping",
            at = @At("HEAD"),
            cancellable = true
    )
    public void wakeUp(CallbackInfo ci) {
        // Trigger entity wake-up event.
        if (ConiumEntityEventMixinIntermediary.fireEntityWakeupEvent(
                ConiumEventType.ENTITY_WAKE_UP,
                Caster.cast(this),
                getSleepingPos().orElse(null)
        )) {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "stopSleeping",
            at = @At("RETURN")
    )
    public void wakedUp(CallbackInfo ci) {
        // Trigger entity waked up event.
        ConiumEntityEventMixinIntermediary.fireEntityWakedUpEvent(
                ConiumEventType.ENTITY_WAKED_UP,
                Caster.cast(this),
                getSleepingPos().orElse(null)
        );
    }

    @Inject(
            method = "setSprinting",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;addTransientModifier(Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V"),
            cancellable = true
    )
    public void onSetSprint(boolean sprinting, CallbackInfo ci) {
        if (!conium$canStartSprint()) {
            conium$setCanStartSprint(true);
            ci.cancel();
        }
    }
}
