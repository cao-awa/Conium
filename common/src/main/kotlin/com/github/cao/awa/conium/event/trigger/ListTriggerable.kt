package com.github.cao.awa.conium.event.trigger

import com.github.cao.awa.conium.kotlin.extend.predicate.test
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import java.util.function.Predicate

abstract class ListTriggerable<P : ParameterSelective> {
    private val triggers: MutableMap<Any, MutableList<P>> = CollectionFactor.hashMap()
    private val constantTriggers: MutableList<P> = CollectionFactor.arrayList()

    fun triggers(): Map<Any, MutableList<P>> = this.triggers

    fun constantTriggers(): MutableList<P> = this.constantTriggers

    fun hasAny(identity: Any?, parameters: Predicate<P>): Boolean {
        var has = false

        for (trigger in this.constantTriggers) {
            has = has or (parameters test trigger)
        }

        for (trigger in this.triggers.getOrDefault(identity, emptyList())) {
            has = has or (parameters test trigger)
        }

        return has
    }

    fun noFailure(identity: Any?, parameters: Predicate<P>): Boolean {
        var has = true

        for (trigger in this.constantTriggers) {
            has = has and (parameters test trigger)
        }

        for (trigger in this.triggers.getOrDefault(identity, emptyList())) {
            has = has and (parameters test trigger)
        }
        return has
    }

    fun subscribe(identity: Any, trigger: P): ListTriggerable<P> {
        this.triggers.computeIfAbsent(identity) {
            CollectionFactor.arrayList()
        }.add(trigger)
        return this
    }

    fun subscribe(trigger: P): ListTriggerable<P> {
        this.constantTriggers.add(trigger)
        return this
    }

    fun clearSubscribes() = this.triggers.clear()
}
