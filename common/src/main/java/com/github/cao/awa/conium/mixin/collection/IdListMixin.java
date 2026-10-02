package com.github.cao.awa.conium.mixin.collection;

import com.github.cao.awa.conium.registry.extend.ConiumDynamicIdList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.IdMapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(IdMapper.class)
public abstract class IdListMixin<T> implements ConiumDynamicIdList<T> {
    @Unique
    private final List<T> dynamicList = new ArrayList<>();
    @Shadow
    @Final
    private List<T> idToT;
    @Shadow
    @Final
    private Reference2IntMap<T> tToId;
    @Shadow
    private int nextId;

    @Shadow
    public abstract void add(T value);

    @Override
    public void conium$clearDynamic() {
        for (T value : this.dynamicList) {
            this.idToT.remove(value);
            this.tToId.removeInt(value);
        }
        this.nextId -= this.dynamicList.size();
        this.dynamicList.clear();
    }

    @Override
    public void conium$add(T value) {
        add(value);
        this.dynamicList.add(value);
    }
}
