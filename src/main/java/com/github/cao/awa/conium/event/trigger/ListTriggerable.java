package com.github.cao.awa.conium.event.trigger;

import com.github.cao.awa.conium.parameter.ParameterSelective;
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class ListTriggerable<P extends ParameterSelective> {
    private final Map<Object, List<P>> triggers = CollectionFactor.hashMap();

    public Map<Object, List<P>> triggers() {
        return this.triggers;
    }

    public boolean hasAny(Object identity, Predicate<P> parameters) {
        boolean has = false;
        for (P trigger : this.triggers.getOrDefault(identity, Collections.emptyList())) {
            has |= parameters.test(trigger);
        }
        return has;
    }

    public boolean noFailure(Object identity, Predicate<P> parameters) {
        boolean has = true;
        for (P trigger : this.triggers.getOrDefault(identity, Collections.emptyList())) {
            has &= parameters.test(trigger);
        }
        return has;
    }

    public ListTriggerable<P> subscribe(Object identity, P trigger) {
        this.triggers.computeIfAbsent(identity, (key) -> CollectionFactor.arrayList()).add(trigger);
        return this;
    }

    public void clearSubscribes() {
        this.triggers.clear();
    }
}
