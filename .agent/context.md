```markdown
# Conium Context for AI Coding Assistants

## Project Summary
Conium is a datapack and script framework for Minecraft 1.21.11+, enabling modding without Java via data-driven content, Conium Kotlin scripts, and partial Bedrock MoLang scripts. It targets Fabric (primary) with incomplete NeoForge support, running mostly client-side (server sync WIP). Built on event-driven, context-oriented architecture with aspect-like features for scripting and APIs.

## Tech Stack
- **Languages**: Kotlin 2.2.10 (strict; no other versions), Java 21+
- **Build**: Gradle 8.14.2+ (wrapper in `/gradle/wrapper`), Fabric Loom 1.13-SNAPSHOT
- **Minecraft**: Fabric API (any), Minecraft >=1.21.11
- **Parsing**: ANTLR grammars (`grammar/molang/*.g4` for MoLang)
- **Scripting**: Custom Kotlin evaluator (requires Kotlin native), structuring-translator for TS/MoLang
- **Other**: DataPacks, Bedrock addons/samples (`samples/`)

## Key Files (Read First)
- **Root**: `README.md` (overview, requirements, warnings), `CHANGE_LOG.md`, `settings.gradle`, `build.gradle`
- **Core**: `common/src/main/kotlin/com/github/cao/awa/conium/` (event system, scripting, hitokoto)
- **Mods**: `fabric/src/main/kotlin/` (Fabric entrypoint), `neoforge/src/main/kotlin/` (WIP)
- **Docs**: `document/data-driven/README.md`, `document/script/kotlin/README.md`, `planing/README.md`
- **Samples/Tests**: `samples/bedrock/`, `samples/conium/test/`, `molang/test/test_molang.txt`
- **Grammars**: `grammar/molang/Molang.g4`, `MolangRules.g4`

## Architecture
- **Modules**: `common` (shared core: events, contexts, scripting, MoLang parser); `fabric`/`neoforge` (loaders/mods).
- **Core Flow**: Event-driven dispatcher → Context-oriented handlers (aspects via traits/extensions) → Script eval (Kotlin/MoLang).
- **Components**:
  | Component | Location | Purpose |
  |-----------|----------|---------|
  | Event System | `common/src/main/kotlin/.../event/` | Dispatches Minecraft ticks, custom events |
  | Scripting | `common/src/main/kotlin/.../script/` | Kotlin script loader/evaluator; Bedrock API stubs |
  | Data-Driven | `document/data-driven/` | Bedrock/Conium formats (JSON-like packs) |
  | Parser | `common/src/main/kotlin/.../molang/` | ANTLR-based MoLang → AST → eval |
- No event-sourcing; state via Minecraft contexts.

## Patterns & Conventions
- **Naming**: Kebab-case files/gradles, PascalCase classes, snake_case vars; Japanese/English Hitokoto in `ConiumHitokoto.kt`.
- **Structure**: Multi-module Gradle (`settings.gradle`); Kotlin DSL in build.gradle.kts where possible.
- **Kotlin Idioms**: Coroutines for async (ticks/scripts), sealed classes for events/states, extension functions for aspects.
- **Conventions**: `@Composable` for UI/contexts; strict null-safety; logging via custom `ConiumLogger`.
- **Scripts**: DataPacks trigger Conium scripts; MoLang via custom runtime.

## Common Tasks
- **Build/Run**: `./gradlew remapJar` (Fabric JAR); reload Gradle in IDE after clone.
- **Add Feature**:
  1. Discuss on Discord/Issues (required; PRs without declined).
  2. Add to `common` (events/script APIs), expose in `fabric`.
  3. Data-driven: Extend `document/data-driven/conium/` schemas.
  4. Script API: `document/script/kotlin/` + impl in `script/`.
- **Fix Bugs**: Repro in samples; trace events in `common/src/main/kotlin/.../event/`.
- **Add Script Support**: Extend MoLang in `grammar/` + `molang/` parser; Kotlin via native compiler.

## Testing
- **Run Samples**: Load `samples/` packs in Minecraft; check console Hitokoto/logs.
- **MoLang Tests**: `molang/test/test_molang.txt` → Parse/eval via `MolangParser`.
- **No Unit Tests Visible**: Add via Kotest/JUnit in `common/src/test/`; run `./gradlew test`.
- **Integration**: Dev Minecraft via `./gradlew runClient` (Fabric).

## Important Notes
- **Strict Versions**: Kotlin **2.2.10 only** (native req); mismatches → build fails.
- **Client-Focused**: Full features client-side; server runs Conium scripts (no Bedrock APIs).
- **NeoForge WIP**: Kotlin native conflicts; no secure JAR support yet.
- **Gotchas**: Gradle reload after changes; Java 21+ mandatory; PRs need team OK.
- **Community**: Discord (https://discord.com/invite/BUY2xQr37N); Hitokoto PRs English/Japanese only.
- **Lines**: ~350 total.
```
```