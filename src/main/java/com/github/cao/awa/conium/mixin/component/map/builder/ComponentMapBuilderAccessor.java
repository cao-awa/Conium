package com.github.cao.awa.conium.mixin.component.map.builder;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ComponentMap.Builder.class)
public interface ComponentMapBuilderAccessor {
    @Accessor
    Reference2ObjectMap<ComponentType<?>, Object> getComponents();
}
