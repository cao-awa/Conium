package com.github.cao.awa.conium.mixin.datapack.worldgen;

import com.github.cao.awa.conium.datapack.worldgen.ConiumPlacedFeatureManager;
import net.fabricmc.fabric.impl.resource.FabricResourceReloader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConiumPlacedFeatureManager.class)
public abstract class ConiumPlacedFeatureManagerMixin implements FabricResourceReloader {
    @Override
    public @NotNull Identifier fabric$getId() {
        // Use identifiable resource loader system to reload placed feature in data packs.
        // Make more compatible with other mods.
        return Identifier.of("conium", "placed_feature_manager");
    }
}
