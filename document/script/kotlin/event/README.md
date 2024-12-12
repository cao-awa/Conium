# Events in kotlin script

## Event types

|                   Key |                       Notes                       | Environment | Cancelable |                                                                                                                                                  Cascade events | Input instance  |
|----------------------:|:-------------------------------------------------:|------------:|-----------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|           SERVER_TICK |           Trigger in every server tick            |      SERVER |      false |                                                                                                                                                 Too many events | MinecraftServer |
|      SERVER_TICK_TAIL |      Trigger in every server tick completed       |      SERVER |      false |                                                                                                                                                               * | MinecraftServer |
|     ITEM_USE_ON_BLOCK |        Trigger when an item use on a block        |         ALL |       true |                                                                                                                                          ``ITEM_USED_ON_BLOCK`` |      Item       |
|    ITEM_USED_ON_BLOCK |       Trigger when an item used on a block        |         ALL |       true |                                                                                                                                                               * |      Item       |
|    ITEM_USE_ON_ENTITY |       Trigger when an item use on an entity       |         ALL |       true |                                                                                                                                         ``ITEM_USED_ON_ENTITY`` |      Item       |
|   ITEM_USED_ON_ENTITY |      Trigger when an item used on an entity       |         ALL |       true |                                                                                                                                                               * |      Item       |
|       ITEM_USAGE_TICK |        Trigger when an item usage ticking         |         ALL |       true |                                                                                                                                           ``ITEM_USAGE_TICKED`` |      Item       |
|     ITEM_USAGE_TICKED |         Trigger when an item usage ticked         |         ALL |       true |                                                                                                                                                               * |      Item       |
|   ITEM_INVENTORY_TICK |     Trigger when an item ticking in inventory     |         ALL |       true |                                                                                                                                       ``ITEM_INVENTORY_TICKED`` |      Item       |
| ITEM_INVENTORY_TICKED |     Trigger when an item ticked in inventory      |         ALL |       true |                                                                                                                                                               * |      Item       |
|      ITEM_STACK_CLICK | Trigger when an item stack clicking in inventory  |         ALL |       true |                                                                                                                                          ``ITEM_STACK_CLICKED`` |      Item       |
|    ITEM_STACK_CLICKED |  Trigger when an item stack clicked in inventory  |         ALL |       true |                                                                                                                                                               * |      Item       |
|        BREAKING_BLOCK |            Trigger when breaking block            |         ALL |       true |                                                                                                                               ``BREAK_BLOCK``  ``BROKEN_BLOCK`` |      Block      |
|           BREAK_BLOCK |            Trigger when broking block             |      SERVER |       true |                                                                                                                                                ``BROKEN_BLOCK`` |      Block      |
|          BROKEN_BLOCK |             Trigger when broken block             |         ALL |      false |                                                                                                                                                               * |      Block      |
|           PLACE_BLOCK |            Trigger when placing block             |         ALL |       true |                                                                                                                                                ``PLACED_BLOCK`` |      Block      |
|          PLACED_BLOCK |             Trigger when block placed             |         ALL |      false |                                                                                                                                                               * |      Block      |
|             USE_BLOCK |             Trigger when using block              |         ALL |       true |                                                                                                                                                  ``USED_BLOCK`` |      Block      |
|            USED_BLOCK |              Trigger when block used              |         ALL |      false |                                                                                                                                                               * |      Block      |
|         ENTITY_DAMAGE |           Trigger when entity damaging            |         ALL |       true |                                                                                                                                              ``ENTITY_DAMAGED`` |  EntityType<*>  |
|        ENTITY_DAMAGED |            Trigger when entity damaged            |         ALL |      false |                                                                                                                                                               * |  EntityType<*>  |
|            ENTITY_DIE |             Trigger when entity dying             |         ALL |       true |                                                                                                                                                 ``ENTITY_DEAD`` |  EntityType<*>  |
|           ENTITY_DIED |             Trigger when entity died              |         ALL |      false |                                                                                                                                                               * |  EntityType<*>  |
|   BLOCK_SCHEDULE_TICK |        Trigger when schedule ticking block        |      SERVER |       true |                                                                                                                                       ``BLOCK_SCHEDULE_TICKED`` |      Block      |
| BLOCK_SCHEDULE_TICKED |             Trigger when block ticked             |      SERVER |      false |                                                                                                                                                               * |      Block      |
|   FLUID_SCHEDULE_TICK |        Trigger when schedule ticking fluid        |      SERVER |       true |                                                                                                                                       ``FLUID_SCHEDULE_TICKED`` |      Fluid      |
|   SHULKER_BOX_OPENING |  Trigger when player trying to open shulker box   |         ALL |       true |                                                                                         ``SHULKER_BOX_OPENED``  ``SHULKER_BOX_CLOSING``  ``SHULKER_BOX_CLOSED`` |      Block      |
|    SHULKER_BOX_OPENED |          Trigger when shulker box opened          |         ALL |      false |                                                                                                                                                               * |      Block      |
|   SHULKER_BOX_CLOSING |  Trigger when player trying to close shulker box  |         ALL |       true |                                                                                                                                         ``SHULKER_BOX_CLOSING`` |      Block      |
|    SHULKER_BOX_CLOSED |          Trigger when shulker box closed          |         ALL |      false |                                                                                                                                                               * |      Block      |
|         CHEST_OPENING |     Trigger when player trying to open chest      |         ALL |       true | ``CHEST_OPENED``  ``CHEST_CLOSING``  ``CHEST_CLOSED``  ``TRAPPED_CHEST_OPENING``  ``TRAPPED_CHEST_OPENED``  ``TRAPPED_CHEST_CLOSING``  ``TRAPPED_CHEST_CLOSED`` |      Block      |
|          CHEST_OPENED |             Trigger when chest opened             |         ALL |      false |                                                                                                                                                               * |      Block      |
|         CHEST_CLOSING |     Trigger when player trying to close chest     |         ALL |       true |                                                                                                                                                ``CHEST_CLOSED`` |      Block      |
|          CHEST_CLOSED |             Trigger when chest closed             |         ALL |      false |                                                                                                                                                               * |      Block      |
| TRAPPED_CHEST_OPENING | Trigger when player trying to open trapped chest  |         ALL |       true |                                                                                   ``TRAPPED_CHEST_OPENED``  ``TRAPPED_CHEST_CLOSING``  ``TRAPPED_CHEST_CLOSED`` |      Block      |
|  TRAPPED_CHEST_OPENED |         Trigger when trapped chest opened         |         ALL |      false |                                                                                                                                                               * |      Block      |
| TRAPPED_CHEST_CLOSING | Trigger when player trying to close trapped chest |         ALL |       true |                                                                                                                                        ``TRAPPED_CHEST_CLOSED`` |      Block      |
|  TRAPPED_CHEST_CLOSED |         Trigger when trapped chest opened         |         ALL |      false |                                                                                                                                                               * |      Block      |

