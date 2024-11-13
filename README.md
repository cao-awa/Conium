# What is Conium

Conium is a datapack and script framework for the modern Minecraft, it provides very diverse features without Java coding,
Conium lets you complete your mods only using datapacks.

![](https://count.getloli.com/@@cao-awa.conium?name=%40cao-awa.conium&theme=rule34&padding=7&offset=0&align=top&scale=1&pixelated=1&darkmode=auto)

## Requirements

|            Requirement |        Version         |  Installs   |             Notes             |
|-----------------------:|:----------------------:|:-----------:|:-----------------------------:|
|                   Java |          21!!          |    Need     |      Only 21 can be use       |
|              Minecraft |        1.21.3!!        |    Need     |    Only 1.21.3 can be use     |
| Fabric language kotlin | 1.12.3+kotlin.2.0.21!! |    Need     | Only kotlin 2.0.21 can be use |
|             Fabric API |           ?            | Unnecessary |       Any version is ok       |
|                 Fluxia |        1.0.10!!        | Unnecessary | Built-in contents translator  |

## Datapack structure

+ \<path-to-save\>/datapacks
    + \<pack-name\>
        + data
            + item
                + This directory is items, data type is '.json'
            + block
                + This directory is blocks, data type is '.json'
            + entity
                + This directory is entities, data type is '.json'
            + property
                + item
                    + This directory is item properties injectors, data type is '.json'
                + block
                    + This directory is block properties injectors, data type is '.json', current is no impls.
            + scripts
                + This directory is scripts, data type is 'kts', '.ts' and '.js'

## Data driven

For items, currently supporting:

|            Conium schema key |         Bedrock schema impls         |                                   Notes                                   |
|-----------------------------:|:------------------------------------:|:-------------------------------------------------------------------------:|
|                         tool |                  *                   |                          Make the item be a tool                          |
|         attack_damage (tool) |           minecraft:damage           |                  Setting tool damage amount to entities                   |
|          attack_speed (tool) |               No impl                |                 Setting tool attack speed (the cooldown)                  |
|            durability (tool) |         minecraft:durability         |                          Setting tool durability                          |
|      effective_blocks (tool) |               No impl                |                Setting what blocks can mined by this tool                 |
|              material (tool) |               No impl                |                   Setting the base data using material                    |
|             is_weapon (tool) |               No impl                | Make different durability consume when used to hit entity or break blocks |
|      effective_blocks (tool) |               No impl                |                Setting what blocks can mined by this tool                 |
|         damage_chance (tool) | damage_chance (minecraft:durability) |              Setting the probability of durability consuming              |
|      can_destroy_in_creative |  minecraft:can_destroy_in_creative   |      Setting block breakable in creative mode when holding this item      |
|                    max_count |       minecraft:max_stack_size       |               Setting the max item stack count of the item                |
|                         food |            minecraft:food            |                        Setting the item be a food                         |
|       can_always_eat  (food) |   can_always_eat (minecraft:food)    |              Setting the food can always eats (no cooldown)               |
|           nutrition   (food) |      nutrition (minecraft:food)      |                     Setting the nutrition of the food                     |
|          saturation   (food) | saturation_modifier (minecraft:food) |                    Setting the saturation of the food                     |
|                   consumable |                  *                   |                 Setting the item has consumable behaviors                 |
|      convert_to (consumable) |  using_converts_to (minecraft:food)  |           Setting the item will convert to other item when used           |
| apply_effects   (consumable) |               No impl                |          Setting the item will give effects to entity when used           |
|                       rarity |           minecraft:rarity           |                      Setting the rarity of the item                       |
|                   use_action |       minecraft:use_animation        |          Setting the using action of the item (client animation)          |
|                         fuel |            minecraft:fuel            |     Setting the item can be put into furnaces and provides fuel time      |
|                        glint |           minecraft:glint            |           Setting the item will glint showing (client display)            |
|                        armor |                  *                   |          Setting the item be a armor and can be wear to the slot          |
|              defense (armor) |   protection (minecraft:wearable)    |         Setting the armor providing how much protection (defense)         |
|                 slot (armor) |      slot (minecraft:wearable)       |                Setting the armor can wearing to what slot                 |
| knockback_resistance (armor) |               No impl                |        Setting the armor providing how much knock back resistance         |
|            toughness (armor) |               No impl                |                          Setting armor toughness                          |
|          enchantable (armor) |               No impl                |                 Setting armor enchantable (Not completed)                 |

For blocks, currently supporting:

| Conium schema key |       Bedrock schema impls       |                               Notes                                |
|------------------:|:--------------------------------:|:------------------------------------------------------------------:|
|          hardness | minecraft:destructible_by_mining |                 Setting the hardness of the block                  |
|         map_color |       minecraft:map_color        |         Setting the map color of the block (Not completed)         |
|         luminance |     minecraft:light_emission     | Setting the lighting of the block (like torch or burning furnaces) |
|         collision |     minecraft:collision_box      |               Setting the collision box of the block               |

For entities, supported to defines 'component_groups' in bedrock schema, but unable to switching now.

Entities currently supporting:

| Conium schema key |  Bedrock schema impls   |                                    Notes                                    |
|------------------:|:-----------------------:|:---------------------------------------------------------------------------:|
|         dimension | minecraft:collision_box |                   Setting the collision box of the entity                   |
|          pushable |   minecraft:pushable    | Setting the entity is can be pushing by other entities or pistons or fluids |

### Conium schema

```json5
{
  // If using conium schema, 'schema' is required.
  "schema_style": "conium",
  // The identifier must present.
  "identifier": "awa:conium",
  "templates": {
    // The components where here following listed.
  }
}
```

Currently, supported these components of items:

```json5
{
  "components": {
    "tool": {
      // The damage amount when hitting the entity.
      "attack_damage": 100,
      // The cooldown modifier after once attack.
      "attack_speed": -3.0,
      // The durability amount.
      "durability": 50,
      // Which blocks this tool can mine.
      // This value is a tag key.
      "effective_blocks": "minecraft:mineable/pickaxe",
      // This tool is what material.
      "material": "netherite",
      // This tool is weapon or not.
      // The durability will decrement 2 after this tool damage to entity when it not weapon.
      "is_weapon": false,
      // Can be missing when doesn't need chances.
      // The damage chance algorithm is:
      //     (rand(max - min + 1) + min) == min 
      "damage_chance": {
        "min": 0,
        "max": 0
      }
    },
    // Setting this tool item can destroy blocks in creative mode.
    "can_destroy_in_creative": true,
    // The max item stack size.
    // The size value must in range 1 to 64.
    "max_count": 64,
    // The food component.
    // It also can be this schema:
    //     "food": <string>
    // This string mean use a food template, here is allowed templates:
    // [
    //     "apple" -> APPLE,
    //     "baked_potato" -> BAKED_POTATO,
    //     "beef" -> BEEF,
    //     "beetroot" -> BEETROOT,
    //     "beetroot_soup" -> BEETROOT_SOUP,
    //     "bread" -> BREAD,
    //     "carrot" -> CARROT,
    //     "chicken" -> CHICKEN,
    //     "chorus_fruit" -> CHORUS_FRUIT,
    //     "cod" -> COD,
    //     "cooked_beef" -> COOKED_BEEF,
    //     "cooked_chicken" -> COOKED_CHICKEN,
    //     "cooked_cod" -> COOKED_COD,
    //     "cooked_mutton" -> COOKED_MUTTON,
    //     "cooked_porkchop" -> COOKED_PORKCHOP,
    //     "cooked_rabbit" -> COOKED_RABBIT,
    //     "cooked_salmon" -> COOKED_SALMON,
    //     "cookie" -> COOKIE,
    //     "dried_kelp" -> DRIED_KELP,
    //     "enchanted_golden_apple" -> ENCHANTED_GOLDEN_APPLE,
    //     "golden_apple" -> GOLDEN_APPLE,
    //     "golden_carrot" -> GOLDEN_CARROT,
    //     "honey_bottle" -> HONEY_BOTTLE,
    //     "melon_slice" -> MELON_SLICE,
    //     "mushroom_stem" -> MUSHROOM_STEW,
    //     "mutton" -> MUTTON,
    //     "poisonous_potato" -> POISONOUS_POTATO,
    //     "porkchop" -> PORKCHOP,
    //     "potato" -> POTATO,
    //     "pufferfish" -> PUFFERFISH,
    //     "pumpkin_pie" -> PUMPKIN_PIE,
    //     "rabbit" -> RABBIT,
    //     "rabbit_stew" -> RABBIT_STEW,
    //     "rotten_flesh" -> ROTTEN_FLESH,
    //     "salmon" -> SALMON,
    //     "spider_eye" -> SPIDER_EYE,
    //     "suspicious_stew" -> SUSPICIOUS_STEW,
    //     "sweet_berries" -> SWEET_BERRIES,
    //     "flow_berries" -> GLOW_BERRIES,
    //     "tropical_fish" -> TROPICAL_FISH
    // ]
    "food": {
      // Setting this food has cooldown after ate.
      "can_always_eat": false,
      // Nutrition.
      "nutrition": 10,
      // Saturation.
      "saturation": 1.0
    },
    // The consumable component.
    // It also can be this schema:
    //     "food": <string>
    // This string mean use a food template, here is allowed templates:
    // [
    //     "food" -> FOOD,
    //     "drink" -> DRINK,
    //     "honey_bottle" -> HONEY_BOTTLE,
    //     "ominous_bottle" -> OMINOUS_BOTTLE,
    //     "dried_kelp" -> DRIED_KELP,
    //     "raw_chicken" -> RAW_CHICKEN,
    //     "enchanted_golden_apple" -> ENCHANTED_GOLDEN_APPLE,
    //     "golden_apple" -> GOLDEN_APPLE,
    //     "poisonous_potato" -> POISONOUS_POTATO,
    //     "pufferfish" -> PUFFERFISH,
    //     "rotten_flesh" -> ROTTEN_FLESH,
    //     "spider_eye" -> SPIDER_EYE,
    //     "milk_bucket" -> MILK_BUCKET,
    //     "chorus_fruit" -> CHORUS_FRUIT
    // ]
    "consumable": {
      // Give back an item after used this item. 
      "convert_to": "bowl",
      // Give effects after used this item.
      "apply_effects": {
        // Affects list.
        "effects": [
          {
            // Effect identifier.
            "id": "minecraft:nausea",
            // Effect level.
            "amplifier": 1,
            // Effect duration, the unit is ticks instead of seconds.
            "duration": 200,
            // Setting this effect is show or hidden particles.
            "show_particles": true,
            // Setting this effect is show or hidden on effects list.
            "show_icon": true,
            // Setting this effect is ambient effect.
            "ambient": true
          }
          // Or more effects here.
        ],
        // Apply chance.
        // Value range is 0 to 1 as float
        "probability": 0.5
      }
    },
    // The rarity component, allowed values is:
    // ["common", "uncommon", "rare", "epic"]
    "rarity": "epic",
    // The use animation component.
    "use_action": "eat",
    // Setting this item can be put into furnaces and burns.
    // The duration unit is ticks instead of seconds. 
    "fuel": 60,
    // Setting this item show glinting on hand.
    "glint": true,
    // Setting this item can be wearing on slots. 
    // It can be abbreviated as:
    //     "armor": <bool>
    // When it abbreviated, the value is mean the 'slot',
    // And other element will all be 0 automatically. 
    "armor": {
      // Allowed slots is:
      // [
      //     "slot.armor.head" -> EquipmentType.HELMET,
      //     "slot.armor.chest" -> EquipmentType.CHESTPLATE,
      //     "slot.armor.legs" -> EquipmentType.LEGGINGS,
      //     "slot.armor.feet" -> EquipmentType.BOOTS,
      //     "helmet" -> EquipmentType.HELMET ,
      //     "chestplate" -> EquipmentType.CHESTPLATE,
      //     "chest_plate" -> EquipmentType.CHESTPLATE,
      //     "leggings" -> EquipmentType.LEGGINGS,
      //     "boots" -> EquipmentType.BOOTS,
      //     "body" -> EquipmentType.BODY.
      //     "head" -> EquipmentType.HELMET,
      //     "chest" -> EquipmentType.CHESTPLATE,
      //     "legs" -> EquipmentType.LEGGINGS,
      //     "feet" -> EquipmentType.BOOTS  
      // ]
      "slot": "chest",
      // This item provided how much defense.
      "defense": 10,
      // This item provided how much knockback resistance.
      // Value range is 0 to 1 as float
      "knockback_resistance": 0.1,
      // This item provided how much toughness.
      "toughness": 10,
      // Setting this item is enchantable.
      "enchantable": 10
    }
  }
}
```

### bedrock schema

```json5
{
  // If using bedrock schema, 'schema' can be missing.
  "schema_style": "bedrock",
  // Format version is always can be missing, conium is supporting to the schemas by all versions. 
  "format_version": "1.20.10",
  "minecraft:item": {
    // Description and identifier must present.
    "description": {
      "identifier": "awa:bedrock"
    },
    "components": {
      // The components where here following listed.
    }
  }
}
```

Currently, supported these components of items:

```json5
{
  "components": {
    // The damage amount when hitting the entity.
    "minecraft:damage": 10,
    // The durability amount of tool item.
    // When damage chance is missing, it can be abbreviated as:
    //     "minecraft:durability": <int>
    "minecraft:durability": {
      // Can be missing when doesn't need chances.
      // The damage chance algorithm is:
      //     (rand(max - min + 1) + min) == min 
      "damage_chance": {
        // Minimum value, must in range 0 to 100 and cannot more than maximum value.
        "min": 0,
        // maximum value, must in range 0 to 100 and cannot less than minimum value.
        "max": 100
      },
      // The amount.
      "max_durability": 50
    },
    // Setting this tool item can destroy blocks in creative mode.
    // It can be abbreviated as:
    //     "minecraft:can_destroy_in_creative": <bool>
    "minecraft:can_destroy_in_creative": {
      "value": true
    },
    // The max item stack size.
    // The size value must in range 1 to 64.
    // It can be abbreviated as:
    //     "minecraft:max_stack_size": <int>
    "minecraft:max_stack_size": {
      "value": 64
    },
    // The food component, use to setting this item is eatable.
    // All element can be missing in this component.
    "minecraft:food": {
      // Setting this food has cooldown after ate.
      "can_always_eat": true,
      // Nutrition.
      "nutrition": 10,
      // Saturation.
      "saturation_modifier": 1.0,
      // Give back an item after ate this food. 
      "using_converts_to": "bowl"
    },
    // The rarity component, allowed values is:
    // ["common", "uncommon", "rare", "epic"]
    "minecraft:rarity": "uncommon",
    // The use animation component.
    // It can be abbreviated as:
    //     "minecraft:use_animation": <string>
    "minecraft:use_animation": {
      "value": "eat"
    },
    // Setting this item can be put into furnaces and burns.
    // The duration unit is seconds instead of ticks. 
    // The ticks value is duration multiply to 20.
    // It can be abbreviated as:
    //     "minecraft:fuel": <float>
    "minecraft:fuel": {
      "duration": 3.0
    },
    // Setting this item show glinting on hand.
    // It can be abbreviated as:
    //     "minecraft:glint": <bool>
    "minecraft:glint": {
      "value": true
    },
    // Setting this item can be wearing on slots. 
    "minecraft:wearable": {
      // This item provided how much protection(defense) value.
      "protection": 10,
      // Allowed slots is:
      // [
      //     "slot.armor.head" -> EquipmentType.HELMET,
      //     "slot.armor.chest" -> EquipmentType.CHESTPLATE,
      //     "slot.armor.legs" -> EquipmentType.LEGGINGS,
      //     "slot.armor.feet" -> EquipmentType.BOOTS,
      //     "helmet" -> EquipmentType.HELMET ,
      //     "chestplate" -> EquipmentType.CHESTPLATE,
      //     "chest_plate" -> EquipmentType.CHESTPLATE,
      //     "leggings" -> EquipmentType.LEGGINGS,
      //     "boots" -> EquipmentType.BOOTS,
      //     "body" -> EquipmentType.BODY.
      //     "head" -> EquipmentType.HELMET,
      //     "chest" -> EquipmentType.CHESTPLATE,
      //     "legs" -> EquipmentType.LEGGINGS,
      //     "feet" -> EquipmentType.BOOTS  
      // ]
      "slot": "slot.armor.chest"
    }
  }
}
```

## Conium script APIs

Documents are not done yet.

## Bedrock script APIs

Not completed bedrock script APIs supports now, only framework able to runs the sample.

### Grammars

About typescript grammar, supported by 'language-translator': [Typescript supports](https://github.com/cao-awa/language-translator/tree/main/doc/typescript)

### APIs

Currently, there's APIs is available:

In context:

|   Name |                                              Conium API type                                               | Gametest API type |      Status      |
|-------:|:----------------------------------------------------------------------------------------------------------:|:-----------------:|:----------------:|
| system | [AbstractBedrockSystem](./src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt) |      System       | Alpha (Unstable) |
|  world |  [AbstractBedrockWorld](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt)   |       World       | Alpha (Unstable) |

In APIs:

+ world
    + [AbstractBedrockWorld](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt) impl by [BedrockWorld](./src/main/java/com/github/cao/awa/conium/bedrock/world/BedrockWorld.kt) as known as 'World' in SAPI
        + player
            + [BedrockPlayer](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/BedrockPlayer.kt) as known as 'Player' in SAPI
                + delegate
                    + [BedrockPlayerDelegate](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt) as known as 'Player[]' in SAPI
                + screen
                    + [BedrockOnScreenDisplay](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt) as known as 'ScreenDisplay' in SAPI
    + dimension
        + [BedrockDimensionLocation](./src/main/java/com/github/cao/awa/conium/bedrock/world/dimension/BedrockDimensionLocation.kt) as known as 'DimensionLocation' in SAPI
+ system
    + [AbstractBedrockSystem](./src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt) impl by [BedrockSystem](./src/main/java/com/github/cao/awa/conium/bedrock/system/BedrockSystem.kt) as known as 'System' in SAPI
+ item
    + stack
        + [BedrockItemStack](./src/main/java/com/github/cao/awa/conium/bedrock/item/stack/BedrockItemStack.kt) as known as 'ItemStack' in SAPI
+ script
    + [BedrockScriptAnonymousObjectMap](./src/main/java/com/github/cao/awa/conium/bedrock/item/stack/BedrockItemStack.kt) as known as 'anonymous object' in Javascript
+ event
    + [BedrockBeforeEvents](./src/main/java/com/github/cao/awa/conium/bedrock/event/BedrockBeforeEvents.kt) as known as 'WorldBeforeEvents' in SAPI
        + context
            + [BedrockEventContext](./src/main/java/com/github/cao/awa/conium/bedrock/event/context/BedrockEventContext.kt)
            + item
                + use
                    + [BedrockItemUseOnEventContext](./src/main/java/com/github/cao/awa/conium/bedrock/event/context/item/use/BedrockItemUseOnEventContext.kt) as known as 'ItemUseOnBeforeEvent' and 'ItemUseOnAfterEvent' in SAPI
    + item
        + use
            + [BedrockItemUseOnBeforeEvent](./src/main/java/com/github/cao/awa/conium/bedrock/event/item/use/BedrockItemUseOnBeforeEvent.kt) as known as 'ItemUseOnBeforeEventSignal' in SAPI

By bedrock script APIs:

+ [world](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt)
    + ```fun``` [getPlayers()](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt): [BedrockPlayerDelegate](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt)
        + ```operator fun``` [get](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt): [BedrockPlayer](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/BedrockPlayer.kt)(index: Int)
    + ```val``` [onScreenDisplay](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt): [BedrockOnScreenDisplay](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt)
        + ```fun``` [setTitle](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt)(title: String, properties: [BedrockScriptAnonymousObjectMap](./src/main/java/com/github/cao/awa/conium/bedrock/script/BedrockScriptAnonymousObjectMap.kt))
        + ```fun``` [updateSubtitle](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt)(title: String)
    + ```val``` [eventsBefore](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt) = ```get()```: [BedrockBeforeEvents](./src/main/java/com/github/cao/awa/conium/bedrock/event/BedrockBeforeEvents.kt)
        + ```val``` [itemUseOn](./src/main/java/com/github/cao/awa/conium/bedrock/event/BedrockBeforeEvents.kt): [BedrockItemUseOnEvent](./src/main/java/com/github/cao/awa/conium/bedrock/event/item/use/BedrockItemUseOnBeforeEvent.kt)
            + ```fun``` [subscribe](./src/main/java/com/github/cao/awa/conium/bedrock/event/item/use/BedrockItemUseOnBeforeEvent.kt)(action: ([BedrockItemUseOnEventContext](./src/main/java/com/github/cao/awa/conium/bedrock/event/context/item/use/BedrockItemUseOnEventContext.kt)) -> Unit)
+ [system](./src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt)
    + ```fun``` [runInterval](./src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt)(callback: () -> Unit, tickInterval: Int): IntegerReceptacle
    + ```fun``` [clearRun](./src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt)(runId: IntegerReceptacle)
