# Conium Codebase Overview

Quick-reference for AI assistants. Multi-module Gradle project for Minecraft mod ("Conium") supporting Fabric/NeoForge loaders, Molang parsing, and Bedrock-style data-driven content.

## File Index
- **README.md**: Project setup, features, usage.
- **CHANGE_LOG.md**: Version history and changes.
- **LICENSE**: Project license (likely MIT/Apache).
- **settings.gradle**: Defines modules (common, fabric, neoforge).
- **build.gradle** (root): Root Gradle config, plugins, versions.
- **fabric/build.gradle**: Fabric mod build, mappings, loom.
- **neoforge/build.gradle**: NeoForge mod build.
- **common/build.gradle**: Shared code module.
- **grammar/molang/Molang.g4**: ANTLR v4 grammar for Molang parser.
- **document/data-driven/README.md**: Bedrock/Conium data-driven docs.
- **planing/README.md**: Development planning.

## Directory Map
- **common/**: Shared Java/Kotlin sources, resources (multi-loader).
- **fabric/**: Fabric-specific src/main (mod init), build props.
- **neoforge/**: NeoForge-specific src/main (mod entry), build props.
- **document/**: Docs; `data-driven/` (Bedrock/Conium examples); `script/kotlin/` (Kotlin scripts).
- **grammar/**: ANTLR grammars (`molang/` for Molang lexer/parser).
- **molang/**: Molang test inputs (`test/`).
- **samples/**: Examples (`bedrock/addons/`, `conium/test/`).
- **planing/**: Planning docs (`data-driven/bedrock.md`).
- **gradle/**: Wrapper (JAR, props).
- **Root files**: Gradle wrapper scripts (`gradlew*`), props, IDEA theme.

## Entry Points
- **Build/Run**: `./gradlew build` (root); `./gradlew runClient` (fabric/neoforge).
- **Mod Init**: `fabric/src/main/java/.../ConiumMod.java` (Fabric initializer); equiv. in neoforge.
- **Parser Gen**: ANTLR tasks in build.gradle generate Molang parser from grammars.
- **Scripts**: `document/script/kotlin/` for data-driven tooling.

## Key Functions/Classes
- **MolangParser/Visitor**: Auto-generated from `Molang.g4` (animation/query lang).
- **Conium Mod Classes**: Loader entrypoints in `fabric/neoforge/src/main` (e.g., init, events).
- **Common Utils**: Data-driven models in `common/src/main` (Bedrock compat).
- **Gradle Tasks**: `generateGrammarSource`, `remap` (Loom/NeoForge).

## Dependencies
- **Gradle/Plugins**: Loom (Fabric), NeoForge MDK, Shadow (fabric/neoforge); ANTLR4.
- **Minecraft**: Fabric Loader/API, NeoForge, Minecraft (1.20+ mappings).
- **Lang**: Kotlin (scripts/docs), Java (core).
- **Other**: Bedrock data formats (JSON schemas in docs/samples).
