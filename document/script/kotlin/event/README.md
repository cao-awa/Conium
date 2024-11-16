# Events in kotlin script

## Event types

|               Key |                Notes                | Environment | Cancelable |
|------------------:|:-----------------------------------:|------------:|-----------:|
|       SERVER_TICK |    Trigger in every server tick     |      SERVER |      false |
| ITEM_USE_ON_BLOCK | Trigger when an item use on a block |         ALL |       true |
|    BREAKING_BLOCK |     Trigger when breaking block     |         ALL |       true |
|       BREAK_BLOCK |     Trigger when broking block      |      SERVER |       true |
|      BROKEN_BLOCK |      Trigger when broken block      |         ALL |      false |
|       PLACE_BLOCK |     Trigger when placing block      |         ALL |       true |
|      PLACED_BLOCK |      Trigger when block placed      |         ALL |      false |
|         USE_BLOCK |      Trigger when using block       |         ALL |       true |
|        USED_BLOCK |       Trigger when block used       |         ALL |      false |
|     ENTITY_DAMAGE |    Trigger when entity damaging     |         ALL |       true |
|    ENTITY_DAMAGED |     Trigger when entity damaged     |         ALL |      false |
|        ENTITY_DIE |      Trigger when entity dying      |         ALL |       true |
|       ENTITY_DIED |      Trigger when entity died       |         ALL |      false |

## Context args

Context args is the parameters starting from the second parameter in ```request```.

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

If you are reading the source code, maybe you take seen some args doesn't pushed to ```ConiumEventContext```,\
but the required args still able to get in scripts.

This is the ```DynamicArgs``` transform(or adapter) mechanism, dynamic args use other presenting args try to found required arg.

For details, see [ConiumEventArgTypes](/src/main/java/com/github/cao/awa/conium/event/type/ConiumEventArgTypes.kt) and [DynamicArgsBuilder#transform](/src/main/java/com/github/cao/awa/conium/parameter/DynamicArgsBuilder.kt).

If required arg is still unable to found when the dynamic args for-each to all other args and runs all transform presets,\
then this ```request``` of this event will not be arising, because the parameters of ```arising``` and ```presaging``` don't receive null value.

Avoid the trouble of guessing yourself, all args possible to uses for every event is here, \
if you are finding not rarely used parameters, then you need read the ```ConiumEventArgTypes```.

### SERVER_TICK

|    Key | Transform from |
|-------:|:--------------:|
| SERVER |       *        |

### ITEM_USE_ON_BLOCK

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