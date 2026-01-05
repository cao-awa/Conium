# Conium API Documentation

## Overview

Conium is a datapack and script framework for modern Minecraft (version >=1.21.11), enabling mod-like functionality without Java coding. Developers use datapacks, Kotlin scripts, and data-driven configurations to extend gameplay. The framework emphasizes **data-driven design** (e.g., Bedrock-style addons and Conium-specific configs) and **scripting support** (Kotlin for server-side execution, partial Bedrock Script API compatibility on client).

### API Design Philosophy and Conventions
- **Event-driven architecture**: Core system reacts to Minecraft events (e.g., ticks, player actions) without event-sourced state.
- **Context-oriented**: Scripts and data operate in Minecraft's world/player contexts.
- **Aspect-like capabilities**: Modular, composable behaviors via scripts and datapacks.
- **No Java required**: Everything is datapack/script-based; Kotlin scripts run natively via the framework.
- **Cross-loader support**: Fabric (primary) and experimental NeoForge (limited due to Kotlin native conflicts).
- **Grammars and parsing**: MoLang expressions parsed via custom ANTLR grammars (`grammar/molang/Molang.g4`, `MolangRules.g4`). TypeScript-like support via external `structuring-translator`.
- **Hitokoto integration**: Console logs random English/Japanese phrases on launch (`common/src/main/kotlin/com/github/cao/awa/conium/hitokoto/ConiumHitokoto.kt`).
- **Samples**: Available in `samples/bedrock` (addons, SAPI samples) and `samples/conium/test`.

Full features are client-side only (server sync incomplete). Server-side scripting works fully, but Bedrock APIs are client-only.

**Documentation References**:
- Data-driven: `./document/data-driven/README.md`, `./document/data-driven/bedrock/`, `./document/data-driven/conium/`.
- Conium Kotlin scripting: `./document/script/kotlin/README.md`.
- Planning: `./planing/README.md`, `./planing/data-driven/bedrock.md`.

Contributions require core team discussion due to complex architecture.

## Authentication

Not applicable. Conium operates within the Minecraft game environment (client/server). No external auth mechanisms exist. Access is controlled via Minecraft datapack loading and mod installation.

## Base URL

Not applicable. Conium is not a networked web service. It exposes **in-game interfaces** via:
- Datapack functions/commands.
- Kotlin script evaluation.
- Data-driven JSON configs.
- Minecraft events/ticks.

## Interfaces

Conium provides no HTTP/REST endpoints. Instead, it offers **scripting and data-driven interfaces** for Minecraft modding. Below are the primary developer interfaces based on the codebase.

### 1. Build and Development CLI (Gradle Tasks)
Conium uses Gradle (8.14.2+) for building across Fabric/NeoForge. Run via `./gradlew` (Unix) or `gradlew.bat` (Windows). Key tasks (inferred from standard Fabric Loom/NeoForge setup and README):

| Task          | Description                          | Usage Example                  | Notes |
|---------------|--------------------------------------|--------------------------------|-------|
| `remapJar`   | Builds and remaps the final mod JAR for distribution. | `./gradlew remapJar`          | Primary release task. Outputs to `build/libs/`. |
| `build`      | Builds JARs without remapping.       | `./gradlew build`             | For dev/testing. |
| `runClient`  | Launches Minecraft client with Conium loaded. | `./gradlew runClient`     | Tests client-side features (e.g., Bedrock APIs). |
| `runServer`  | Launches dedicated server with Conium. | `./gradlew runServer`      | Tests server-side scripting (no Bedrock APIs). |

**Requirements**:
- Java 21+.
- Kotlin 2.2.10 (strict).
- Fabric Loom 1.13-SNAPSHOT+.
- Minecraft mappings for >=1.21.11.

Project configured in `build.gradle`, `fabric/build.gradle`, `neoforge/build.gradle`, `common/build.gradle`, `settings.gradle`.

**Example**:
```
git clone <repo>
./gradlew remapJar
# Install generated JAR via Fabric/NeoForge loader.
```

### 2. Conium Kotlin Scripting Interface
- **Entry Point**: Scripts in `./document/script/kotlin/` and `samples/conium/test/`.
- **Execution**: Server-side fully supported; client sync pending.
- **Capabilities**: Event handling, context-aware execution, MoLang integration.
- **Usage**: Load via datapacks. Scripts evaluate in Minecraft's Kotlin runtime (no external deps).
- **Samples**: `samples/conium/test/` – Run via modded client/server.
- **Grammar Integration**: MoLang parsed for expressions (`molang/test/test_molang.txt`).

**Example Script Loading** (hypothetical based on docs; see `./document/script/kotlin/README.md`):
```kotlin
// In a datapack script file
import com.github.cao.awa.conium.* // Core Conium APIs
event.onTick { world ->
    // Context-oriented logic
}
```

### 3. Data-Driven Interface (Bedrock/Conium)
- **Formats**: JSON configs in `./document/data-driven/bedrock/`, `./document/data-driven/conium/`.
- **Bedrock Compatibility**: Partial Script API (SAPI) support. Framework runs samples; full APIs incomplete.
- **Samples**: `samples/bedrock/addons/`, `samples/bedrock/sapi-samples/`.
- **Usage**: Place in datapack `data/<namespace>/` folders. Reload via `/reload`.

**Key Components**:
- **Bedrock Addons**: Behavior/entity packs mimicking Bedrock edition.
- **Conium Data**: Custom data-driven features (see `./document/data-driven/conium/`).

**Example Structure** (from file layout):
```
data/
├── mypack/
│   ├── functions/
│   │   └── conium_script.kts  # Kotlin script
│   └── data/
│       └── conium/
│           └── events.json    # Event configs
```

### 4. MoLang Expression Interface
- **Parser**: ANTLR-based (`grammar/molang/*.g4`).
- **Usage**: Embed in datapacks/scripts for dynamic expressions (e.g., `math.random(1, 10)`).
- **Tests**: `molang/test/test_molang.txt`.

## Rate Limiting

Not applicable. No networked requests. Minecraft tick rates and script execution are governed by game loops (20 ticks/second).

## Webhooks

Not applicable. No external event emission. Use Minecraft's event system for in-game notifications.

## Error Handling

- **Console Logs**: Hitokoto + errors on launch/load.
- **Runtime**: Script failures logged to Minecraft logs (`latest.log`).
- **Common Issues**:
  | Error | Cause | Fix |
  |-------|-------|-----|
  | Kotlin version mismatch | Not 2.2.10 | Update `build.gradle`. |
  | NeoForge conflicts | Secure JAR handler | Use Fabric; NeoForge unsupported. |
  | Client-server sync | Incomplete feature | Run client-side only. |

For full details, join [Discord](https://discord.com/invite/BUY2xQr37N) or check `CHANGE_LOG.md`. Source navigation recommended with core team guidance.