package com.github.cao.awa.conium.mixin.component.map.builder;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DataComponentMap.Builder.class)
public interface ComponentMapBuilderAccessor {
    @Accessor("map")
    Reference2ObjectMap<DataComponentType<?>, Object> getComponents();
}
