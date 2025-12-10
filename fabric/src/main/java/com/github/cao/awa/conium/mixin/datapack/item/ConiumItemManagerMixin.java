package com.github.cao.awa.conium.mixin.datapack.item;

import com.github.cao.awa.conium.datapack.item.ConiumItemManager;
import net.fabricmc.fabric.impl.resource.FabricResourceReloader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConiumItemManager.class)
public abstract class ConiumItemManagerMixin implements FabricResourceReloader {
    @Override
    public @NotNull Identifier fabric$getId() {
        return Identifier.of("conium", "item_manager");
    }
}
