package com.github.cao.awa.conium.mixin.client.renderer.entity;

import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EntityRenderers.class)
public class EntityRendererFactoriesMixin {
    @Unique
    private static EntityRendererProvider.Context currentContext = null;

    @Inject(
            method = "createEntityRenderers",
            at = @At("HEAD")
    )
    private static void reloadEntityRenderersContext(EntityRendererProvider.Context ctx, CallbackInfoReturnable<Map<EntityType<?>, EntityRenderer<?, ?>>> cir) {
        currentContext = ctx;
    }

    @Inject(
            method = "createEntityRenderers",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void reloadEntityRenderers(EntityRendererProvider.Context ctx, CallbackInfoReturnable<Map<EntityType<?>, EntityRenderer<?, ?>>> cir) {
        ImmutableMap.Builder<EntityType<?>, EntityRenderer<?, ?>> builder = ImmutableMap.builder();

        ConiumEntityRenderers.renderers.forEach((entityType, factory) -> {
            try {
                builder.put(entityType, factory.create(currentContext));
            } catch (Exception var5) {
                throw new IllegalArgumentException("Failed to create model for " + BuiltInRegistries.ENTITY_TYPE.getKey(entityType), var5);
            }
        });
        builder.putAll(cir.getReturnValue());

        cir.setReturnValue(builder.build());
    }
}
