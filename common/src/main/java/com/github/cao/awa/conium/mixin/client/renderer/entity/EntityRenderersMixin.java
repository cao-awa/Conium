package com.github.cao.awa.conium.mixin.client.renderer.entity;

import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EntityRenderers.class)
public class EntityRenderersMixin {
    @Unique
    private static EntityRendererFactory.Context currentContext = null;

    @Inject(
            method = "reloadEntityRenderers",
            at = @At("HEAD")
    )
    private static void reloadEntityRenderersContext(EntityRendererFactory.Context ctx, CallbackInfoReturnable<Map<EntityType<?>, EntityRenderer<?, ?>>> cir) {
        currentContext = ctx;
    }

    @Inject(
            method = "reloadEntityRenderers",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void reloadEntityRenderers(EntityRendererFactory.Context ctx, CallbackInfoReturnable<Map<EntityType<?>, EntityRenderer<?, ?>>> cir) {
        ImmutableMap.Builder<EntityType<?>, EntityRenderer<?, ?>> builder = ImmutableMap.builder();

        ConiumEntityRenderers.renderers.forEach((entityType, factory) -> {
            try {
                builder.put(entityType, factory.create(currentContext));
            } catch (Exception var5) {
                throw new IllegalArgumentException("Failed to create model for " + Registries.ENTITY_TYPE.getId(entityType), var5);
            }
        });
        builder.putAll(cir.getReturnValue());

        cir.setReturnValue(builder.build());
    }
}
