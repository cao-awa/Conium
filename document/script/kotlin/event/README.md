# Events in kotlin script

## Event types

|               Key |                Notes                |
|------------------:|:-----------------------------------:|
|       SERVER_TICK |    Trigger in every server tick     |
| ITEM_USE_ON_BLOCK | Trigger when an item use on a block |
|       BREAK_BLOCK |     Trigger when breaking block     |
|       PLACE_BLOCK |     Trigger when placing block      |
|      PLACED_BLOCK |      Trigger when block placed      |
|         USE_BLOCK |      Trigger when using block       |
|        USED_BLOCK |       Trigger when block used       |

## Context args

Context args is the parameters starting from the second parameter in ```request```.

For example:

``` kts
request(
    // This is the event type.
    PLACE_BLOCK,
    // This is the conext args.
    SERVER_WORLD,
    // This also.
    BLOCK_POS,
    // This also.
    ITEM_STACK
) { _, world, pos, stack ->
    // When you defines how many context args in 'request',
    // then you must defines they in arising context as consistent order and quantity.
    true
}

request(
    // This is the event type.
    PLACE_BLOCK,
    // This is the conext args.
    SERVER_WORLD,
    // This also.
    ITEM_STACK
) { _, world, stack ->
    // Here missing 'BLOCK_POS', so arising context also must missing it.
    // Arising context should match to 'request' required args.
    true
}
```

## Dynamic args

If you are reading the source code, maybe you take seen some args doesn't pushed to ```ConiumEventContext```,\
but the required args still able to get in scripts.

This is the ```DynamicArgs``` transform(or adapter) mechanism, dynamic args use other presenting args try to found required arg.

For details, see [ConiumEventArgTypes](/src/main/java/com/github/cao/awa/conium/event/type/ConiumEventArgTypes.kt) and [DynamicArgsBuilder#transform](/src/main/java/com/github/cao/awa/conium/parameter/DynamicArgsBuilder.kt).

If required arg is still unable to found when the dynamic args for-each to all other args and runs all transform presets,\
then this ```request``` of this event will not be arising, because the parameters of arising and presaging don't receive null value.

Avoid the trouble of guessing yourself, all args possible to uses for every event is here, \
if you are finding not rarely used parameters, then you need read the ```ConiumEventArgTypes```.

### SERVER_TICK

SERVER

### ITEM_USE_ON_BLOCK

### BREAK_BLOCK

### PLACE_BLOCK

```ITEM_PLACEMENT_CONTEXT``` ()

```WORLD``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```SERVER_WORLD``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```CLIENT_WORLD``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```ITEM_STACK``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```PLAYER``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```SERVER_PLAYER``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```CLIENT_PLAYER``` (transform from ```ITEM_PLACEMENT_CONTEXT```) \
```BLOCK_POS``` (transform from ```ITEM_PLACEMENT_CONTEXT```)

### PLACED_BLOCK

### USE_BLOCK

### USED_BLOCK
