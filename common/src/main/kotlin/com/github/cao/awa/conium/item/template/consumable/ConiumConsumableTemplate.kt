package com.github.cao.awa.conium.item.template.consumable

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.ConiumTemplates.Item.CONSUMABLE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ConsumableComponent
import net.minecraft.component.type.ConsumableComponents.*
import net.minecraft.component.type.UseRemainderComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.consume.ApplyEffectsConsumeEffect
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

class ConiumConsumableTemplate(presetConsumableComponent: ConsumableComponent?) : ConiumItemTemplate(name = CONSUMABLE) {
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
                    "food" -> FOOD
                    "drink" -> DRINK
                    "honey_bottle" -> HONEY_BOTTLE
                    "ominous_bottle" -> OMINOUS_BOTTLE
                    "dried_kelp" -> DRIED_KELP
                    "raw_chicken" -> RAW_CHICKEN
                    "enchanted_golden_apple" -> ENCHANTED_GOLDEN_APPLE
                    "golden_apple" -> GOLDEN_APPLE
                    "poisonous_potato" -> POISONOUS_POTATO
                    "pufferfish" -> PUFFERFISH
                    "rotten_flesh" -> ROTTEN_FLESH
                    "spider_eye" -> SPIDER_EYE
                    "milk_bucket" -> MILK_BUCKET
                    "chorus_fruit" -> CHORUS_FRUIT
                    else -> {
                        throw IllegalArgumentException("No preset consumable component that named by '$it'")
                    }
                }
            )
        }!!

        fun createConvert(jsonObject: JsonObject, customKey: String, callback: (ItemStack) -> Unit) {
            jsonObject[customKey]?.let { convert ->
                if (convert.isJsonObject) {
                    ItemStack.CODEC.parse(JsonOps.INSTANCE, convert.asJsonObject).orThrow
                } else {
                    ItemStack(Registries.ITEM.get(Identifier.of(convert.asString)), 1)
                }
            }?.apply {
                callback(this)
            }
        }

        private fun createFoodComponent(
            template: ConiumConsumableTemplate,
            jsonObject: JsonObject
        ): ConsumableComponent {
            return ConsumableComponent.builder().also {
                createConvert(jsonObject, "convert_to") { remainder ->
                    template.useRemainder = remainder
                }

                jsonObject["apply_effects"]?.let { effects ->
                    if (effects.isJsonObject) {
                        val ops: JsonOps = JsonOps.INSTANCE
                        ApplyEffectsConsumeEffect.CODEC.decoder()
                            .decode(ops, effects).orThrow.first.let { theEffects ->
                                it.consumeEffect(theEffects)
                            }
                    } else {
                        throwNotSupported(effects)
                    }
                }
            }.build()
        }
    }

    private lateinit var consumableComponent: ConsumableComponent
    private var useRemainder: ItemStack = ItemStack.EMPTY

    init {
        presetConsumableComponent?.let {
            this.consumableComponent = it
        }
    }

    constructor(jsonObject: JsonObject) : this(null) {
        this.consumableComponent = createFoodComponent(this, jsonObject)
    }

    override fun settings(settings: Item.Settings) {
        settings.component(DataComponentTypes.CONSUMABLE, this.consumableComponent)

        this.useRemainder.let {
            settings.component(DataComponentTypes.USE_REMAINDER, UseRemainderComponent(it))
        }
    }
}
