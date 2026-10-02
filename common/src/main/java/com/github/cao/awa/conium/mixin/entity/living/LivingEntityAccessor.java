package com.github.cao.awa.conium.mixin.entity.living;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public abstract class LivingEntityAccessor {
    @Accessor("SPEED_MODIFIER_SPRINTING")
    public static AttributeModifier getAttributeSprintingSpeedBoost() {
        throw new AssertionError();
    }
}
