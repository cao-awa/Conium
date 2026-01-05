# Conium Database Documentation

## Overview

Conium does not utilize a traditional database system (e.g., relational SQL like SQLite/PostgreSQL/MySQL, or NoSQL like MongoDB). 

The project is a datapack and script framework for Minecraft, relying on Minecraft's native data storage mechanisms:
- **Datapacks**: JSON-based structures for advancements, functions, loot tables, predicates, structures, dimensions, and worldgen.
- **NBT (Named Binary Tag)**: For entity, block, and item data storage within Minecraft worlds.
- **Scripting storage**: In-memory or file-based (e.g., Kotlin/TypeScript/MoLang scripts), with no persistent relational or document database observed.

Data persistence is handled through Minecraft's world save format (region files, chunk data) and datapack files. No schema, tables/collections, migrations, or seeding mechanisms exist in the codebase.

This document is provided for completeness, but there is no database to document. For data-driven features, refer to [Data driven](./document/data-driven/README.md). For scripting, see [Conium scripting](./document/script/kotlin/README.md).