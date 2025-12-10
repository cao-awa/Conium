package com.github.cao.awa.conium.mixin.datapack.script;

import com.github.cao.awa.conium.script.manager.ConiumScriptManager;
import net.fabricmc.fabric.impl.resource.FabricResourceReloader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConiumScriptManager.class)
public abstract class ConiumScriptManagerMixin implements FabricResourceReloader {
    @Override
    public @NotNull Identifier fabric$getId() {
        return Identifier.of("conium", "script_manager");
    }
}
