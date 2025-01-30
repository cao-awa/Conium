## Items

|            Conium schema key |         Bedrock schema impls         |                                   Notes                                   |         Value type          |
|-----------------------------:|:------------------------------------:|:-------------------------------------------------------------------------:|:---------------------------:|
|                         tool |                  *                   |                          Make the item be a tool                          |         Tool object         |
|         attack_damage (tool) |           minecraft:damage           |                  Setting tool damage amount to entities                   |            float            |
|          attack_speed (tool) |               No impl                |                 Setting tool attack speed (the cooldown)                  |            float            |
|            durability (tool) |         minecraft:durability         |                          Setting tool durability                          |             int             |
|      effective_blocks (tool) |               No impl                |                Setting what blocks can mined by this tool                 |           string            |
|              material (tool) |               No impl                |                   Setting the base data using material                    |           string            |
|             is_weapon (tool) |               No impl                | Make different durability consume when used to hit entity or break blocks |           boolean           |
|         damage_chance (tool) | damage_chance (minecraft:durability) |              Setting the probability of durability consuming              |    Damage chance object     |
|      can_destroy_in_creative |  minecraft:can_destroy_in_creative   |      Setting block breakable in creative mode when holding this item      |           boolean           |
|                    max_count |       minecraft:max_stack_size       |               Setting the max item stack count of the item                |             int             |
|                         food |            minecraft:food            |                        Setting the item be a food                         |         Food object         |
|       can_always_eat  (food) |   can_always_eat (minecraft:food)    |              Setting the food can always eats (no cooldown)               |           boolean           |
|           nutrition   (food) |      nutrition (minecraft:food)      |                     Setting the nutrition of the food                     |             int             |
|          saturation   (food) | saturation_modifier (minecraft:food) |                    Setting the saturation of the food                     |            float            |
|                   consumable |                  *                   |                 Setting the item has consumable behaviors                 |      Consumable object      |
|      convert_to (consumable) |  using_converts_to (minecraft:food)  |           Setting the item will convert to other item when used           | string or Item stack object |
| apply_effects   (consumable) |               No impl                |          Setting the item will give effects to entity when used           |     Apply effects list      |
|                       rarity |           minecraft:rarity           |                      Setting the rarity of the item                       |           string            |
|                   use_action |       minecraft:use_animation        |          Setting the using action of the item (client animation)          |           string            |
|                         fuel |            minecraft:fuel            |     Setting the item can be put into furnaces and provides fuel time      |     int or Fuel object      |
|                        glint |           minecraft:glint            |           Setting the item will glint showing (client display)            |           boolean           |
|                        armor |                  *                   |          Setting the item be a armor and can be wear to the slot          |        Armor object         |
|              defense (armor) |   protection (minecraft:wearable)    |         Setting the armor providing how much protection (defense)         |           double            |
|                 slot (armor) |      slot (minecraft:wearable)       |                Setting the armor can wearing to what slot                 |           string            |
| knockback_resistance (armor) |               No impl                |        Setting the armor providing how much knock back resistance         |           double            |
|            toughness (armor) |               No impl                |                          Setting armor toughness                          |           double            |
|          enchantable (armor) |               No impl                |                 Setting armor enchantable (Not completed)                 |             int             |
|           force_mining_speed |               No impl                |  Force override the mining speed of item, ignored material mining speed   |            float            |

## Blocks

|           Conium schema key |       Bedrock schema impls       |                                    Notes                                    |        Value type        |
|----------------------------:|:--------------------------------:|:---------------------------------------------------------------------------:|:------------------------:|
|                    hardness | minecraft:destructible_by_mining |                      Setting the hardness of the block                      |          float           |
|                   map_color |       minecraft:map_color        |             Setting the map color of the block (Not completed)              |     Map color object     |
|                   luminance |     minecraft:light_emission     |     Setting the lighting of the block (like torch or burning furnaces)      |           int            |
|                   collision |     minecraft:collision_box      |                   Setting the collision box of the block                    |     Collision object     |
|                 replaceable |             No impl              |   Setting the block can be replaced when placing block like water or fire   |         boolean          |
|           movement_velocity |             No impl              |      Setting the movement velocity when entities walk or jump on block      | Movement velocity object |
|               walk_velocity |             No impl              |          Setting the movement velocity when entities walk on block          |          float           |
|               jump_velocity |             No impl              |          Setting the movement velocity when entities jump on block          |          float           |
|             piston_behavior |             No impl              |          Setting the behavior when piston interacting to the block          |          string          |
|                  instrument |             No impl              |                    Setting the instrument of note block                     |          string          |
|         enable_block_entity |             No impl              |                Setting the block entity register identifier                 |          string          |
|                        data |             No impl              |       Setting the registrable data and default values of block entity       |    Block data object     |
|        block_entity_presets |             No impl              | Setting the block entity behaviors using presets (or called as 'templates') |          string          |
|        emits_redstone_power |             No impl              |           Setting the block emits weak and strong redstone power            |      boolean or int      |
|   emits_weak_redstone_power |             No impl              |                 Setting the block emits weak redstone power                 |           int            |
| emits_strong_redstone_power |             No impl              |                Setting the block emits strong redstone power                |           int            |

## Block entities

## Entities

| Conium schema key |  Bedrock schema impls   |                                    Notes                                    |          Value type           |
|------------------:|:-----------------------:|:---------------------------------------------------------------------------:|:-----------------------------:|
|         dimension | minecraft:collision_box |                   Setting the collision box of the entity                   |       Dimension object        |
|          pushable |   minecraft:pushable    | Setting the entity is can be pushing by other entities or pistons or fluids |  boolean or Pushable object   |
|             model |         No impl         |                     Setting the entity rendering model                      | Entity rendering model object |
