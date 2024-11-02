package com.github.cao.awa.conium.item.template.bedrock.food

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.FoodComponent
import net.minecraft.component.type.UseRemainderComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockFoodTemplate() : ConiumItemTemplate(ConiumTemplates.BEDROCK_FOOD) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockFoodTemplate {
            if (element is JsonObject) {
                return ConiumBedrockFoodTemplate(element.asJsonObject, registryLookup)
            }
            throw IllegalArgumentException("minecraft:food must be a JSON object")
        }

        private fun createFoodComponent(template: ConiumBedrockFoodTemplate, jsonObject: JsonObject, registryLookup: WrapperLookup): FoodComponent {
            FoodComponent.Builder().let { it ->
                if (jsonObject.has("nutrition")) {
                    it.nutrition(jsonObject["nutrition"].asInt)
                }

                if (jsonObject.has("saturation_modifier")) {
                    it.saturationModifier(jsonObject["saturation_modifier"].asFloat)
                }

                if (jsonObject.has("can_always_eat") && jsonObject["can_always_eat"].asBoolean) {
                    it.alwaysEdible()
                }

                ConiumConsumableTemplate.createConvert(jsonObject, registryLookup, "using_converts_to") { remainder ->
                    template.useRemainder = remainder
                }

                return it.build()
            }
        }
    }

    private lateinit var foodComponent: FoodComponent
    private var useRemainder: ItemStack = ItemStack.EMPTY

    constructor(element: JsonElement, registryLookup: WrapperLookup) : this() {
        this.foodComponent = createFoodComponent(this, element.asJsonObject, registryLookup)
    }

    override fun settings(settings: Item.Settings) {
        // Set food component
        settings.food(this.foodComponent)

        // Set using convert to stack.
        this.useRemainder.let {
            settings.component(DataComponentTypes.USE_REMAINDER, UseRemainderComponent(it))
        }
    }
}
