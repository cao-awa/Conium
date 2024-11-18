# Data driven

## Datapack structure

+ \<path-to-save\>/datapacks
    + \<pack-name\>
        + data
            + item
                + This directory is items, data type is '.json'
            + block
                + This directory is blocks, data type is '.json'
            + entity
                + This directory is entities, data type is '.json'
            + property
                + item
                    + This directory is item properties injectors, data type is '.json'
                + block
                    + This directory is block properties injectors, data type is '.json', current is no impls.
            + script
                + This directory is scripts, data type is 'kts', '.ts' and '.js'

## Templates or components

For conium templates, see [Conium data driven](/document/data-driven/conium/README.md). 

For bedrock components, see [Bedrock data driven](/document/data-driven/bedrock/README.md).

## Conium schema

```json5
{
  // If using conium schema, 'schema' is required.
  "schema_style": "conium",
  // The identifier must present.
  "identifier": "awa:conium",
  "templates": {
    // The components where here following listed.
  }
}
```

Example:

```json5
{
  "schema_style": "conium",
  "identifier": "awa:conium",
  "templates": {
    "tool": {
      // The damage amount when hitting the entity.
      "attack_damage": 100,
      // The cooldown modifier after once attack.
      "attack_speed": -3.0,
      // The durability amount.
      "durability": 50,
      // Which blocks this tool can mine.
      // This value is a tag key.
      "effective_blocks": "minecraft:mineable/pickaxe",
      // This tool is what material.
      "material": "netherite",
      // This tool is weapon or not.
      // The durability will decrement 2 after this tool damage to entity when it not weapon.
      "is_weapon": false
    },
    // Setting this tool item can destroy blocks in creative mode.
    "can_destroy_in_creative": true,
    // The max item stack size.
    // The size value must in range 1 to 64.
    "max_count": 64
  }
}
```

## bedrock schema

```json5
{
  // If using bedrock schema, 'schema' can be missing.
  "schema_style": "bedrock",
  // Format version is always can be missing, conium is supporting to the schemas by all versions. 
  "format_version": "1.20.10",
  "minecraft:item": {
    // Description and identifier must present.
    "description": {
      "identifier": "awa:bedrock"
    },
    "components": {
      // The components where here following listed.
    }
  }
}
```

Example:

```json5
{
  "minecraft:item": {
    // Description and identifier must present.
    "description": {
      "identifier": "awa:bedrock"
    },
    "components": {
      // The damage amount when hitting the entity.
      "minecraft:damage": 10,
      // The durability amount of tool item.
      // When damage chance is missing, it can be abbreviated as:
      //     "minecraft:durability": <int>
      "minecraft:durability": {
        // The amount.
        "max_durability": 50
      },
      // The max item stack size.
      // The size value must in range 1 to 64.
      // It can be abbreviated as:
      //     "minecraft:max_stack_size": <int>
      "minecraft:max_stack_size": {
        "value": 64
      }
    },
  }
}
```
