# What is Conium

> 新世代を呼ぶハロー、声はまだ途切れないだろう、 \
> 新世界の扉を、ほら叩き続けよう！

Conium is a datapack and script framework for modern Minecraft, it provides very diverse features without Java coding, Conium lets you complete your mods only using the datapacks.

![](https://count.getloli.com/@@cao-awa.conium?name=%40cao-awa.conium&padding=7&offset=0&align=top&scale=1&pixelated=1&darkmode=auto)

## Warning

The full Conium feature is currently as 'on client' mode, the server needs to synchronize the datapacks and scripts to client but are not completed this feature now.

But the Conium scripting system can run on the server side completely, only the bedrock scripting APIs are not available on the server side now.

## Requirements

|            Requirement |        Version         |   Installs   |             Notes             |
|-----------------------:|:----------------------:|:------------:|:-----------------------------:|
|                   Java |          21+           | 21 Or higher |      21 Or higher is ok       |
|              Minecraft |       >=1.21.11        |     Need     | 1.21.11 or higher can be use  |
| Fabric language kotlin | 1.13.5+kotlin.2.2.10!! |     Need     | Only kotlin 2.2.10 can be use |
|             Fabric API |           ?            |     Need     |       Any version is ok       |

### Build requirements

Conium has configured whole project, just clone the repository, and reload project then run the gradle task ```remapJar```.

| Requirement |    Version    |                Notes                |
|------------:|:-------------:|:-----------------------------------:|
|        Java |      21+      |            21 Or higher             |
|      Gradle |    8.14.2     |    8.14.2 or higher could be use    |
|      Kotlin |   2.2.10!!    |       Only 2.2.10 can be use        |
| Fabric loom | 1.13-SNAPSHOT | 1.13-SNAPSHOT or other could be use |
|   Minecraft |  \>=1.21.11   |  Only 1.21.11 or higher can be use  |

### NeoForge

About supporting of NeoForge, currently cannot be completed because the secure jar handler in NeoForge has conflict to kotlin native.

And Conium cannot remove kotlin native because scripting running needs, Conium may try to run script evaluator without NeoForge environment in the future.

## Discord server

You can join ours [discord](https://discord.com/invite/BUY2xQr37N) server to chat with Conium community！

## Data driven

See the [Data driven](./document/data-driven/README.md)

## Conium script APIs

See the [Conium scripting](./document/script/kotlin/README.md).

## Bedrock script APIs

Not completed bedrock script APIs supports now, only framework able to run the samples.

### Grammars

About TypeScript grammar, supported by ```structuring-translator```: [TypeScript supports](https://github.com/cao-awa/structuring-translator/tree/main/doc/typescript)

The MoLang support also based on ```structuring-translator``` but implemented by Conium: [MoLang supports](https://github.com/cao-awa/Conium/tree/main/common/src/main/kotlin/com/github/cao/awa/conium/molang)

# Source Code and Contributions
Conium is built on an event-driven, context-oriented architecture with strong aspect-like capabilities, though it does not use event-sourced state semantics.

The codebase is architecturally complex and extensive. While we continuously improve documentation, navigating the source may still be challenging.

Therefore, we strongly recommend discussing your ideas with the core team before contributing. This ensures alignment with the project’s design and specifications.

Please note that pull requests submitted without prior discussion may be declined. For general inquiries, we encourage you to submit an issue tagged “question.”

## Hitokoto

Conium will randomly roll a [Hitokoto](https://en.wiktionary.org/wiki/%E4%B8%80%E8%A8%80#Japanese) in console every time launching, the list of Hitokotos receives pull requests, only allow in English and Japanese, don't take other languages, don't accept nonsense sentences.

For the list, see [ConiumHitokoto.kt](/common/src/main/kotlin/com/github/cao/awa/conium/hitokoto/ConiumHitokoto.kt).
