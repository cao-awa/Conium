# SETUP.md

## Prerequisites

Before setting up Conium, ensure the following software and versions are installed. Conium is a Fabric-based Minecraft mod using Kotlin and Gradle.

| Requirement          | Minimum Version          | Notes                                                                 |
|----------------------|--------------------------|-----------------------------------------------------------------------|
| Java                 | 21                      | Java 21 or higher required for building and running.                  |
| Minecraft            | 1.21.11                 | Minecraft 1.21.11 or higher required.                                 |
| Gradle               | 8.14.2                  | Gradle 8.14.2 or higher; project is pre-configured.                   |
| Kotlin               | 2.2.10                  | **Strictly 2.2.10 only**; used via Fabric Language Kotlin.            |
| Fabric Loader        | 1.13.5                  | Bundled via Fabric Language Kotlin 1.13.5+kotlin.2.2.10.              |
| Fabric Loom          | 1.13-SNAPSHOT           | For development; other versions may work.                             |
| Fabric Language Kotlin | 1.13.5+kotlin.2.2.10  | **Required**; ensures Kotlin 2.2.10 compatibility.                    |
| Fabric API           | Any                     | Required for Minecraft runtime.                                       |

**Notes**:
- NeoForge support is partial and incomplete due to conflicts with Kotlin native libraries. Use Fabric Loader for full functionality.
- Conium runs primarily in client mode; server-side scripting is supported, but Bedrock APIs are client-only.

## Quick Start

1. Clone the repository: `git clone https://github.com/cao-awa/Conium.git && cd Conium`.
2. Open the project in an IDE (e.g., IntelliJ IDEA) and reload Gradle (or run `./gradlew wrapper` if needed).
3. Run `./gradlew remapJar` to build the JAR.
4. Copy `build/libs/conium-*.jar` to your Minecraft `mods/` folder (Fabric Loader 1.13.5+ on Minecraft 1.21.11+).
5. Launch Minecraft with Fabric Loader.

Conium will load datapacks and scripts automatically.

## Detailed Installation

### Clone Repository

```bash
git clone https://github.com/cao-awa/Conium.git
cd Conium
```

### Install Dependencies

The project is fully pre-configured with Gradle. No manual dependency installation is needed beyond prerequisites.

- Run `./gradlew wrapper` (if Gradle wrapper is missing).
- In your IDE, import as a Gradle project and refresh/reload dependencies.

Key dependencies (auto-resolved):
- Fabric Loom 1.13-SNAPSHOT.
- Fabric Language Kotlin 1.13.5+kotlin.2.2.10.
- Fabric API (latest compatible).

### Environment Setup

No `.env` file or environment variables are required. The project uses standard Gradle configurations in `build.gradle` and `gradle.properties`.

## Running the Application

Conium is a Minecraft mod. Build the JAR and install in a Fabric Loader instance.

### Development Mode

1. Build with `./gradlew remapJar`.
2. For IDE development:
   - Use Fabric Loom tasks: `./gradlew runClient` (client) or `./gradlew runServer` (server, scripting only).
3. For out of IDE development:
   - Place `build/libs/conium-*.jar` in `<minecraft-instance>/mods/` for IDE-run instances.

**Warning**: Full features are client-side; servers require datapack/script sync (incomplete).

### Production Mode

1. Build the JAR: `./gradlew remapJar`.
2. Copy `build/libs/conium-*.jar` to `<minecraft-instance>/mods/`.
3. Ensure datapacks/scripts are in `datapacks/` in world folders.
4. Launch Minecraft (Fabric Loader 1.13.5+, Minecraft 1.21.11+).

## Troubleshooting

| Issue                                      | Solution                                                                 |
|--------------------------------------------|--------------------------------------------------------------------------|
| `Kotlin version mismatch`                  | Use **exactly** Kotlin 2.2.10 via Fabric Language Kotlin 1.13.5+kotlin.2.2.10. |
| `Java version too low`                     | Install Java 21+ and set `JAVA_HOME`. Verify with `java -version`.       |
| `Gradle build fails on remapJar`           | Reload Gradle in IDE; ensure Fabric Loom 1.13-SNAPSHOT. Run `./gradlew clean`. |
| `NeoForge incompatibility`                 | Use Fabric Loader only; NeoForge conflicts with Kotlin native (unresolved). |
| `Scripts/datapacks not loading`            | Client-side only for full features. Verify Minecraft >=1.21.11, Fabric API installed. 
| `Bedrock APIs unavailable on server`       | Expected; server supports Conium scripting only.                         |

For further help, join the [Discord server](https://discord.com/invite/BUY2xQr37N) or file an issue tagged "question".

## IDE Setup

| IDE/Editor     | Recommended Plugins/Extensions                                                                 |
|----------------|------------------------------------------------------------------------------------------------|
| IntelliJ IDEA  | - Kotlin (bundled).<br>- Gradle (bundled).<br>- Fabric Loom (via Gradle plugin).<br>- Minecraft Development plugin (optional). |
| Android Studio | Same as IntelliJ (Kotlin/Gradle bundled).                                                      |
| VS Code        | - Kotlin Language (fwcd).<br>- Gradle for Java.<br>- Minecraft extension pack (optional).     |

**Tips**:
- Enable Gradle auto-import/reload.
- Generate sources: `./gradlew genSources`.
- Discuss contributions on Discord before PRs.
