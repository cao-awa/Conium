# What is Conium

Conium is datapack and script framework for the modern Minecraft, it provides very diverse features without Java coding,
Conium let you complete your mods only using datapack.

## Requirements

|            Requirement |        Version         |             Notes              |
|-----------------------:|:----------------------:|:------------------------------:|
|                   Java |          21!!          |       Only 21 can be use       |
|              Minecraft |        1.21.1!!        |      Only 1.21 can be use      |
| Fabric language kotlin | 1.12.3+kotlin.2.0.21!! | Only kotlin 2.0.21 can be use  |
|             Fabric API |           ?            |       Any version is ok        |

## datapack structure

+ \<path-to-save\>/datapacks
    + \<pack-name\>
        + data
            + item
                + This directory is items, data type is '.json'
            + block
                + This directory is blocks, data type is '.json'
            + property
                + item
                    + This directory is item properties injectors, data type is '.json'
                + block
                    + This directory is block properties injectors, data type is '.json', current is no impls.
            + scripts
                + This directory is scripts, data type is 'kts', '.ts' and '.js'

## bedrock script api

Not completed bedrock script api supports now, only framework able to runs the sample.

### grammars

About typescript grammar, supported by '
language-translator': [Typescript supports](https://github.com/cao-awa/language-translator/tree/main/doc/typescript)

### APIs

Currently, there's API is available:

In context:

|   Name |                                              Conium API type                                               | Gametest API type |      Status      |
|-------:|:----------------------------------------------------------------------------------------------------------:|:-----------------:|:----------------:|
| system | [AbstractBedrockSystem](./src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt) |      System       | Alpha (Unstable) |
|  world |  [AbstractBedrockWorld](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt)   |       World       | Alpha (Unstable) |

In APIs:

+ world
    + [AbstractBedrockWorld](./src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt) impl by [BedrockWorld](./src/main/java/com/github/cao/awa/conium/bedrock/world/BedrockWorld.kt) as known as 'System' in SAPI
        + player
            + [BedrockPlayer](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/BedrockPlayer.kt) as known as 'Player' in SAPI
                + delegate
                    + [BedrockPlayerDelegate](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt) as known as 'Player[]' in SAPI
                + screen
                    + [BedrockOnScreenDisplay](./src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt) as known as 'ScreenDisplay' in SAPI
    + dimension
        + [BedrockDimensionLocation](./src/main/java/com/github/cao/awa/conium/bedrock/world/dimension/BedrockDimensionLocation.kt) as known as 'DimensionLocation' in SAPI
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