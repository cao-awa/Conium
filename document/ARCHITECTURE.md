# Conium Architecture

## Overview

Conium is a datapack and script framework for modern Minecraft (version 1.21.11+), enabling mod-like functionality without requiring Java coding. It allows developers to implement diverse features using datapacks, Kotlin scripts, and Bedrock scripting APIs. The framework emphasizes data-driven development, supports Molang expression parsing, and operates primarily in "on client" mode (with server-side scripting fully supported, though Bedrock APIs are client-only and server datapack synchronization is incomplete).

Key purposes:
- Provide an event-driven, context-oriented architecture with aspect-like capabilities for Minecraft modding.
- Enable scripting via Kotlin and partial Bedrock APIs.
- Support Fabric Loader as the primary platform, with partial NeoForge compatibility (limited by Kotlin native conflicts).
- Facilitate no-compile modding through datapacks and scripts.

The project is not event-sourced but leverages events for reactivity. It includes random "Hitokoto" (one-sentence quotes in English/Japanese) display on launch.

## Tech Stack

| Technology          | Version              | Notes |
|---------------------|----------------------|-------|
| Kotlin              | 2.2.10              | Primary language; strict version requirement for Fabric Kotlin support. |
| Gradle              | 8.14.2              | Build system; 8.14.2+ supported. |
| Fabric Loader       | 1.13.5              | Primary mod loader; uses Fabric Kotlin 1.13.5+kotlin.2.2.10. |
| Fabric Loom         | 1.13-SNAPSHOT       | Minecraft mod development plugin. |
| NeoForge            | Partial support     | Incomplete due to secure JAR handler conflicts with Kotlin native. |
| Minecraft           | >=1.21.11           | Target runtime environment. |
| Java                | 21+                 | Runtime and build requirement. |
| ANTLR (inferred)    | N/A (grammar files) | Used for Molang parser via `Molang.g4` and `MolangRules.g4`. |

No `package.json` or Node.js dependencies; all managed via Gradle.

## Project Structure

Conium is a multi-module Gradle project with platform-specific mod modules sharing a common library. The structure supports building Fabric and NeoForge variants.

```
├── CHANGE_LOG.md          # Project changelog.
├── LICENSE                # Project license.
├── README.md              # Main documentation, requirements, and usage.
├── build.gradle           # Root build script (configures plugins, repositories).
├── common                 # Shared library module.
│   ├── build.gradle       # Module build script.
│   └── src/main           # Common source code (e.g., Molang parser, scripting engine, Hitokoto).
├── document               # Documentation submodules.
│   ├── data-driven        # Data-driven feature docs (bedrock/, conium/ subdirs, README.md).
│   └── script/kotlin      # Kotlin scripting API docs (README.md implied).
├── fabric                 # Fabric mod module.
│   ├── build.gradle       # Fabric-specific build (uses Loom).
│   ├── gradle.properties  # Fabric mod metadata (mod ID, version).
│   └── src/main           # Fabric entrypoints and platform integrations.
├── gradle/wrapper         # Gradle wrapper (gradle-wrapper.jar, gradle-wrapper.properties).
├── gradle.properties      # Root properties (e.g., versions, Minecraft mappings).
├── gradlew / gradlew.bat  # Gradle wrappers for Unix/Windows.
├── grammar/molang         # ANTLR grammars for Molang parser.
│   ├── Molang.g4
│   └── MolangRules.g4
├── idea/appearance        # IDE settings (IntelliJ color scheme).
├── molang/test            # Molang parser tests (test_molang.txt).
├── neoforge               # NeoForge mod module (partial).
│   ├── build.gradle       # NeoForge-specific build.
│   ├── gradle.properties  # NeoForge mod metadata.
│   └── src/main           # NeoForge entrypoints (limited implementation).
├── planing                # Planning docs (README.md, data-driven/bedrock.md).
├── samples                # Example datapacks and scripts.
│   ├── bedrock            # Bedrock API samples (addons/, sapi-samples/).
│   └── conium/test        # Conium-specific samples.
└── settings.gradle        # Includes common, fabric, neoforge modules.
```

- **Root**: Orchestrates builds; `remapJar` task produces runnable JARs.
- **common**: Core logic (scripting, Molang, data-driven, `ConiumHitokoto.kt`).
- **fabric/neoforge**: Platform adapters; share common via Gradle dependencies.
- **document/planing**: Non-code docs for data-driven and scripting.
- **grammar/samples**: Tools and examples for Molang and APIs.

## Architecture Diagram

