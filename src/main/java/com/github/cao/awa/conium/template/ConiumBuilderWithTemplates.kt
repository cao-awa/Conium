package com.github.cao.awa.conium.template

import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

abstract class ConiumBuilderWithTemplates<R : ConiumBuilderWithTemplates<R, I, X, T>, I, X, T> {
    private val builder: (R, I?) -> X

    constructor(xBuilder: (R, I) -> X) {
        this.builder = { r: R, i: I? -> xBuilder(r, i!!) }
    }

    constructor(builder: (R) -> X) {
        this.builder = { r: R, _: I? -> builder.invoke(r) }
    }

    val templates: MutableList<T> = CollectionFactor.arrayList()

    fun addTemplate(template: T): ConiumBuilderWithTemplates<R, I, X, T> {
        this.templates.add(template)
        return this
    }

    fun addTemplates(templates: List<T>): ConiumBuilderWithTemplates<R, I, X, T> {
        this.templates.addAll(templates)
        return this
    }

    fun build(input: I): X = this.builder(Manipulate.cast(this), input)

    fun build(): X = this.builder(Manipulate.cast(this), null)
}
