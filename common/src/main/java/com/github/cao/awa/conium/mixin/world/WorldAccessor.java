package com.github.cao.awa.conium.mixin.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(World.class)
public interface WorldAccessor {
    @Invoker("getEntityLookup")
    EntityLookup<Entity> getWorldEntityLookup();
    @Invoker("canHaveWeather")
    boolean canWorldHaveWeather();
    @Invoker("initWeatherGradients")
    void initWorldWeatherGradients();
    @Invoker("tickBlockEntities")
    void tickWorldBlockEntities();
}
