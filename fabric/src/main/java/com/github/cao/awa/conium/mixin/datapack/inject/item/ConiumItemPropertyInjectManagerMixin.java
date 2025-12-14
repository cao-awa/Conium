package com.github.cao.awa.conium.mixin.datapack.inject.item;

import com.github.cao.awa.conium.datapack.inject.item.ConiumItemPropertyInjectManager;
import net.fabricmc.fabric.impl.resource.FabricResourceReloader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConiumItemPropertyInjectManager.class)
public abstract class ConiumItemPropertyInjectManagerMixin implements FabricResourceReloader {
    @Override
    public @NotNull Identifier fabric$getId() {
        // Use identifiable resource loader system to reload inject information in data packs.
        // Make more compatible with other mods.
        return Identifier.of("conium", "item_property_inject_manager");
    }
}
