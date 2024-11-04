package com.github.cao.awa.conium.template

//import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.block.template.bedrock.destructible.explosion.ConiumBedrockDestructibleByExplosionTemplate
import com.github.cao.awa.conium.block.template.bedrock.destructible.ConiumBedrockDestructibleByMiningTemplate
import com.github.cao.awa.conium.block.template.map.ConiumBedrockMapColorTemplate
import com.github.cao.awa.conium.block.template.bedrock.light.ConiumBedrockLightEmissionTemplate
import com.github.cao.awa.conium.block.template.luminance.ConiumLuminanceTemplate
import com.github.cao.awa.conium.block.template.map.ConiumMapColorTemplate
import com.github.cao.awa.conium.block.template.mining.ConiumHardnessTemplate
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
    object Item {
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

        fun initItemTemplates() {
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
        }
    }

    object BedrockItem {
        // The food component in bedrock.
        const val FOOD: String = "minecraft:food"

        // The tool component in bedrock.
        const val DAMAGE: String = "minecraft:damage"
        const val DURABILITY: String = "minecraft:durability"

        // Bedrock stack size.
        const val MAX_STACK_SIZE: String = "minecraft:max_stack_size"

        // Can destroy in creative.
        const val CAN_DESTROY_IN_CREATIVE: String = "minecraft:can_destroy_in_creative"

        // Bedrock rarity.
        const val RARITY: String = "minecraft:rarity"

        // Bedrock fuel.
        const val FUEL: String = "minecraft:fuel"

        // Bedrock glint.
        const val BEDROCK_GLINT: String = "minecraft:glint"

        // The food component in bedrock.
        const val WEARABLE: String = "minecraft:wearable"

        // Item use animation.
        const val USE_ANIMATION: String = "minecraft:use_animation"

        fun initBedrockItemTemplates() {
            // Bedrock tool.
            register(
                DAMAGE,
                ConiumBedrockDamageTemplate::create
            )
            register(
                DURABILITY,
                ConiumBedrockDurabilityTemplate::create
            )

            // Stack.
            register(
                MAX_STACK_SIZE,
                ConiumBedrockMaxStackSizeTemplate::create
            )

            // Can destroy in creative.
            register(
                CAN_DESTROY_IN_CREATIVE,
                ConiumBedrockCanDestroyInCreativeTemplate::create
            )

            // Rarity.
            register(
                RARITY,
                ConiumRarityTemplate::createBedrock
            )

            // Food.
            register(
                FOOD,
                ConiumBedrockFoodTemplate::create
            )

            // Fuel.
            register(
                FUEL,
                ConiumBedrockFuelTemplate::create
            )

            // Glint.
            register(
                BEDROCK_GLINT,
                ConiumBedrockGlintTemplate::create
            )

            // Wearable.
            register(
                WEARABLE,
                ConiumBedrockWearableTemplate::create
            )

            // Use animation.
            register(
                USE_ANIMATION,
                ConiumBedrockUseAnimationTemplate::create
            )
        }
    }

    object BedrockRecipe {
        // Bedrock recipes.
        const val RECIPE_SHAPED: String = "minecraft:recipe_shaped"
        const val RECIPE_SHAPELESS: String = "minecraft:recipe_shapeless"
        const val RECIPE_FURNACE: String = "minecraft:recipe_furnace"

        fun initBedrockRecipeTemplates() {
            // Bedrock recipes.
            register(
                RECIPE_SHAPED,
                ConiumBedrockRecipeShapedTemplate::create
            )
            register(
                RECIPE_SHAPELESS,
                ConiumBedrockRecipeShapelessTemplate::create
            )
            register(
                RECIPE_FURNACE,
                ConiumBedrockRecipeFurnaceTemplate::create
            )
        }
    }

    object Block {
        // Destructible.
        const val HARDNESS: String = "hardness"
        const val EXPLOSION_RESISTANCE: String = "explosion_resistance"
        // Map color.
        const val MAP_COLOR: String = "map_color"
        // Luminance.
        const val LUMINANCE: String = "luminance"

        fun initBlockTemplates() {
            // Mining.
            register(
                HARDNESS,
                ConiumHardnessTemplate::create
            )

            // Map color.
            register(
                MAP_COLOR,
                ConiumMapColorTemplate::create
            )

            // Luminance.
            register(
                LUMINANCE,
                ConiumLuminanceTemplate::create
            )
        }
    }

    object BedrockBlock {
        // Destructible.
        const val DESTRUCTIBLE_BY_EXPLOSION: String = "minecraft:destructible_by_explosion"
        const val DESTRUCTIBLE_BY_MINING: String = "minecraft:destructible_by_mining"
        // Map color.
        const val MAP_COLOR: String = "minecraft:map_color"
        // Light emission.
        const val LIGHT_EMISSION: String = "minecraft:light_emission"

        fun initBedrockBlockTemplates() {
            // Destructible by explosion.
            register(
                DESTRUCTIBLE_BY_EXPLOSION,
                ConiumBedrockDestructibleByExplosionTemplate::create
            )
            register(
                DESTRUCTIBLE_BY_MINING,
                ConiumBedrockDestructibleByMiningTemplate::create
            )

            // Map color.
            register(
                MAP_COLOR,
                ConiumBedrockMapColorTemplate::create
            )

            // Light emission.
            register(
                LIGHT_EMISSION,
                ConiumBedrockLightEmissionTemplate::create
            )
        }
    }

    fun init() {
        Item.initItemTemplates()
        BedrockItem.initBedrockItemTemplates()
        Block.initBlockTemplates()
        BedrockBlock.initBedrockBlockTemplates()
        BedrockRecipe.initBedrockRecipeTemplates()
    }
}
