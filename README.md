# What is Conium

Conium is a datapack and script framework for the modern Minecraft, it provides very diverse features without Java coding,
Conium lets you complete your mods only using datapacks.

![](https://count.getloli.com/@@cao-awa.conium?name=%40cao-awa.conium&theme=rule34&padding=7&offset=0&align=top&scale=1&pixelated=1&darkmode=auto)

## Requirements

|            Requirement |        Version        |  Installs   |            Notes             |
|-----------------------:|:---------------------:|:-----------:|:----------------------------:|
|                   Java |         21!!          |    Need     |      Only 21 can be use      |
|              Minecraft |       1.21.4!!        |    Need     |    Only 1.21.4 can be use    |
| Fabric language kotlin | 1.13.0+kotlin.2.1.0!! |    Need     | Only kotlin 2.1.0 can be use |
|             Fabric API |           ?           | Unnecessary |      Any version is ok       |
|                 Fluxia |     1.1.0-fix2!!      | Unnecessary | Built-in contents translator |

### Build requirements

Conium has configured whole project, just clone the repository, and reload project then run the gradle task ```remapJar```.

| Requirement | Version  |            Notes            |
|------------:|:--------:|:---------------------------:|
|        Java |   21!!   |     Only 21 can be use      |
|      Gradle |   8.11   | 8.11 or other could be use  |
|      Kotlin | 2.1.0!!  |    Only 2.1.0 can be use    |
| Fabric loom |  1.9.2   | 1.9.2 or other could be use |
|   Minecraft | 1.21.4!! |   Only 1.21.4 can be use    |

## Data driven

See the [Data driven](./document/data-driven/README.md)

## Conium script APIs

See the [Conium scripting](./document/script/kotlin/README.md).

## Bedrock script APIs

Not completed bedrock script APIs supports now, only framework able to runs the sample.

### Grammars

About typescript grammar, supported by ```structring-translator``` or called ```fluxia```: [Typescript supports](https://github.com/cao-awa/structring-translator/tree/main/doc/typescript)

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
