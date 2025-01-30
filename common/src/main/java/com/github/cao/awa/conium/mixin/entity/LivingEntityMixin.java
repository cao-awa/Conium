package com.github.cao.awa.conium.mixin.entity;

import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.mixin.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract Optional<BlockPos> getSleepingPosition();

    @Inject(
            method = "damage",
            at = @At("HEAD"),
            cancellable = true
    )
    public void damage(ServerWorld world, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        // Trigger entity damaged event.
        if (ConiumEntityEventMixinIntermediary.fireEntityDamageEvent(
                ConiumEventType.ENTITY_DAMAGE,
                Manipulate.cast(this),
                damageSource,
                amount
        )) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "applyDamage",
            at = @At("RETURN")
    )
    public void damaged(ServerWorld world, DamageSource damageSource, float amount, CallbackInfo ci) {
        // Trigger entity damaged event.
        ConiumEntityEventMixinIntermediary.fireEntityDamageEvent(
                ConiumEventType.ENTITY_DAMAGED,
                Manipulate.cast(this),
                damageSource,
                amount
        );
    }

    @Inject(
            method = "onDeath",
            at = @At("HEAD"),
            cancellable = true
    )
    public void dying(DamageSource damageSource, CallbackInfo ci) {
        // Trigger entity dying event.
        if (ConiumEntityEventMixinIntermediary.fireEntityDyeEvent(
                ConiumEventType.ENTITY_DIE,
                Manipulate.cast(this),
                damageSource
        )) {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "onDeath",
            at = @At("RETURN")
    )
    public void dead(DamageSource damageSource, CallbackInfo ci) {
        // Trigger entity dead event.
        ConiumEntityEventMixinIntermediary.fireEntityDyeEvent(
                ConiumEventType.ENTITY_DEAD,
                Manipulate.cast(this),
                damageSource
        );
    }

    @Inject(
            method = "sleep",
            at = @At("HEAD"),
            cancellable = true
    )
    public void trySleep(BlockPos pos, CallbackInfo ci) {
        // Trigger entity wake-up event.
        if (ConiumEntityEventMixinIntermediary.fireEntityRestEvent(
                ConiumEventType.ENTITY_TRY_SLEEP,
                Manipulate.cast(this),
                pos
        )) {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "sleep",
            at = @At("RETURN")
    )
    public void sleep(BlockPos pos, CallbackInfo ci) {
        // Trigger entity wake-up event.
        ConiumEntityEventMixinIntermediary.fireEntityRestEvent(
                ConiumEventType.ENTITY_SLEEP,
                Manipulate.cast(this),
                pos
        );
    }

    @Inject(
            method = "wakeUp",
            at = @At("HEAD"),
            cancellable = true
    )
    public void wakeUp(CallbackInfo ci) {
        // Trigger entity wake-up event.
        if (ConiumEntityEventMixinIntermediary.fireEntityRestEvent(
                ConiumEventType.ENTITY_WAKE_UP,
                Manipulate.cast(this),
                getSleepingPosition().orElse(null)
        )) {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "wakeUp",
            at = @At("HEAD")
    )
    public void wakedUp(CallbackInfo ci) {
        // Trigger entity waked up event.
        ConiumEntityEventMixinIntermediary.fireEntityRestEvent(
                ConiumEventType.ENTITY_WAKED_UP,
                Manipulate.cast(this),
                getSleepingPosition().orElse(null)
        );
    }
}
