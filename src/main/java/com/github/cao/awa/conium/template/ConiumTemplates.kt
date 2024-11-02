package com.github.cao.awa.conium.template

//import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.action.ConiumUseActionTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumArmorTemplate
import com.github.cao.awa.conium.item.template.bedrock.animation.ConiumBedrockUseAnimationTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate
import com.github.cao.awa.conium.item.template.bedrock.food.ConiumBedrockFoodTemplate
import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.item.template.tool.axe.*
import com.github.cao.awa.conium.item.template.bedrock.damage.ConiumBedrockDamageTemplate
import com.github.cao.awa.conium.item.template.bedrock.destory.ConiumBedrockCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.bedrock.durability.ConiumBedrockDurabilityTemplate
import com.github.cao.awa.conium.item.template.bedrock.fuel.ConiumBedrockFuelTemplate
import com.github.cao.awa.conium.item.template.bedrock.glint.ConiumBedrockGlintTemplate
import com.github.cao.awa.conium.item.template.bedrock.rarity.ConiumBedrockRarityTemplate
import com.github.cao.awa.conium.item.template.bedrock.stack.size.ConiumBedrockMaxStackSizeTemplate
import com.github.cao.awa.conium.item.template.bedrock.wearable.ConiumBedrockWearableTemplate
import com.github.cao.awa.conium.item.template.fuel.ConiumFuelTemplate
import com.github.cao.awa.conium.item.template.glint.ConiumGlintTemplate
import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumCommonRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumEpicRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumRareRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumUncommonRarityTemplate
import com.github.cao.awa.conium.item.template.stack.count.ConiumStackMaxCountTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.*
import com.github.cao.awa.conium.recipe.template.bedrock.furnace.ConiumBedrockRecipeFurnaceTemplate
import com.github.cao.awa.conium.recipe.template.bedrock.shape.ConiumBedrockRecipeShapedTemplate
import com.github.cao.awa.conium.recipe.template.bedrock.shape.ConiumBedrockRecipeShapelessTemplate
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.register

object ConiumTemplates {
    const val SPAWN_EGG: String = "spawn_egg"

    const val FOOD: String = "food"

    // Consumable.
    const val CONSUMABLE: String = "consumable"

    const val USE_ACTION: String = "use_action"

    // Stack max count.
    const val STACK_MAX_COUNT: String = "max_count"

    // Rarity.
    const val RARITY: String = "rarity"
    const val EPIC_RARITY: String = "epic_rarity"
    const val RARE_RARITY: String = "rare_rarity"
    const val UNCOMMON_RARITY: String = "uncommon_rarity"
    const val COMMON_RARITY: String = "common_rarity"

    // Fuel.
    const val FUEL: String = "fuel"

    // Glint.
    const val GLINT: String = "glint"

    // Armor.
    const val ARMOR: String = "armor"

    // Tool.
    const val TOOL: String = "tool"
    const val CAN_DESTROY_IN_CREATIVE: String = "can_destroy_in_creative"

    // Axes.
    const val WOODEN_AXE: String = "wooden_axe"
    const val STONE_AXE: String = "stone_axe"
    const val IRON_AXE: String = "iron_axe"
    const val GOLDEN_AXE: String = "golden_axe"
    const val DIAMOND_AXE: String = "diamond_axe"
    const val NETHERITE_AXE: String = "netherite_axe"

    // Pickaxes.
    const val WOODEN_PICKAXE: String = "wooden_pickaxe"
    const val STONE_PICKAXE: String = "stone_pickaxe"
    const val IRON_PICKAXE: String = "iron_pickaxe"
    const val GOLDEN_PICKAXE: String = "golden_pickaxe"
    const val DIAMOND_PICKAXE: String = "diamond_pickaxe"
    const val NETHERITE_PICKAXE: String = "netherite_pickaxe"

    // The food component in bedrock.
    const val BEDROCK_FOOD: String = "minecraft:food"

    // The tool component in bedrock.
    const val BEDROCK_DAMAGE: String = "minecraft:damage"
    const val BEDROCK_DURABILITY: String = "minecraft:durability"

    // Bedrock stack size.
    const val BEDROCK_MAX_STACK_SIZE: String = "minecraft:max_stack_size"

    // Can destroy in creative.
    const val BEDROCK_CAN_DESTROY_IN_CREATIVE: String = "minecraft:can_destroy_in_creative"

    // Bedrock rarity.
    const val BEDROCK_RARITY: String = "minecraft:rarity"

    // Bedrock fuel.
    const val BEDROCK_FUEL: String = "minecraft:fuel"

    // Bedrock glint.
    const val BEDROCK_GLINT: String = "minecraft:glint"

    // The food component in bedrock.
    const val BEDROCK_WEARABLE: String = "minecraft:wearable"

    // Item use animation.
    const val BEDROCK_USE_ANIMATION: String = "minecraft:use_animation"

