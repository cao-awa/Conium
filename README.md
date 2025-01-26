# What is Conium

Conium is a datapack and script framework for the modern Minecraft, it provides very diverse features without Java coding, Conium lets you complete your mods only using datapacks.

![](https://count.getloli.com/@@cao-awa.conium?name=%40cao-awa.conium&theme=rule34&padding=7&offset=0&align=top&scale=1&pixelated=1&darkmode=auto)

## Requirements

|            Requirement |        Version        |  Installs   |            Notes             |
|-----------------------:|:---------------------:|:-----------:|:----------------------------:|
|                   Java |         21!!          |    Need     |      Only 21 can be use      |
|              Minecraft |       1.21.4!!        |    Need     |    Only 1.21.4 can be use    |
| Fabric language kotlin | 1.13.0+kotlin.2.1.0!! |    Need     | Only kotlin 2.1.0 can be use |
|             Fabric API |           ?           |    Need     |      Any version is ok       |
|                 Fluxia |        1.1.1!!        | Unnecessary | Built-in contents translator |

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

|   Name |                                                  Conium API type                                                  | Gametest API type |      Status      |
|-------:|:-----------------------------------------------------------------------------------------------------------------:|:-----------------:|:----------------:|
| system | [AbstractBedrockSystem](./common/src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt) |      System       | Alpha (Unstable) |
|  world |  [AbstractBedrockWorld](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt)   |       World       | Alpha (Unstable) |

In APIs:

+ world
    + [AbstractBedrockWorld](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt) impl by [BedrockWorld](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/BedrockWorld.kt) as known as 'World' in SAPI
        + player
            + [BedrockPlayer](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/BedrockPlayer.kt) as known as 'Player' in SAPI
                + delegate
                    + [BedrockPlayerDelegate](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt) as known as 'Player[]' in SAPI
                + screen
                    + [BedrockOnScreenDisplay](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt) as known as 'ScreenDisplay' in SAPI
    + dimension
        + [BedrockDimensionLocation](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/dimension/BedrockDimensionLocation.kt) as known as 'DimensionLocation' in SAPI
+ system
    + [AbstractBedrockSystem](./common/src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt) impl by [BedrockSystem](./common/src/main/java/com/github/cao/awa/conium/bedrock/system/BedrockSystem.kt) as known as 'System' in SAPI
+ item
    + stack
        + [BedrockItemStack](./common/src/main/java/com/github/cao/awa/conium/bedrock/item/stack/BedrockItemStack.kt) as known as 'ItemStack' in SAPI
+ script
    + [BedrockScriptAnonymousObjectMap](./common/src/main/java/com/github/cao/awa/conium/bedrock/item/stack/BedrockItemStack.kt) as known as 'anonymous object' in Javascript
+ event
    + [BedrockBeforeEvents](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/BedrockBeforeEvents.kt) as known as 'WorldBeforeEvents' in SAPI
        + context
            + [BedrockEventContext](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/context/BedrockEventContext.kt)
            + item
                + use
                    + [BedrockItemUseOnEventContext](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/context/item/use/BedrockItemUseOnEventContext.kt) as known as 'ItemUseOnBeforeEvent' and 'ItemUseOnAfterEvent' in SAPI
    + item
        + use
            + [BedrockItemUseOnBeforeEvent](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/item/use/BedrockItemUseOnBeforeEvent.kt) as known as 'ItemUseOnBeforeEventSignal' in SAPI

By bedrock script APIs:

+ [world](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt)
    + ```fun``` [getPlayers()](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt): [BedrockPlayerDelegate](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt)
        + ```operator fun``` [get](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt): [BedrockPlayer](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/BedrockPlayer.kt)(index: Int)
    + ```val``` [onScreenDisplay](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/delegate/BedrockPlayerDelegate.kt): [BedrockOnScreenDisplay](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt)
        + ```fun``` [setTitle](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt)(title: String, properties: [BedrockScriptAnonymousObjectMap](./common/src/main/java/com/github/cao/awa/conium/bedrock/script/BedrockScriptAnonymousObjectMap.kt))
        + ```fun``` [updateSubtitle](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/player/screen/BedrockOnScreenDisplay.kt)(title: String)
    + ```val``` [eventsBefore](./common/src/main/java/com/github/cao/awa/conium/bedrock/world/AbstractBedrockWorld.kt) = ```get()```: [BedrockBeforeEvents](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/BedrockBeforeEvents.kt)
        + ```val``` [itemUseOn](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/BedrockBeforeEvents.kt): [BedrockItemUseOnEvent](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/item/use/BedrockItemUseOnBeforeEvent.kt)
            + ```fun``` [subscribe](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/item/use/BedrockItemUseOnBeforeEvent.kt)(action: ([BedrockItemUseOnEventContext](./common/src/main/java/com/github/cao/awa/conium/bedrock/event/context/item/use/BedrockItemUseOnEventContext.kt)) ->
              Unit)
+ [system](./common/src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt)
    + ```fun``` [runInterval](./common/src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt)(callback: () -> Unit, tickInterval: Int): IntegerReceptacle
    + ```fun``` [clearRun](./common/src/main/java/com/github/cao/awa/conium/bedrock/system/AbstractBedrockSystem.kt)(runId: IntegerReceptacle)

# Source code and commit

Conium has large and complex source codes and architectures.\
Read source codes still very challenging even though we are completing documents and comments try we best.

So if you are willing to contribute to Conium, please do conversations with Conium developers to learn the details and specifications.\
We will reject all pull requests that not after discussion.

If it is not necessary, avoid directly reading source codes.