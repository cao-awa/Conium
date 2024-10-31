package com.github.cao.awa.conium.mixin.item.setting;

import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Settings.class)
public interface ItemSettingsAccessor {
    @Accessor
    ComponentMap.Builder getComponents();
}
