```markdown
# Conium Documentation Index

Welcome to the **Conium** documentation. Conium is a datapack and script framework for modern Minecraft, enabling modding via datapacks and scripts without Java coding. It includes a common library, Fabric and NeoForge mod modules, Molang grammar parser, data-driven features, and Kotlin/Bedrock scripting APIs.

This index provides quick access to all guides, references, and tutorials in the `docs/` folder.

## Quick Links

| Document | Description | When to Read |
|----------|-------------|--------------|
| [Getting Started](./getting-started.md) | Initial setup, prerequisites, and first datapack creation. | Always first for new users. |
| [Installation](./installation.md) | Detailed install instructions for common library, mod modules, and tools. | Before building your first project. |
| [Common Library](./common-library.md) | Core APIs, utilities, and shared functionality across platforms. | When integrating base features. |
| [Fabric Mod Module](./fabric-mod.md) | Fabric-specific integration, events, and datapack loading. | Fabric mod developers. |
| [NeoForge Mod Module](./neoforge-mod.md) | NeoForge-specific integration, events, and datapack loading. | NeoForge mod developers. |
| [Molang Grammar Parser](./molang-parser.md) | Parsing, validation, and usage of Molang expressions. | Working with animations or expressions. |
| [Data-Driven Features](./data-driven-features.md) | Custom behaviors, predicates, advancements, and loot tables. | Building complex datapacks. |
| [Kotlin/Bedrock Scripting APIs](./kotlin-bedrock-apis.md) | Scripting runtime, APIs, and Bedrock-like Kotlin integration. | Script-heavy modding. |
| [API Reference](./api-reference.md) | Full Javadoc-style reference for all public APIs. | During implementation for details. |
| [Troubleshooting](./troubleshooting.md) | Common issues, errors, and debugging tips. | When things go wrong. |

## Reading Order

For new developers:

1. **[Getting Started](./getting-started.md)** – Overview and hello world.
2. **[Installation](./installation.md)** – Set up your environment.
3. **[Common Library](./common-library.md)** – Learn the foundation.
4. Platform-specific: **[Fabric Mod Module](./fabric-mod.md)** or **[NeoForge Mod Module](./neoforge-mod.md)**.
5. **[Data-Driven Features](./data-driven-features.md)** – Core datapack modding.
6. **[Kotlin/Bedrock Scripting APIs](./kotlin-bedrock-apis.md)** – Add scripts.
7. **[API Reference](./api-reference.md)** – Dive deep.

Experienced users: Jump to [Quick Links](#quick-links) or search.

## Contributing to Docs

- Edit `.md` files directly in `docs/`.
- Use standard Markdown (headings, tables, code blocks).
- Preview locally with VS Code, Typora, or `mdbook serve`.
- Reference code with `[source](https://github.com/.../blob/main/path/to/file.kt#L42)`.
- Submit PRs to `main` branch with clear changelogs.
- Keep concise, accurate, and code-backed – no speculation.

See [CONTRIBUTING.md](../CONTRIBUTING.md) in repo root for full guidelines.
```