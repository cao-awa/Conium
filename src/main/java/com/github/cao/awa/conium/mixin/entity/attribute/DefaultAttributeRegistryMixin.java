package com.github.cao.awa.conium.mixin.entity.attribute;

import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultAttributeRegistry.class)
public class DefaultAttributeRegistryMixin {
    @Inject(
            method = "get",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void get(EntityType<? extends LivingEntity> type, CallbackInfoReturnable<DefaultAttributeContainer> cir) {
        if (cir.getReturnValue() == null) {
            cir.setReturnValue(ConiumEntityAttributeRegistry.attributes.get(type));
        }
    }

    @Inject(
            method = "hasDefinitionFor",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void hasDefinitionFor(EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            cir.setReturnValue(ConiumEntityAttributeRegistry.attributes.containsKey(type));
        }
    }
}
