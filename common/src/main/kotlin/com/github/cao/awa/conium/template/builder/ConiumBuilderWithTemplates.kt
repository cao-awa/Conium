package com.github.cao.awa.conium.template.builder

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.collections.iterator

abstract class ConiumBuilderWithTemplates<B : ConiumBuilderWithTemplates<B, I, X, T>, I, X, T: ConiumTemplate<*, *>> {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumTemplatesBuilder")
    }

    private val builder: (B, I?) -> X
    val templates: MutableMap<Class<out T>, T> = CollectionFactor.hashMap()

    constructor(xBuilder: (B, I) -> X) {
        this.builder = { builder: B, input: I? -> xBuilder(builder, input!!) }
    }

    constructor(xBuilder: (B) -> X) {
        this.builder = { builder: B, _: I? -> xBuilder.invoke(builder) }
    }

    fun addTemplate(template: T): ConiumBuilderWithTemplates<B, I, X, T> {
        this.templates[template::class.java] = template
        return this
    }

    fun addTemplates(templates: List<T>): ConiumBuilderWithTemplates<B, I, X, T> {
        templates.forEach(::addTemplate)
        return this
    }

    fun distinct() {
        for ((_, template) in CollectionFactor.hashMap(this.templates)) {
            for ((conflictType, notice) in template.conflicts) {
                if (this.templates.containsKey(conflictType)) {
                    this.templates.remove(conflictType)
                    LOGGER.warn(notice)
                }
            }
        }
    }

    fun forEachTemplate(action: (T) -> Unit) {
        this.templates.values.forEach(action)
    }

    fun templates(): MutableList<T> = this.templates.values.toMutableList()

    fun build(input: I): X = this.builder(doCast(), input)

    fun build(): X = this.builder(doCast(), null)
}