```
+-------------------+     +-------------------+
| Minecraft 1.21+   |     |   NeoForge        |
|   (Client/Server) |     |   (Partial)       |
+-------------------+     +-------------------+
          |                       |
          +-----------+-----------+
                      |
                +-----+-----+
                | Fabric    |
                | Loader    |
                +-----+-----+
                      |
                +-----+-----+
                | Conium Mod |  <-- Platform entrypoints
                | (Fabric/   |
                |  NeoForge) |
                +-----+-----+
                      |
                +-----+-----+
                |  Common    |  <-- Shared library
                | Library    |
                | - Molang   |
                |   Parser   |
                | - Scripting|
                |   (Kotlin/ |
                |    Bedrock)|
                | - Data-    |
                |   driven   |
                | - Events/  |
                |   Contexts |
                +-----+-----+
                      |
                +-----+-----+
                | Datapacks  |  <-- JSON/Molang configs
                | & Scripts  |     + Samples (bedrock/conium)
                +-----+-----+
                      |
                v     v
            Game State Updates
            (Client-focused)
```

- Arrows represent dependency flow: Platforms load common, which processes datapacks/scripts.
- Event-driven: Events from Minecraft trigger script evaluation in contexts.

## Key Components

| Component              | Location/Module       | Description |
|------------------------|-----------------------|-------------|
| Common Library         | `common/src/main`     | Core shared code: scripting engine, Molang parser, data-driven features, Hitokoto system. |
| Fabric Mod             | `fabric/src/main`     | Primary mod implementation; integrates common with Fabric Loader events. |
| NeoForge Mod           | `neoforge/src/main`   | Partial port; limited by Kotlin native issues. |
| Molang Parser          | `grammar/molang/*.g4`, `common/src/main/kotlin/.../molang` | ANTLR-based grammar for parsing Molang expressions (used in datapacks/scripts). |
| Data-Driven Features   | `document/data-driven`, `common` | Configurable via datapacks; supports Bedrock/Conium formats. |
| Kotlin Scripting APIs  | `document/script/kotlin`, `common` | Server-compatible scripting; event/context-oriented. |
| Bedrock Scripting APIs | `samples/bedrock`, `common` | Client-only (incomplete); framework runs samples. |
| Hitokoto System        | `common/src/main/kotlin/.../ConiumHitokoto.kt` | Displays random English/Japanese quotes on launch. |

## Data Flow

1. **Initialization**: Minecraft loads mod (Fabric/NeoForge) → Injects common library → Parses datapacks (JSON + Molang via grammars).
2. **Event Trigger**: Minecraft events (e.g., ticks, player actions) propagate to Conium contexts.
3. **Script Evaluation**: Events invoke Kotlin/Bedrock scripts → Molang expressions parsed/evaluated → Aspect-like behaviors applied.
4. **Data Processing**: Data-driven configs load into runtime contexts → Scripts mutate game state (client-focused).
5. **Output**: State updates (e.g., entities, blocks) on client; server runs scripts but lacks Bedrock APIs/sync.
6. **Feedback**: Contexts provide reactivity; no persistent state (event-driven only).

Flow is reactive: Datapacks → Parse (Molang) → Event → Script → Game.

## Configuration

- **Gradle Properties**:
  | File                  | Purpose |
  |-----------------------|---------|
  | `gradle.properties` (root) | Global versions (Kotlin 2.2.10, MC 1.21.11, mappings). |
  | `fabric/gradle.properties` | Fabric mod ID, version, entrypoints. |
  | `neoforge/gradle.properties` | NeoForge mod metadata. |

- **No Environment Variables**: Configuration is Gradle-centric; no runtime env vars documented.
- **Mod Configs**: Inferred via datapacks (data-driven); no static config files in structure.
- **Build**: Run `./gradlew remapJar` after cloning/reloading project.

## Dependencies

| Dependency       | Purpose | Version/Notes |
|------------------|---------|---------------|
| Fabric API       | Minecraft modding APIs (events, registries). | Any compatible version. |
| Fabric Loom      | Gradle plugin for Fabric mod building/mapping. | 1.13-SNAPSHOT+. |
| Fabric Kotlin    | Kotlin support for Fabric. | 1.13.5+kotlin.2.2.10 (strict). |
| Kotlin Stdlib    | Language runtime. | 2.2.10. |
| ANTLR            | Molang grammar parsing. | Inferred from `.g4` files. |
| structuring-translator (external) | Basis for TypeScript/Molang support (linked in README). | Not bundled; referenced. |

Dependencies managed in `build.gradle` files (root/module). No transitive runtime deps beyond Minecraft/Fabric ecosystem. NeoForge conflicts prevent full dep resolution.