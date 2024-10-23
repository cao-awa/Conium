package com.github.cao.awa.conium.block.template;

import com.github.cao.awa.conium.block.ConiumBlock;
import com.github.cao.awa.conium.template.ConiumTemplate;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public abstract class ConiumBlockTemplate extends ConiumTemplate<ConiumBlock> {
    public ConiumBlockTemplate(@NotNull String name) {
        super(name);
    }

    public abstract void settings(@NotNull AbstractBlock.Settings settings);
}
