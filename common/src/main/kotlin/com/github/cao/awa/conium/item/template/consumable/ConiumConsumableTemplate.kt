package com.github.cao.awa.conium.item.template.consumable

import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.exception.Exceptions.notSupported
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.kotlin.extent.json.ifString
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.CONSUMABLE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.component.Consumable
import net.minecraft.world.item.component.Consumables.*
import net.minecraft.world.item.component.UseRemainder
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemStackTemplate
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier

class ConiumConsumableTemplate(presetConsumableComponent: Consumable?) : ConiumItemTemplate(name = CONSUMABLE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumConsumableTemplate = element.objectOrString(
            {
                // Use constructor create.
                ConiumConsumableTemplate(it)
            }
        ) {
            // Use template create.
            ConiumConsumableTemplate(
                when (it) {
                    "food" -> DEFAULT_FOOD
                    "drink" -> DEFAULT_DRINK
                    "honey_bottle" -> HONEY_BOTTLE
                    "ominous_bottle" -> OMINOUS_BOTTLE
                    "dried_kelp" -> DRIED_KELP
                    "raw_chicken", "chicken" -> CHICKEN
                    "enchanted_golden_apple" -> ENCHANTED_GOLDEN_APPLE
                    "golden_apple" -> GOLDEN_APPLE
                    "poisonous_potato" -> POISONOUS_POTATO
                    "pufferfish" -> PUFFERFISH
                    "rotten_flesh" -> ROTTEN_FLESH
                    "spider_eye" -> SPIDER_EYE
                    "milk_bucket" -> MILK_BUCKET
                    "chorus_fruit" -> CHORUS_FRUIT
                    else -> {
                        illegalArgument(
                            "No preset consumable component that named by '$it'"
                        )
                    }
                }
            )
        }!!

        /**
         * Parse the convert result as a [ItemStackTemplate] instead of a [ItemStack].
         *
         * A template only records the item holder, the count and the component patch, it never touches
         * [net.minecraft.core.Holder.components]. That matters because this parsing happens while data packs
         * are being loaded, and vanilla item components are only bound later in that same reload, so building
         * an [ItemStack] here would fail with "Components not bound yet".
         * The stack gets materialized by [ItemStackTemplate.create] on the first actual use.
         */
        @JvmStatic
        fun createConvert(jsonObject: JsonObject, customKey: String, callback: (ItemStackTemplate) -> Unit) {
            jsonObject[customKey]?.let { convert ->
                // If got a JSON object, mean this convertible is a custom data item.
                if (convert.isJsonObject) {
                    ItemStackTemplate.CODEC.parse(JsonOps.INSTANCE, convert.asJsonObject).orThrow
                } else {
                    // Or else got a string mean this convertible is a item that refer to registered item as default data.
                    convert.ifString(
                        { identifier ->
                            val item: Item? = BuiltInRegistries.ITEM.getValue(Identifier.parse(identifier))
                            ItemStackTemplate(
                                item ?: illegalArgument("No item that named by '$identifier' can be used as convert result"),
                                1
                            )
                        },
                        // Cannot be other type of value because is no meaning.
                        notSupported()
                    )
                }
            }?.apply {
                callback(this)
            }
        }

        @JvmStatic
        private fun createFoodComponent(
            template: ConiumConsumableTemplate,
            jsonObject: JsonObject
        ): Consumable {
            return Consumable.builder().also { builder ->
                createConvert(jsonObject, "convert_to") { remainder ->
                    template.useRemainder = remainder
                }

                jsonObject["apply_effects"]?.let { effects ->
                    effects.ifJsonObject(
                        {
                            val ops: JsonOps = JsonOps.INSTANCE
                            ApplyStatusEffectsConsumeEffect.CODEC.codec().decode(ops, it).orThrow.first.let { theEffects ->
                                builder.onConsume(theEffects)
                            }
                        },
                        notSupported()
                    )
                }
            }.build()
        }
    }

    private lateinit var consumableComponent: Consumable
    private var useRemainder: ItemStackTemplate? = null

    init {
        presetConsumableComponent?.let {
            this.consumableComponent = it
        }
    }

    constructor(jsonObject: JsonObject) : this(null) {
        this.consumableComponent = createFoodComponent(this, jsonObject)
    }

    override fun settings(settings: Item.Properties) {
        settings.component(DataComponents.CONSUMABLE, this.consumableComponent)

        this.useRemainder?.let {
            settings.component(DataComponents.USE_REMAINDER, UseRemainder(it))
        }
    }
}
