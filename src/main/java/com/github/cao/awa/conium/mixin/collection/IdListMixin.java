package com.github.cao.awa.conium.mixin.collection;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.conium.extend.ConiumDynamicIdList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.util.collection.IdList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(IdList.class)
public abstract class IdListMixin<T> implements ConiumDynamicIdList<T> {
    @Shadow public abstract void add(T value);

    @Shadow @Final private List<T> list;
    @Shadow @Final private Reference2IntMap<T> idMap;
    @Shadow private int nextId;
    @Unique
    private final List<T> dynamicList = ApricotCollectionFactor.arrayList();

    @Override
    public void conium$clearDynamic() {
        for (T value : this.dynamicList) {
            this.list.remove(value);
            this.idMap.removeInt(value);
        }
        this.nextId -= this.dynamicList.size();
        this.dynamicList.clear();

        System.out.println("Next id: " + this.nextId);
    }

    @Override
    public void conium$add(T value) {
        add(value);
        this.dynamicList.add(value);
    }
}
