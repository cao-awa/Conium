package com.github.cao.awa.conium.item.template;

import com.github.cao.awa.conium.template.ConiumTemplate;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public abstract class ConiumItemTemplate extends ConiumTemplate {
    public ConiumItemTemplate(@NotNull String name) {
        super(name);
    }

    public abstract void settings(@NotNull Item.Settings settings);
}
