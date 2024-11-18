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

### Build requirements

Conium has configured whole project, just clone the repository, and reload project and run gradle task ```remapJar```.

| Requirement |   Version    |               Notes                |
|------------:|:------------:|:----------------------------------:|
|        Java |     21!!     |         Only 21 can be use         |
|      Gradle |     8.11     |     8.11 or other could be use     |
|      Kotlin |   2.0.21!!   |       Only 2.0.21 can be use       |
| Fabric loom | 1.8-SNAPSHOT | 1.8-SNAPSHOT or other could be use |
|   Minecraft |   1.21.3!!   |       Only 1.21.3 can be use       |

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
            + script
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

| Conium schema key |       Bedrock schema impls       |                                  Notes                                  |
|------------------:|:--------------------------------:|:-----------------------------------------------------------------------:|
|          hardness | minecraft:destructible_by_mining |                    Setting the hardness of the block                    |
|         map_color |       minecraft:map_color        |           Setting the map color of the block (Not completed)            |
|         luminance |     minecraft:light_emission     |   Setting the lighting of the block (like torch or burning furnaces)    |
|         collision |     minecraft:collision_box      |                 Setting the collision box of the block                  |
|       replaceable |             No impl              | Setting the block can be replaced when placing block like water or fire |
| movement_velocity |             No impl              |    Setting the movement velocity when entities walk or jump on block    |
|     walk_velocity |             No impl              |        Setting the movement velocity when entities walk on block        |
|     jump_velocity |             No impl              |        Setting the movement velocity when entities jump on block        |
|   piston_behavior |             No impl              |        Setting the behavior when piston interacting to the block        |

For entities, supported to defines 'component_groups' in bedrock schema, but unable to switching now.

Entities currently supporting:

| Conium schema key |  Bedrock schema impls   |                                    Notes                                    |
|------------------:|:-----------------------:|:---------------------------------------------------------------------------:|
|         dimension | minecraft:collision_box |                   Setting the collision box of the entity                   |
|          pushable |   minecraft:pushable    | Setting the entity is can be pushing by other entities or pistons or fluids |
|             model |         No impl         |                     Setting the entity rendering model                      |

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

Example:

```json5
{
  "schema_style": "conium",
  "identifier": "awa:conium",
  "templates": {
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
      "is_weapon": false
    },
    // Setting this tool item can destroy blocks in creative mode.
    "can_destroy_in_creative": true,
    // The max item stack size.
    // The size value must in range 1 to 64.
    "max_count": 64
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

Example:

```json5
{
  "minecraft:item": {
    // Description and identifier must present.
    "description": {
      "identifier": "awa:bedrock"
    },
    "components": {
      // The damage amount when hitting the entity.
      "minecraft:damage": 10,
      // The durability amount of tool item.
      // When damage chance is missing, it can be abbreviated as:
      //     "minecraft:durability": <int>
      "minecraft:durability": {
        // The amount.
        "max_durability": 50
      },
      // The max item stack size.
      // The size value must in range 1 to 64.
      // It can be abbreviated as:
      //     "minecraft:max_stack_size": <int>
      "minecraft:max_stack_size": {
        "value": 64
      }
    },
  }
}
```

## Conium script APIs

See the [Conium scripting](./document/script/kotlin/README.md).

## Bedrock script APIs

Not completed bedrock script APIs supports now, only framework able to runs the sample.

### Grammars

About typescript grammar, supported by ```language-translator``` or called ```fluxia```: [Typescript supports](https://github.com/cao-awa/language-translator/tree/main/doc/typescript)

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