### Cascade events

Cascade events are one or more events that only can happen when the preceding event is successful.

For example, the ``BREAKING_BLOCK`` event occurs when a player starting mining a block, it normally takes time for the player to destroy the block, and only then can ``BREAK_BLOCK`` event occur, followed by the ``BROKEN_BLOCK`` event.\
The ``BREAK_BLOCK`` and ``BROKEN_BLOCK`` events cannot happen if ``BREAKING_BLOCK`` event is canceled or failed.

In summary, cascade events is one or more events that depends on certain preceding events.

Remember, all cascade event of the event cannot happen when you are canceling a event!\
Includes all ``arising`` and ``presaging`` context of cascaded events and ``arising`` context of this event.

## Context args

Context args is the parameters starting from the second parameter in ``request``.

For example:

``` kts
request(
    // This is the event type.
    PLACE_BLOCK,
    // This is the context args.
    SERVER_WORLD,
    // This also.
    BLOCK_POS,
    // This also.
    ITEM_STACK
) { _, world, pos, stack ->
    // When you defines how many context args in 'request',
    // then you must define them in arising context as consistent order and quantity.
    true
}

request(
    // This is the event type.
    PLACE_BLOCK,
    // This is the context args.
    SERVER_WORLD,
    // This also.
    ITEM_STACK
) { _, world, stack ->
    // Here missing 'BLOCK_POS', so arising context also must miss it to match requiring args.
    // Arising context should match the required arguments for 'request'.
    true
}
```

## Dynamic args

If you are reading the source code, maybe you take seen some args doesn't pushed to ``ConiumEventContext``,\
but the required args still able to get in scripts.

This is the ``DynamicArgs`` transform(or adapter) mechanism, dynamic args use other presenting args try to found required arg.

For details, see [ConiumEventArgTypes](/src/main/java/com/github/cao/awa/conium/event/type/ConiumEventArgTypes.kt) and [DynamicArgsBuilder#transform](/src/main/java/com/github/cao/awa/conium/parameter/DynamicArgsBuilder.kt).

If required arg is still unable to found when the dynamic args for-each to all other args and runs all transform presets,\
then this ``request`` of this event will not be arising, because the parameters of ``arising`` and ``presaging`` don't receive null value.

Avoid the trouble of guessing yourself, all args possible to uses for every event is here, \
if you are finding not rarely used parameters, then you need read the ``ConiumEventArgTypes``.

### SERVER_TICK

### SERVER_TICK_TAIL

### ITEM_USE_ON_BLOCK

### ITEM_USED_ON_BLOCK

### ITEM_USE_ON_ENTITY

### ITEM_USED_ON_ENTITY

### ITEM_USAGE_TICK

### ITEM_USAGE_TICKED

### BREAKING_BLOCK

### BREAK_BLOCK

### BROKEN_BLOCK

### PLACE_BLOCK

|                    Key |     Transform from     | Environment |
|-----------------------:|:----------------------:|------------:|
| ITEM_PLACEMENT_CONTEXT |           *            |         ALL |
|                  WORLD | ITEM_PLACEMENT_CONTEXT |         ALL |
|           SERVER_WORLD | ITEM_PLACEMENT_CONTEXT |      SERVER |
|           CLIENT_WORLD | ITEM_PLACEMENT_CONTEXT |      CLIENT |
|             ITEM_STACK | ITEM_PLACEMENT_CONTEXT |         ALL |
|                 PLAYER | ITEM_PLACEMENT_CONTEXT |         ALL |
|          SERVER_PLAYER | ITEM_PLACEMENT_CONTEXT |      SERVER |
|          CLIENT_PLAYER | ITEM_PLACEMENT_CONTEXT |      CLIENT |
|              BLOCK_POS | ITEM_PLACEMENT_CONTEXT |         ALL |

### PLACED_BLOCK

### USE_BLOCK

### USED_BLOCK

### ENTITY_DAMAGE

### ENTITY_DAMAGED

### ENTITY_DIE

### ENTITY_DIED

### BLOCK_SCHEDULE_TICK

### BLOCK_SCHEDULE_TICKED

### FLUID_SCHEDULE_TICK

### SHULKER_BOX_OPENING

### SHULKER_BOX_OPENED

### SHULKER_BOX_CLOSING

### SHULKER_BOX_CLOSED

### CHEST_OPENING

### CHEST_OPENED

### CHEST_CLOSING

### CHEST_CLOSED

### TRAPPED_CHEST_OPENING

### TRAPPED_CHEST_OPENED

### TRAPPED_CHEST_CLOSING

### TRAPPED_CHEST_CLOSED
