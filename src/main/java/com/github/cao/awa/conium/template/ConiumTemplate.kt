package com.github.cao.awa.conium.template

import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import java.util.function.BiFunction
import kotlin.reflect.KClass

abstract class ConiumTemplate<T, P>(private val name: String) {
    companion object {
        private val templates: MutableMap<String, BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*, *>>> = CollectionFactor.hashMap()

        @JvmStatic
        fun registerItem(name: String, template: BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*, *>>) {
            this.templates["$name:item"] = template
        }

        @JvmStatic
        fun registerBlock(name: String, template: BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*, *>>) {
            this.templates["$name:block"] = template
        }

        @JvmStatic
        fun registerEntity(name: String, template: BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*, *>>) {
            this.templates["$name:entity"] = template
        }

        @JvmStatic
        fun registerRecipe(name: String, template: BiFunction<JsonElement, WrapperLookup, ConiumTemplate<*, *>>) {
            this.templates["$name:recipe"] = template
        }

        fun deserializeItemTemplates(
            json: JsonObject,
            registryLookup: WrapperLookup,
            wrapper: (String) -> ConiumTemplate<*, *>? = { _ -> null }
        ): MutableList<ConiumTemplate<*, *>> = deserializeTemplates("item", json, registryLookup, wrapper)

        fun deserializeBlockTemplates(
            json: JsonObject,
            registryLookup: WrapperLookup,
            wrapper: (String) -> ConiumTemplate<*, *>? = { _ -> null }
        ): MutableList<ConiumTemplate<*, *>> = deserializeTemplates("block", json, registryLookup, wrapper)

        fun deserializeEntityTemplates(
            json: JsonObject,
            registryLookup: WrapperLookup,
            wrapper: (String) -> ConiumTemplate<*, *>? = { _ -> null }
        ): MutableList<ConiumTemplate<*, *>> = deserializeTemplates("entity", json, registryLookup, wrapper)

        fun deserializeRecipeTemplates(
            json: JsonObject,
            registryLookup: WrapperLookup,
            wrapper: (String) -> ConiumTemplate<*, *>? = { _ -> null }
        ): MutableList<ConiumTemplate<*, *>> = deserializeTemplates("recipe", json, registryLookup, wrapper)

        fun deserializeTemplates(
            subtype: String,
            json: JsonObject,
            registryLookup: WrapperLookup,
            wrapper: (String) -> ConiumTemplate<*, *>? = { _ -> null }
        ): MutableList<ConiumTemplate<*, *>> {
            // Templates list.
            val templates: MutableList<ConiumTemplate<*, *>> = CollectionFactor.arrayList()

            // Make sharing context used to shares data.
            val sharingContext = CollectionFactor.hashMap<Class<*>, Any>()

            // Complete the template.
            val completeTemplate: (ConiumTemplate<*, *>) -> Unit = {
                // Set sharing context used to shares data when stage 'attach', and 'complete' or other.
                it.sharingContext = sharingContext
                // Add to template list.
                templates.add(it)
            }

            // Create all templates(also known as 'components' in bedrock).
            for (entry in json.entrySet()) {
                val (name, value) = entry

                // Let wrapper create a attaching template when specially template is presents.
                wrapper(name)?.let(completeTemplate)

                // Deserialize template content.
                deserializeTemplate(
                    name,
                    subtype,
                    value,
                    registryLookup
                ).let(completeTemplate)
            }

            return templates
        }

        fun deserializeTemplate(name: String, subtype: String, json: JsonElement, registryLookup: WrapperLookup): ConiumTemplate<*, *> {
            return this.templates["$name:$subtype"]?.apply(json, registryLookup) ?: throw IllegalArgumentException("Unable to deserialize template '$name' because it does not exist")
        }

        // Attention to duration, this duration value in bedrock is seconds instead of ticks in bedrock.
        fun secondsToTicks(duration: Float): Int = (duration * 20).toInt()
    }

    // This contexts will be set in deserializing templates, do not set it again in feature.
    private lateinit var sharingContext: Map<Class<*>, Any>

    // Use 'sharedContext' to delegate because reassignment shared contexts is unexpected.
    // So only allow to get, instead to set it.
    val sharedContext: Map<Class<*>, Any> get() = this.sharingContext

    fun <T : Any> getContext(clazz: KClass<T>): T? = getContext(clazz.java)

    fun <T> getContext(clazz: Class<T>): T? = Manipulate.cast(this.sharedContext[clazz])

    fun name(): String = this.name

    abstract fun attach(target: T)

    abstract fun complete(target: T)

    open fun finish(target: T) {
        // Do nothing here.
    }

    open fun prepare(target: P) {
        // Do nothing here.
    }

    open fun results(): List<T> = listOf(result())

    open fun result(): T = throw IllegalStateException("The template ${this.javaClass.simpleName} has no result")
}
