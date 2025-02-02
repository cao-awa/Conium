package com.github.cao.awa.conium.mixin.entity.living;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public abstract class LivingEntityAccessor {
    @Accessor("SPRINTING_SPEED_BOOST")
    public static EntityAttributeModifier getAttributeSprintingSpeedBoost() {
        throw new AssertionError();
    }
}
