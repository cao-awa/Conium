@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.template

import com.github.cao.awa.conium.block.entity.template.ConiumBlockEntityTemplate
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*
import kotlin.reflect.KClass

abstract class ConiumTemplate<R, P>(val isClient: Boolean = false, private val name: String, val conflicts: Map<Class<out ConiumTemplate<*, *>>, String> = CollectionFactor.hashMap()) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumTemplate")
        private val templates: MutableMap<String, ConiumTemplateCreator> = CollectionFactor.hashMap()

        fun count(): Int = this.templates.size

        fun bedrockCount(): Int = this.templates.values.filter(ConiumTemplateCreator::isBedrock).size

        fun coniumCount(): Int = this.templates.values.filter(ConiumTemplateCreator::notBedrock).size

        fun templates(): MutableMap<String, ConiumTemplateCreator> = Collections.unmodifiableMap(this.templates)

        @JvmStatic
        fun registerItem(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "item", template, isBedrock)
        }

        @JvmStatic
        fun registerBlock(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "block", template, isBedrock)
        }

        @JvmStatic
        fun registerBlockEntity(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "block_entity", template, isBedrock)
        }

        @JvmStatic
        fun registerEntity(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "entity", template, isBedrock)
        }

        @JvmStatic
        fun registerRecipe(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "recipe", template, isBedrock)
        }

        @JvmStatic
        fun register(name: String, subtype: String, template: ConiumTemplateFactor, isBedrock: Boolean) {
            this.templates["$name:$subtype"] = ConiumTemplateCreator(name, subtype, template, isBedrock)
        }

        fun deserializeItemTemplates(
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): MutableList<ConiumItemTemplate> = deserializeTemplates("item", json, registryLookup) as MutableList<ConiumItemTemplate>

        fun deserializeBlockTemplates(
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): MutableList<ConiumBlockTemplate> = deserializeTemplates("block", json, registryLookup) as MutableList<ConiumBlockTemplate>

        fun deserializeBlockEntityTemplates(
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): MutableList<ConiumBlockEntityTemplate> = deserializeTemplates("block_entity", json, registryLookup) as MutableList<ConiumBlockEntityTemplate>

        fun deserializeEntityTemplates(
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): MutableList<ConiumEntityTemplate> = deserializeTemplates("entity", json, registryLookup) as MutableList<ConiumEntityTemplate>

        fun deserializeRecipeTemplates(
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): MutableList<ConiumRecipeTemplate<Recipe<*>>> = deserializeTemplates("recipe", json, registryLookup) as MutableList<ConiumRecipeTemplate<Recipe<*>>>

        fun deserializeItemTemplate(
            name: String,
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): Result<ConiumItemTemplate> = deserializeTemplate(name, "item", json, registryLookup) as Result<ConiumItemTemplate>

        fun deserializeBlockTemplate(
            name: String,
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): Result<ConiumBlockTemplate> = deserializeTemplate(name, "block", json, registryLookup) as Result<ConiumBlockTemplate>

        fun deserializeEntityTemplate(
            name: String,
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): Result<ConiumEntityTemplate> = deserializeTemplate(name, "entity", json, registryLookup) as Result<ConiumEntityTemplate>

        fun deserializeRecipeTemplate(
            name: String,
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): Result<ConiumRecipeTemplate<Recipe<*>>> = deserializeTemplate(name, "recipe", json, registryLookup) as Result<ConiumRecipeTemplate<Recipe<*>>>

        fun deserializeTemplates(
            subtype: String,
            json: JsonObject,
            registryLookup: RegistryWrapper.WrapperLookup,
        ): MutableList<ConiumTemplate<*, *>> {
            // Templates list.
            val templates: MutableList<ConiumTemplate<*, *>> = CollectionFactor.arrayList()

            // Make sharing context used to share data.
            val sharingContext: HashMap<Class<*>, Any> = CollectionFactor.hashMap()

            // Create all templates (also known as 'components' in bedrock).
            for ((name: String, value: JsonElement) in json.entrySet()) {
                // Deserialize template content.
                deserializeTemplate(
                    name,
                    subtype,
                    value,
                    registryLookup
                ).let { result ->
                    // Complete the template.
                    result.fold({
                        // Set sharing context used to share data when the stage named 'attach', and 'complete' or other.
                        it.sharingContext = sharingContext
                        // Add to the template list.
                        templates.add(it)
                    }) {
                        // When errors.
                        LOGGER.error(it.message, it)
                    }
                }
            }

            return templates
        }

        fun deserializeTemplate(name: String, subtype: String, json: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): Result<ConiumTemplate<*, *>> {
            return this.templates["$name:$subtype"]?.createResult(json, registryLookup) ?: throw IllegalArgumentException("Unable to deserialize template '$name:$subtype' because it does not exist")
        }

        // Attention to duration, this duration value in bedrock is seconds instead of ticks in bedrock.
        fun secondsToTicks(duration: Float): Int = (duration * 20).int

        fun <R> notSupported(): (JsonElement) -> R = { throw notSupported(it) }

        fun notSupported(jsonElement: JsonElement): IllegalArgumentException = IllegalArgumentException("Not supported syntax: $jsonElement")

        @Throws(IllegalArgumentException::class)
        fun throwNotSupported(jsonElement: JsonElement): IllegalArgumentException = throw IllegalArgumentException("Not supported syntax: $jsonElement")

        fun createItemStack(jsonObject: JsonObject, name: String): ItemStack {
            return jsonObject[name]!!.let { result: JsonElement ->
                val count: Int
                val resultItemName: String

                if (result is JsonObject) {
                    count = ConiumItemTemplate.validateStackSize(result["count"]?.asInt ?: 1)
                    resultItemName = result["item"].asString
                } else {
                    count = 1
                    resultItemName = result.asString
                }

                ItemStack(
                    Registries.ITEM.get(Identifier.of(resultItemName)),
                    count
                )
            }
        }
    }

    // The contexts will be set in deserializing templates, do not set it again in feature.
    private lateinit var sharingContext: Map<Class<*>, Any>

    // Use 'sharedContext' to delegate because reassignment shared contexts are unexpected.
    // So only allow getting, instead to set it.
    val sharedContext: Map<Class<*>, Any> get() = this.sharingContext

    fun <T : Any> getContext(clazz: KClass<T>): T? = getContext(clazz.java)

    fun <T> getContext(clazz: Class<T>): T? = this.sharedContext[clazz].doCast()

    fun name(): String = this.name

    abstract fun attach(target: R)

    abstract fun complete(target: R)

    open fun finish(target: R) {
        // Do nothing here.
    }

    open fun change(target: R) {
        // Do nothing here.
    }

    open fun prepare(target: P) {
        // Do nothing here.
    }

    open fun results(): List<R> = listOf(result())

    open fun result(): R = throw IllegalStateException("The template ${this.javaClass.simpleName} has no result")
}
