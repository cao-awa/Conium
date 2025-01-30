package com.github.cao.awa.conium.template

import com.github.cao.awa.conium.block.entity.template.preset.redstone.ConiumBlockEntityOutputRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.bedrock.collision.ConiumBedrockBlockCollisionBoxTemplate
import com.github.cao.awa.conium.block.template.bedrock.destructible.ConiumBedrockDestructibleByMiningTemplate
import com.github.cao.awa.conium.block.template.bedrock.destructible.explosion.ConiumBedrockDestructibleByExplosionTemplate
import com.github.cao.awa.conium.block.template.bedrock.light.ConiumBedrockLightEmissionTemplate
import com.github.cao.awa.conium.block.template.collision.ConiumBlockCollisionTemplate
import com.github.cao.awa.conium.block.template.data.ConiumBlockDataTemplate
import com.github.cao.awa.conium.block.template.entity.ConiumEnableBlockEntityTemplate
import com.github.cao.awa.conium.block.template.explosion.resistance.ConiumExplosionResistanceTemplate
import com.github.cao.awa.conium.block.template.instrument.ConiumBlockInstrumentTemplate
import com.github.cao.awa.conium.block.template.luminance.ConiumLuminanceTemplate
import com.github.cao.awa.conium.block.template.map.ConiumBedrockMapColorTemplate
import com.github.cao.awa.conium.block.template.map.ConiumMapColorTemplate
import com.github.cao.awa.conium.block.template.mining.ConiumHardnessTemplate
import com.github.cao.awa.conium.block.template.path.through.ConiumBlockPathFindThroughTemplate
import com.github.cao.awa.conium.block.template.piston.ConiumBlockPistonBehaviorsTemplate
import com.github.cao.awa.conium.block.template.preset.ConiumBlockEntityPresetsTemplate
import com.github.cao.awa.conium.block.template.redstone.ConiumBlockEmitsRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.redstone.ConiumBlockEmitsStrongRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.redstone.ConiumBlockEmitsWeakRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.replaceable.ConiumBlockReplaceableTemplate
import com.github.cao.awa.conium.block.template.velocity.ConiumBlockMovementVelocityTemplate
import com.github.cao.awa.conium.block.template.velocity.jump.ConiumBlockJumpVelocityTemplate
import com.github.cao.awa.conium.block.template.velocity.walk.ConiumBlockWalkVelocityTemplate
import com.github.cao.awa.conium.entity.template.bedrock.collision.ConiumBedrockEntityCollisionBoxTemplate
import com.github.cao.awa.conium.entity.template.bedrock.pushable.ConiumBedrockEntityPushableTemplate
import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
import com.github.cao.awa.conium.entity.template.pushable.ConiumEntityPushableTemplate
import com.github.cao.awa.conium.entity.template.renderer.model.ConiumEntityModelTemplate
import com.github.cao.awa.conium.item.template.action.ConiumUseActionTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumArmorTemplate
import com.github.cao.awa.conium.item.template.bedrock.animation.ConiumBedrockUseAnimationTemplate
import com.github.cao.awa.conium.item.template.bedrock.damage.ConiumBedrockDamageTemplate
import com.github.cao.awa.conium.item.template.bedrock.destory.ConiumBedrockCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.bedrock.durability.ConiumBedrockDurabilityTemplate
import com.github.cao.awa.conium.item.template.bedrock.food.ConiumBedrockFoodTemplate
import com.github.cao.awa.conium.item.template.bedrock.fuel.ConiumBedrockFuelTemplate
import com.github.cao.awa.conium.item.template.bedrock.glint.ConiumBedrockGlintTemplate
import com.github.cao.awa.conium.item.template.bedrock.stack.size.ConiumBedrockMaxStackSizeTemplate
import com.github.cao.awa.conium.item.template.bedrock.wearable.ConiumBedrockWearableTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate
import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate
import com.github.cao.awa.conium.item.template.fuel.ConiumFuelTemplate
import com.github.cao.awa.conium.item.template.glint.ConiumGlintTemplate
import com.github.cao.awa.conium.item.template.ignite.ConiumClearEntityIgniteTemplate
import com.github.cao.awa.conium.item.template.ignite.ConiumIgniteEntityTemplate
import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumCommonRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumEpicRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumRareRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumUncommonRarityTemplate
import com.github.cao.awa.conium.item.template.stack.count.ConiumStackMaxCountTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.item.template.tool.axe.*
import com.github.cao.awa.conium.item.template.tool.mining.ConiumForceMiningSpeedTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.*
import com.github.cao.awa.conium.recipe.template.bedrock.furnace.ConiumBedrockRecipeFurnaceTemplate
import com.github.cao.awa.conium.recipe.template.bedrock.shape.ConiumBedrockRecipeShapedTemplate
import com.github.cao.awa.conium.recipe.template.bedrock.shape.ConiumBedrockRecipeShapelessTemplate
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.registerBlock
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.registerBlockEntity
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.registerEntity
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.registerItem
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.registerRecipe

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

        // Mining speed
        const val FORCE_MINING_SPEED: String = "force_mining_speed"

        // Ignite entity.
        const val IGNITE: String = "ignite"
        const val CLEAR_IGNITE: String = "clear_ignite"

        fun initItemTemplates() {
            // Spawn egg.
            registerItem(
                SPAWN_EGG,
                ConiumSpawnEggTemplate::create
            )

            // Food.
            registerItem(
                FOOD,
                ConiumFoodTemplate::create
            )

            // Stack max count
            registerItem(
                STACK_MAX_COUNT,
                ConiumStackMaxCountTemplate::create
            )

            // Consumable.
            registerItem(
                CONSUMABLE,
                ConiumConsumableTemplate::create
            )
            registerItem(
                USE_ACTION,
                ConiumUseActionTemplate::create
            )

            // Tool.
            registerItem(
                TOOL,
                ConiumItemToolTemplate::create
            )
            // Can destroy in creative.
            registerItem(
                CAN_DESTROY_IN_CREATIVE,
                ConiumCanDestroyInCreativeTemplate::create
            )

            // Axes.
            registerItem(
                WOODEN_AXE,
                ConiumItemWoodenAxeTemplate::create
            )
            registerItem(
                STONE_AXE,
                ConiumItemStoneAxeTemplate::create
            )
            registerItem(
                IRON_AXE,
                ConiumItemIronAxeTemplate::create
            )
            registerItem(
                GOLDEN_AXE,
                ConiumItemGoldenAxeTemplate::create
            )
            registerItem(
                DIAMOND_AXE,
                ConiumItemDiamondAxeTemplate::create
            )
            registerItem(
                NETHERITE_AXE,
                ConiumItemNetheriteAxeTemplate::create
            )

            // Pickaxes.
            registerItem(
                WOODEN_PICKAXE,
                ConiumItemWoodenPickaxeTemplate::create
            )
            registerItem(
                STONE_PICKAXE,
                ConiumItemStonePickaxeTemplate::create
            )
            registerItem(
                IRON_PICKAXE,
                ConiumItemIronPickaxeTemplate::create
            )
            registerItem(
                GOLDEN_PICKAXE,
                ConiumItemGoldenPickaxeTemplate::create
            )
            registerItem(
                DIAMOND_PICKAXE,
                ConiumItemDiamondPickaxeTemplate::create
            )
            registerItem(
                NETHERITE_PICKAXE,
                ConiumItemNetheritePickaxeTemplate::create
            )

            // Mining speed.
            registerItem(
                FORCE_MINING_SPEED,
                ConiumForceMiningSpeedTemplate::create
            )

            // Rarity.
            registerItem(
                RARITY,
                ConiumRarityTemplate::create
            )
            registerItem(
                EPIC_RARITY,
                ConiumEpicRarityTemplate::create
            )
            registerItem(
                RARE_RARITY,
                ConiumRareRarityTemplate::create
            )
            registerItem(
                UNCOMMON_RARITY,
                ConiumUncommonRarityTemplate::create
            )
            registerItem(
                COMMON_RARITY,
                ConiumCommonRarityTemplate::create
            )

            // Fuel.
            registerItem(
                FUEL,
                ConiumFuelTemplate::create
            )

            // Glint.
            registerItem(
                GLINT,
                ConiumGlintTemplate::create
            )

            // Armor.
            registerItem(
                ARMOR,
                ConiumArmorTemplate::create
            )

            // Ignite entity
            registerItem(
                IGNITE,
                ConiumIgniteEntityTemplate::create
            )
            registerItem(
                CLEAR_IGNITE,
                ConiumClearEntityIgniteTemplate::create
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
            registerItem(
                DAMAGE,
                ConiumBedrockDamageTemplate::create,
                true
            )
            registerItem(
                DURABILITY,
                ConiumBedrockDurabilityTemplate::create,
                true
            )

            // Stack.
            registerItem(
                MAX_STACK_SIZE,
                ConiumBedrockMaxStackSizeTemplate::create,
                true
            )

            // Can destroy in creative.
            registerItem(
                CAN_DESTROY_IN_CREATIVE,
                ConiumBedrockCanDestroyInCreativeTemplate::create,
                true
            )

            // Rarity.
            registerItem(
                RARITY,
                ConiumRarityTemplate::createBedrock,
                true
            )

            // Food.
            registerItem(
                FOOD,
                ConiumBedrockFoodTemplate::create,
                true
            )

            // Fuel.
            registerItem(
                FUEL,
                ConiumBedrockFuelTemplate::create,
                true
            )

            // Glint.
            registerItem(
                BEDROCK_GLINT,
                ConiumBedrockGlintTemplate::create,
                true
            )

            // Wearable.
            registerItem(
                WEARABLE,
                ConiumBedrockWearableTemplate::create,
                true
            )

            // Use animation.
            registerItem(
                USE_ANIMATION,
                ConiumBedrockUseAnimationTemplate::create,
                true
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
            registerRecipe(
                RECIPE_SHAPED,
                ConiumBedrockRecipeShapedTemplate::create,
                true
            )
            registerRecipe(
                RECIPE_SHAPELESS,
                ConiumBedrockRecipeShapelessTemplate::create,
                true
            )
            registerRecipe(
                RECIPE_FURNACE,
                ConiumBedrockRecipeFurnaceTemplate::create,
                true
            )
        }
    }

    object Block {
        // Destructible.
        const val EXPLOSION_RESISTANCE: String = "explosion_resistance"
        const val HARDNESS: String = "hardness"

        // Map color.
        const val MAP_COLOR: String = "map_color"

        // Luminance.
        const val LUMINANCE: String = "luminance"

        // Collision.
        const val COLLISION: String = "collision"

        // Replaceable.
        const val REPLACEABLE: String = "replaceable"

        // Velocities.
        const val MOVEMENT_VELOCITY: String = "movement_velocity"
        const val WALK_VELOCITY: String = "walk_velocity"
        const val JUMP_VELOCITY: String = "jump_velocity"

        // Piston behavior.
        const val PISTON_BEHAVIOR: String = "piston_behavior"

        // Note block instrument.
        const val INSTRUMENT: String = "instrument"

        // Path find.
        const val PATH_FIND_THROUGH: String = "path_find_through"

        // Block entity.
        const val ENABLE_BLOCK_ENTITY: String = "enable_block_entity"

        // Block data.
        const val DATA: String = "data"

        // Block entity presets.
        const val BLOCK_ENTITY_PRESETS: String = "block_entity_presets"

        // Emits redstone power.
        const val EMITS_REDSTONE_POWER: String = "emits_redstone_power"
        const val EMITS_WEAK_REDSTONE_POWER: String = "emits_weak_redstone_power"
        const val EMITS_STRONG_REDSTONE_POWER: String = "emits_strong_redstone_power"

        fun initBlockTemplates() {
            // Destructible.
            registerBlock(
                EXPLOSION_RESISTANCE,
                ConiumExplosionResistanceTemplate::create
            )
            registerBlock(
                HARDNESS,
                ConiumHardnessTemplate::create
            )

            // Map color.
            registerBlock(
                MAP_COLOR,
                ConiumMapColorTemplate::create
            )

            // Luminance.
            registerBlock(
                LUMINANCE,
                ConiumLuminanceTemplate::create
            )

            // Collision.
            registerBlock(
                COLLISION,
                ConiumBlockCollisionTemplate::create
            )

            // Replaceable.
            registerBlock(
                REPLACEABLE,
                ConiumBlockReplaceableTemplate::create
            )

            // Movement velocity multiplier.
            registerBlock(
                MOVEMENT_VELOCITY,
                ConiumBlockMovementVelocityTemplate::create
            )
            registerBlock(
                WALK_VELOCITY,
                ConiumBlockWalkVelocityTemplate::create
            )
            registerBlock(
                JUMP_VELOCITY,
                ConiumBlockJumpVelocityTemplate::create
            )

            // Piston behavior.
            registerBlock(
                PISTON_BEHAVIOR,
                ConiumBlockPistonBehaviorsTemplate::create
            )

            // Note block instrument.
            registerBlock(
                INSTRUMENT,
                ConiumBlockInstrumentTemplate::create
            )

            // Path through.
            registerBlock(
                PATH_FIND_THROUGH,
                ConiumBlockPathFindThroughTemplate::create
            )

            // Block entity.
            registerBlock(
                ENABLE_BLOCK_ENTITY,
                ConiumEnableBlockEntityTemplate::create
            )

            // Block data.
            registerBlock(
                DATA,
                ConiumBlockDataTemplate::create
            )

            // Block entity presets.
            registerBlock(
                BLOCK_ENTITY_PRESETS,
                ConiumBlockEntityPresetsTemplate::create
            )

            // Emits redstone power.
            registerBlock(
                EMITS_REDSTONE_POWER,
                ConiumBlockEmitsRedstonePowerTemplate::create
            )
            registerBlock(
                EMITS_WEAK_REDSTONE_POWER,
                ConiumBlockEmitsWeakRedstonePowerTemplate::create
            )
            registerBlock(
                EMITS_STRONG_REDSTONE_POWER,
                ConiumBlockEmitsStrongRedstonePowerTemplate::create
            )
        }
    }

    object BlockEntity {

        const val OUTPUT_REDSTONE_POWER: String = "output_redstone_power"

        fun initBlockEntityTemplates() {
            registerBlockEntity(
                OUTPUT_REDSTONE_POWER,
                ConiumBlockEntityOutputRedstonePowerTemplate::create
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

        // Collision.
        const val COLLISION_BOX: String = "minecraft:collision_box"

        fun initBedrockBlockTemplates() {
            // Destructible by explosion.
            registerBlock(
                DESTRUCTIBLE_BY_EXPLOSION,
                ConiumBedrockDestructibleByExplosionTemplate::create,
                true
            )
            registerBlock(
                DESTRUCTIBLE_BY_MINING,
                ConiumBedrockDestructibleByMiningTemplate::create,
                true
            )

            // Map color.
            registerBlock(
                MAP_COLOR,
                ConiumBedrockMapColorTemplate::create,
                true
            )

            // Light emission.
            registerBlock(
                LIGHT_EMISSION,
                ConiumBedrockLightEmissionTemplate::create,
                true
            )

            // Collision.
            registerBlock(
                COLLISION_BOX,
                ConiumBedrockBlockCollisionBoxTemplate::create,
                true
            )
        }
    }

    object Entity {
        // Dimension.
        const val DIMENSION: String = "dimension"

        // Pushable.
        const val PUSHABLE: String = "pushable"

        // Model.
        const val MODEL: String = "model"

        fun initEntityTemplates() {
            // Dimension.
            registerEntity(
                DIMENSION,
                ConiumEntityDimensionTemplate::create
            )

            // Pushable.
            registerEntity(
                PUSHABLE,
                ConiumEntityPushableTemplate::create
            )

            // Model.
            registerEntity(
                MODEL,
                ConiumEntityModelTemplate::create
            )
        }
    }

    object BedrockEntity {
        // Collision box.
        const val COLLISION_BOX: String = "minecraft:collision_box"

        // Pushable.
        const val PUSHABLE: String = "minecraft:pushable"

        fun initBedrockEntityTemplates() {
            // Collision box.
            registerEntity(
                COLLISION_BOX,
                ConiumBedrockEntityCollisionBoxTemplate::create,
                true
            )

            // Pushable.
            registerEntity(
                PUSHABLE,
                ConiumBedrockEntityPushableTemplate::create,
                true
            )
        }
    }

    fun init() {
        Item.initItemTemplates()
        Block.initBlockTemplates()
        BlockEntity.initBlockEntityTemplates()
        Entity.initEntityTemplates()
        BedrockItem.initBedrockItemTemplates()
        BedrockBlock.initBedrockBlockTemplates()
        BedrockEntity.initBedrockEntityTemplates()
        BedrockRecipe.initBedrockRecipeTemplates()
    }
}
