package com.github.cao.awa.conium.mixin.entity.attribute;

import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultAttributes.class)
public class DefaultAttributeRegistryMixin {
    @Inject(
            method = "getSupplier",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void getSupplier(EntityType<? extends LivingEntity> type, CallbackInfoReturnable<AttributeSupplier> cir) {
        if (cir.getReturnValue() == null) {
            cir.setReturnValue(ConiumEntityAttributeRegistry.attributes.get(type));
        }
    }

    @Inject(
            method = "hasSupplier",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void hasSupplier(EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            cir.setReturnValue(ConiumEntityAttributeRegistry.attributes.containsKey(type));
        }
    }
}
