//package com.github.cao.awa.conium.item.template.consumable
//
//import com.github.cao.awa.conium.item.ConiumItem
//import com.github.cao.awa.conium.item.template.ConiumItemTemplate
//import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate
//import com.github.cao.awa.conium.template.ConiumTemplates
//import com.google.gson.JsonElement
//import com.google.gson.JsonObject
//import com.mojang.serialization.JsonOps
//import net.minecraft.component.DataComponentTypes
//import net.minecraft.component.type.ConsumableComponent
//import net.minecraft.component.type.ConsumableComponents.*
//import net.minecraft.component.type.UseRemainderComponent
//import net.minecraft.item.Item
//import net.minecraft.item.ItemStack
//import net.minecraft.item.consume.ApplyEffectsConsumeEffect
//import net.minecraft.registry.Registries
//import net.minecraft.registry.RegistryOps
//import net.minecraft.registry.RegistryWrapper.WrapperLookup
//import net.minecraft.util.Identifier
//import java.util.*
//
//class ConiumConsumableTemplate(presetConsumableComponent: ConsumableComponent?) : ConiumItemTemplate(ConiumTemplates.CONSUMABLE) {
//    companion object {
//        @JvmStatic
//        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumConsumableTemplate {
//            if (element is JsonObject) {
//                return ConiumConsumableTemplate(element, registryLookup)
//            }
//
//            val preset = when (val presetName = element.asString) {
//                "food" -> FOOD
//                "drink" -> DRINK
//                "honey_bottle" -> HONEY_BOTTLE
//                "ominous_bottle" -> OMINOUS_BOTTLE
//                "dried_kelp" -> DRIED_KELP
//                "raw_chicken" -> RAW_CHICKEN
//                "enchanted_golden_apple" -> ENCHANTED_GOLDEN_APPLE
//                "golden_apple" -> GOLDEN_APPLE
//                "poisonous_potato" -> POISONOUS_POTATO
//                "pufferfish" -> PUFFERFISH
//                "rotten_flesh" -> ROTTEN_FLESH
//                "spider_eye" -> SPIDER_EYE
//                "milk_bucket" -> MILK_BUCKET
//                "chorus_fruit" -> CHORUS_FRUIT
//                else -> {
//                    throw IllegalArgumentException("No preset consumable component that named by '$presetName'")
//                }
//            }
//
//            return ConiumConsumableTemplate(preset)
//        }
//
//        private fun createFoodComponent(
//            template: ConiumConsumableTemplate,
//            jsonObject: JsonObject,
//            registryLookup: WrapperLookup
//        ): ConsumableComponent {
//            return ConsumableComponent.builder().also {
//                if (jsonObject.has("convert_to")) {
//                    jsonObject["convert_to"].let { convert ->
//                        if (convert.isJsonObject) {
//                            ItemStack.CODEC.parse(registryLookup.getOps(JsonOps.INSTANCE), convert.asJsonObject).orThrow
//                        } else {
//                            ItemStack(Registries.ITEM.get(Identifier.of(convert.asString)), 1)
//                        }.also { remainder ->
//                            template.useRemainder = remainder
//                        }
//                    }
//                }
//
//                if (jsonObject.has("effects")) {
//                    jsonObject["effects"].let { convert ->
//                        if (convert.isJsonArray) {
//                            val ops: RegistryOps<JsonElement> = registryLookup.getOps(JsonOps.INSTANCE)
//                            ApplyEffectsConsumeEffect.CODEC.decoder()
//                                .decode(ops, jsonObject).orThrow.first.let { effects ->
//                                it.consumeEffect(effects)
//                            }
//                        }
//                    }
//                }
//            }.build()
//        }
//    }
//
//    lateinit var consumableComponent: ConsumableComponent
//    var useRemainder: ItemStack = ItemStack.EMPTY
//
//    init {
//        presetConsumableComponent?.let {
//            this.consumableComponent = it
//        }
//    }
//
//    constructor(element: JsonElement, registryLookup: WrapperLookup) : this(null) {
//        this.consumableComponent = createFoodComponent(this, element.asJsonObject, registryLookup)
//    }
//
//    override fun attach(item: ConiumItem) {
//
//    }
//
//    override fun complete(item: ConiumItem) {
//
//    }
//
//    override fun settings(settings: Item.Settings) {
//        settings.component(DataComponentTypes.CONSUMABLE, this.consumableComponent)
//
//        this.useRemainder.let {
//            settings.component(DataComponentTypes.USE_REMAINDER, UseRemainderComponent(it))
//        }
//    }
//}