    // Bedrock recipes.
    const val BEDROCK_RECIPE_SHAPED: String = "minecraft:recipe_shaped"
    const val BEDROCK_RECIPE_SHAPELESS: String = "minecraft:recipe_shapeless"
    const val BEDROCK_RECIPE_FURNACE: String = "minecraft:recipe_furnace"

    fun init() {
        // Spawn egg.
        register(
            SPAWN_EGG,
            ConiumSpawnEggTemplate::create
        )

        // Food.
        register(
            FOOD,
            ConiumFoodTemplate::create
        )
        register(
            BEDROCK_FOOD,
            ConiumBedrockFoodTemplate::create
        )

        // Stack max count
        register(
            STACK_MAX_COUNT,
            ConiumStackMaxCountTemplate::create
        )

        // Consumable.
        register(
            CONSUMABLE,
            ConiumConsumableTemplate::create
        )
        register(
            USE_ACTION,
            ConiumUseActionTemplate::create
        )

        // Tool.
        register(
            TOOL,
            ConiumItemToolTemplate::create
        )
        // Can destroy in creative.
        register(
            CAN_DESTROY_IN_CREATIVE,
            ConiumCanDestroyInCreativeTemplate::create
        )

        // Axes.
        register(
            WOODEN_AXE,
            ConiumItemWoodenAxeTemplate::create
        )
        register(
            STONE_AXE,
            ConiumItemStoneAxeTemplate::create
        )
        register(
            IRON_AXE,
            ConiumItemIronAxeTemplate::create
        )
        register(
            GOLDEN_AXE,
            ConiumItemGoldenAxeTemplate::create
        )
        register(
            DIAMOND_AXE,
            ConiumItemDiamondAxeTemplate::create
        )
        register(
            NETHERITE_AXE,
            ConiumItemNetheriteAxeTemplate::create
        )

        // Pickaxes.
        register(
            WOODEN_PICKAXE,
            ConiumItemWoodenPickaxeTemplate::create
        )
        register(
            STONE_PICKAXE,
            ConiumItemStonePickaxeTemplate::create
        )
        register(
            IRON_PICKAXE,
            ConiumItemIronPickaxeTemplate::create
        )
        register(
            GOLDEN_PICKAXE,
            ConiumItemGoldenPickaxeTemplate::create
        )
        register(
            DIAMOND_PICKAXE,
            ConiumItemDiamondPickaxeTemplate::create
        )
        register(
            NETHERITE_PICKAXE,
            ConiumItemNetheritePickaxeTemplate::create
        )

        // Rarity.
        register(
            RARITY,
            ConiumRarityTemplate::create
        )
        register(
            EPIC_RARITY,
            ConiumEpicRarityTemplate::create
        )
        register(
            RARE_RARITY,
            ConiumRareRarityTemplate::create
        )
        register(
            UNCOMMON_RARITY,
            ConiumUncommonRarityTemplate::create
        )
        register(
            COMMON_RARITY,
            ConiumCommonRarityTemplate::create
        )

        // Fuel.
        register(
            FUEL,
            ConiumFuelTemplate::create
        )

        // Glint.
        register(
            GLINT,
            ConiumGlintTemplate::create
        )

        // Armor.
        register(
            ARMOR,
            ConiumArmorTemplate::create
        )

        // Bedrock tool.
        register(
            BEDROCK_DAMAGE,
            ConiumBedrockDamageTemplate::create
        )
        register(
            BEDROCK_DURABILITY,
            ConiumBedrockDurabilityTemplate::create
        )

        // Stack.
        register(
            BEDROCK_MAX_STACK_SIZE,
            ConiumBedrockMaxStackSizeTemplate::create
        )

        // Bedrock can destroy in creative.
        register(
            BEDROCK_CAN_DESTROY_IN_CREATIVE,
            ConiumBedrockCanDestroyInCreativeTemplate::create
        )

        // Rarity.
        register(
            BEDROCK_RARITY,
            ConiumBedrockRarityTemplate::create
        )

        // Bedrock fuel.
        register(
            BEDROCK_FUEL,
            ConiumBedrockFuelTemplate::create
        )

        // Bedrock glint.
        register(
            BEDROCK_GLINT,
            ConiumBedrockGlintTemplate::create
        )

        // Bedrock wearable.
        register(
            BEDROCK_WEARABLE,
            ConiumBedrockWearableTemplate::create
        )

        // Use animation.
        register(
            BEDROCK_USE_ANIMATION,
            ConiumBedrockUseAnimationTemplate::create
        )

        // Bedrock recipes.
        register(
            BEDROCK_RECIPE_SHAPED,
            ConiumBedrockRecipeShapedTemplate::create
        )
        register(
            BEDROCK_RECIPE_SHAPELESS,
            ConiumBedrockRecipeShapelessTemplate::create
        )
        register(
            BEDROCK_RECIPE_FURNACE,
            ConiumBedrockRecipeFurnaceTemplate::create
        )
    }
}
