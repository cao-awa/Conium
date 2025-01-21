package com.github.cao.awa.conium.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor("dimensions")
    EntityDimensions dimensions();

    @Accessor("dimensions")
    void dimensions(EntityDimensions dimensions);
}
