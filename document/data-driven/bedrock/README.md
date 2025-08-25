## Items

|            Bedrock schema impls             |             Conium schema key |                              Notes                               |         Value type          |
|:-------------------------------------------:|------------------------------:|:----------------------------------------------------------------:|:---------------------------:|
|              minecraft:damage               |    attack_damage (```tool```) |              Setting tool damage amount to entities              |            float            |
|            minecraft:durability             |       durability (```tool```) |                     Setting tool durability                      |             int             |
| damage_chance (```minecraft:durability```)  |    damage_chance (```tool```) |         Setting the probability of durability consuming          |            float            |
|      minecraft:can_destroy_in_creative      |       can_destroy_in_creative | Setting block breakable in creative mode when holding this item  |           boolean           |
|          minecraft:max_stack_size           |                     max_count |           Setting the max item stack count of the item           |             int             |
|               minecraft:food                |                          food |                    Setting the item be a food                    |         Food object         |
|    can_always_eat (```minecraft:food```)    |  can_always_eat  (```food```) |          Setting the food can always eats (no cooldown)          |           boolean           |
|      nutrition (```minecraft:food```)       |      nutrition   (```food```) |                Setting the nutrition of the food                 |             int             |
| saturation_modifier (```minecraft:food```)  |     saturation   (```food```) |                Setting the saturation of the food                |            float            |
|  using_converts_to (```minecraft:food```)   | convert_to (```consumable```) |      Setting the item will convert to other item when used       | string or Item stack object |
|              minecraft:rarity               |                        rarity |                  Setting the rarity of the item                  |           string            |
|           minecraft:use_animation           |                    use_action |     Setting the using action of the item (client animation)      |           string            |
|               minecraft:fuel                |                          fuel | Setting the item can be put into furnaces and provides fuel time |     int or Fuel object      |
|               minecraft:glint               |                         glint |       Setting the item will glint showing (client display)       |           boolean           |
|    protection (```minecraft:wearable```)    |         defense (```armor```) |    Setting the armor providing how much protection (defense)     |            float            |
|       slot (```minecraft:wearable```)       |            slot (```armor```) |            Setting the armor can wearing to what slot            |           string            |
|           minecraft:entity_placer           |                 entity_placer |        Setting the item to place the entity in the world         |           object            |
|   entity (```minecraft:entity_placer```)    |                        entity |                     Setting the entity type                      |           string            |
| dispense_on (```minecraft:entity_placer```) |      allowed_dispenser_blocks |    Setting the blocks that can place the entity by dispenser     |         string list         |
|   used_on (```minecraft:entity_placer```)   |                allowed_blocks |  Setting the blocks that can place the entity by placer entity   |         string list         |

## Blocks

|       Bedrock schema impls       | Conium schema key |                               Notes                                |    Value type    |
|:--------------------------------:|------------------:|:------------------------------------------------------------------:|:----------------:|
| minecraft:destructible_by_mining |          hardness |                 Setting the hardness of the block                  |      float       |
|       minecraft:map_color        |         map_color |         Setting the map color of the block (Not completed)         | Map color object |
|     minecraft:light_emission     |         luminance | Setting the lighting of the block (like torch or burning furnaces) |       int        |
|     minecraft:collision_box      |         collision |               Setting the collision box of the block               | Collision object |

## Entities
Entities supported to defines 'component_groups' in bedrock schema, but unable to switching now.

|  Bedrock schema impls   | Conium schema key |                                    Notes                                    |         Value type         |
|:-----------------------:|------------------:|:---------------------------------------------------------------------------:|:--------------------------:|
| minecraft:collision_box |         dimension |                   Setting the collision box of the entity                   |      Dimension object      |
|   minecraft:pushable    |          pushable | Setting the entity is can be pushing by other entities or pistons or fluids | boolean or Pushable object |
