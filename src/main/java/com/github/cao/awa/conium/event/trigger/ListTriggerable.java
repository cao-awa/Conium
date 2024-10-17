package com.github.cao.awa.conium.event.trigger;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.conium.parameter.ParameterSelective;
import com.github.cao.awa.conium.parameter.ParameterSelective1;
import net.minecraft.item.ItemUsageContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class ListTriggerable<P extends ParameterSelective> {
    private final Map<Object, List<P>> triggers = ApricotCollectionFactor.hashMap();

    public Map<Object, List<P>> triggers() {
        return this.triggers;
    }

    public boolean hasAny(Object identity, Predicate<P> parameters) {
        boolean has = false;
        System.out.println(identity);
        for (P trigger : this.triggers.getOrDefault(identity, Collections.emptyList())) {
            has |= parameters.test(trigger);
        }
        return has;
    }

    public ListTriggerable<P> subscribe(Object identity, P trigger) {
        this.triggers.computeIfAbsent(identity, (key) -> ApricotCollectionFactor.arrayList()).add(trigger);
        return this;
    }
}
