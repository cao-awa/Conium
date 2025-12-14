package com.github.cao.awa.conium.mixin.datapack.entity;

import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager;
import net.fabricmc.fabric.impl.resource.FabricResourceReloader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConiumEntityManager.class)
public abstract class ConiumEntityManagerMixin implements FabricResourceReloader {
    @Override
    public @NotNull Identifier fabric$getId() {
        // Use identifiable resource loader system to reload entity in data packs.
        // Make more compatible with other mods.
        return Identifier.of("conium", "entity_manager");
    }
}
