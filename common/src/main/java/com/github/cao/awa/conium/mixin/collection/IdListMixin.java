package com.github.cao.awa.conium.mixin.collection;

import com.github.cao.awa.conium.registry.extend.ConiumDynamicIdList;
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.util.collection.IdList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(IdList.class)
public abstract class IdListMixin<T> implements ConiumDynamicIdList<T> {
    @Unique
    private final List<T> dynamicList = CollectionFactor.arrayList();
    @Shadow
    @Final
    private List<T> list;
    @Shadow
    @Final
    private Reference2IntMap<T> idMap;
    @Shadow
    private int nextId;

    @Shadow
    public abstract void add(T value);

    @Override
    public void conium$clearDynamic() {
        for (T value : this.dynamicList) {
            this.list.remove(value);
            this.idMap.removeInt(value);
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
