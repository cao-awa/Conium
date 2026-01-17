@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.template

import com.github.cao.awa.conium.blockentity.template.ConiumBlockEntityTemplate
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.exception.syntax.SyntaxNotSupportedException
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.innate.int
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.builder.factor.ConiumTemplateCreator
import com.github.cao.awa.conium.template.builder.factor.ConiumTemplateFactor
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*
import kotlin.jvm.Throws
import kotlin.reflect.KClass

abstract class ConiumTemplate<R, P>(
    val isClient: Boolean = false,
    private val name: String,
    val conflicts: Map<Class<out ConiumTemplate<*, *>>, String> = CollectionFactor.hashMap()
) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumTemplate")
        private val templates: MutableMap<String, ConiumTemplateCreator> = CollectionFactor.hashMap()

        /**
         * Return count of registered templates.
         *
         * @return Count of all templates
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        fun count(): Int = this.templates.size

        /**
         * Return count of registered bedrock templates.
         *
         * @return Count of bedrock templates
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        fun bedrockCount(): Int = this.templates.values.filter(ConiumTemplateCreator::isBedrock).size

        /**
         * Return count of registered non-bedrock templates。
         *
         * @return Count of Conium templates
         */
        fun coniumCount(): Int = this.templates.values.filter(ConiumTemplateCreator::notBedrock).size

        /**
         * Return all templates registered (read-only) .
         *
         * @return All templates map
         */
        fun templates(): MutableMap<String, ConiumTemplateCreator> = Collections.unmodifiableMap(this.templates)

        /**
         * Register an item template factory.
         *
         * @param name Template name
         * @param template Template factory
         * @param isBedrock Marking of bedrock template
         */
        @JvmStatic
        fun registerItem(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "item", template, isBedrock)
        }

        /**
         * Register a block template factory.
         *
         * @param name Template name
         * @param template Template factory
         * @param isBedrock Marking of bedrock template
         */
        @JvmStatic
        fun registerBlock(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "block", template, isBedrock)
        }

        /**
         * Register a block entity template factory.
         *
         * @param name Template name
         * @param template Template factory
         * @param isBedrock Marking of bedrock template
         */
        @JvmStatic
        fun registerBlockEntity(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "block_entity", template, isBedrock)
        }

        /**
         * Register an entity template factory.
         *
         * @param name Template name
         * @param template Template factory
         * @param isBedrock Marking of bedrock template
         */
        @JvmStatic
        fun registerEntity(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "entity", template, isBedrock)
        }

        /**
         * Register a recipe template factory.
         *
         * @param name Template name
         * @param template Template factory
         * @param isBedrock Marking of bedrock template
         */
        @JvmStatic
        fun registerRecipe(name: String, template: ConiumTemplateFactor, isBedrock: Boolean = false) {
            register(name, "recipe", template, isBedrock)
        }

        /**
         * Register a template factory.
         *
         * @param name Template name
         * @param subtype Template subtype (like item, block ETC.) .
         * @param template Template factory
         * @param isBedrock Marking of bedrock template
         */
        @JvmStatic
        fun register(name: String, subtype: String, template: ConiumTemplateFactor, isBedrock: Boolean) {
            this.templates["$name:$subtype"] = ConiumTemplateCreator(name, subtype, template, isBedrock)
        }

        /**
         * Deserialize all item templates from JSON.
         *
         * @param json JSON object, key is the name, value is the template content
         *
         * @return Deserialized item templates list
         */
        fun deserializeItemTemplates(
            json: JsonObject
        ): MutableList<ConiumItemTemplate> = deserializeTemplates("item", json, ConiumItemTemplate::class)

        /**
         * Deserialize all block templates from JSON.
         *
         * @param json JSON object, key is the name, value is the template content
         *
         * @return Deserialized block templates list
         */
        fun deserializeBlockTemplates(
            json: JsonObject
        ): MutableList<ConiumBlockTemplate> = deserializeTemplates("block", json, ConiumBlockTemplate::class)

        /**
         * Deserialize all block entity templates from JSON.
         *
         * @param json JSON object, key is the name, value is the template content
         *
         * @return Deserialized block entity templates list
         */
        fun deserializeBlockEntityTemplates(
            json: JsonObject
        ): MutableList<ConiumBlockEntityTemplate> =
            deserializeTemplates("block_entity", json, ConiumBlockEntityTemplate::class)

        /**
         * Deserialize all entity templates from JSON.
         *
         * @param json JSON object, key is the name, value is the template content
         *
         * @return Deserialized entity templates list
         */
        fun deserializeEntityTemplates(
            json: JsonObject
        ): MutableList<ConiumEntityTemplate> = deserializeTemplates("entity", json, ConiumEntityTemplate::class)

        /**
         * Deserialize all recipe templates from JSON.
         *
         * @param json JSON object, key is the name, value is the template content
         *
         * @return Deserialized recipe templates list
         */
        fun deserializeRecipeTemplates(
            json: JsonObject
        ): MutableList<ConiumRecipeTemplate<Recipe<*>>> =
            deserializeTemplates("recipe", json) as MutableList<ConiumRecipeTemplate<Recipe<*>>>

        /**
         * Deserialize item template from JSON.
         *
         * @param name Template name
         * @param json Template JSON object
         *
         * @return Item template
         */
        fun deserializeItemTemplate(
            name: String,
            json: JsonObject
        ): Result<ConiumItemTemplate> = deserializeTemplate(name, "item", json, ConiumItemTemplate::class)

        /**
         * Deserialize block template from JSON.
         *
         * @param name Template name
         * @param json Template JSON object
         *
         * @return Block template
         */
        fun deserializeBlockTemplate(
            name: String,
            json: JsonObject
        ): Result<ConiumBlockTemplate> = deserializeTemplate(name, "block", json, ConiumBlockTemplate::class)

        /**
         * Deserialize entity template from JSON.
         *
         * @param name Template name
         * @param json Template JSON object
         *
         * @return Entity template
         */
        fun deserializeEntityTemplate(
            name: String,
            json: JsonObject
        ): Result<ConiumEntityTemplate> = deserializeTemplate(name, "entity", json, ConiumEntityTemplate::class)

        /**
         * Deserialize recipe template from JSON.
         *
         * @param name Template name
         * @param json Template JSON object
         *
         * @return Recipe template
         */
        fun deserializeRecipeTemplate(
            name: String,
            json: JsonObject
        ): Result<ConiumRecipeTemplate<Recipe<*>>> = deserializeTemplate(name, "recipe", json) as Result<ConiumRecipeTemplate<Recipe<*>>>

        /**
         * Deserialize templates.
         *
         * @param subtype Template subtype
         * @param json JSON object
         * @param targetType Target type
         *
         * @return Templates deserialized
         */
        fun <T : ConiumTemplate<*, *>> deserializeTemplates(
            subtype: String,
            json: JsonObject,
            targetType: KClass<T>
        ): MutableList<T> = deserializeTemplates(subtype, json) as MutableList<T>

        fun deserializeTemplates(subtype: String, json: JsonObject): MutableList<ConiumTemplate<*, *>> {
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
                    value
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

        fun <T : ConiumTemplate<*, *>> deserializeTemplate(
            name: String, subtype: String,
            json: JsonElement,
            targetType: KClass<T>
        ): Result<T> = deserializeTemplate(name, subtype, json) as Result<T>

        fun deserializeTemplate(name: String, subtype: String, json: JsonElement): Result<ConiumTemplate<*, *>> {
            return this.templates["$name:$subtype"]?.createResult(json)
                ?: throw IllegalArgumentException("Unable to deserialize template '$name:$subtype' because it does not exist")
        }

        fun secondsToTicks(duration: Float): Int = (duration * 20).int

        /**
         * Supply a thrower to throws a syntax not supported exception with the unsupported JSON element.
         *
         * @param R placeholder type, never got a result actually
         *
         * @author cao_awa
         * @author 草二号机
         *
         * @since 1.0.0
         */
        @Throws(SyntaxNotSupportedException::class)
        fun <R> notSupported(): (JsonElement) -> R = {
            throw SyntaxNotSupportedException("Not supported syntax: $it")
        }

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

    open fun result(): R = throw IllegalStateException("The template '${this.javaClass.simpleName}' has no result")
}
