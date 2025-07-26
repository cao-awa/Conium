# Kotlin script
Conium supported to run the kotlin script in game running, called as 'Conium script.'

Directly write and put the '.kts' file to ```/saves/<world>/datapacks/<pack-name>/data/script```.

## Events
Conium script can handle game events used to complete what you need.

Use ```request``` to subscribe an event, first parameter is the type of event, for all events, see [Event types](/document/script/kotlin/event/README.md#event-types). 

Then other parameters are the context args, for all args type, see [Context arg types](/document/script/kotlin/event/README.md#context-args).

### Arising
``` kts
// Shorter schema.
request(
    PLACED_BLOCK,
    BLOCK_POS
) { _, pos ->
    // Do something here.
    
    // Here must return a boolean.
    // True means this event is succeed, false then means failures.  
    true
}

// Full schema.
// The 'arise' call be shorter as missing in 'request', but 'presage' cannot.
request(
    PLACED_BLOCK,
    BLOCK_POS
).arise { _, pos ->
    // Do something here.
    
    // Here must return a boolean.
    // True means this event is succeed, false then means failures.  
    true
}
```

### Presaging

``` kts
// Shorter schema.
preRequest(
    PLACED_BLOCK,
    BLOCK_POS
) { _, pos ->
    // Here must return a boolean.
    // The presaging is called before the event really happening,
    // the whole event will be canceled when the result is false.
    // (whole mean the event after events, like 'PLACED_BLOCK' is the after event of 'PLACE_BLOCK')
    true
}
```

## Reference type
> See main article: [Reference type](/document/script/kotlin/reference/README.md)

Cause by minecraft obfuscation and class name mapping, kotlin scripts cannot import minecraft class like ```import net.minecraft.block.*``` or other, so conium mapping some useful classes to script context.

The kotlin script can use that class with name, don't import any minecraft class manually!  

For example, if you want to use ServerWorld, then use it directly:
```kts
val world: ServerWorld = player.world as ServerWorld
doSomething(world)
// Others...
```

Instead of:
```kts
// This import will not successes!
import net.minecraft.server.world.ServerWorld

val world: ServerWorld = player.world as ServerWorld
doSomething(world)
// Others...
```

## DSL style (tentative)
You can use the DSL style to process the events like:
```kts
onEvent(ITEM_USE_ON_BLOCK) {
    action {
        // Main event handler logic.
        println(this.itemUsageContext)
        println(this.world)

        // Test exception happening 
        throw NullPointerException()
    }

    catching {
        // Trigger when 'action' happening exceptions or errors.
        this.exception.printStackTrace()
    }

    finalize {
        // Trigger when all handler done (include 'catching').
        println("DSL style event handle compled! ")
    }
}
```
and cascaded event：
```kts
onEvent(ITEM_USE_ON_BLOCK) {
    action {
        // Main event handler logic.
        println(this.itemUsageContext)
        println(this.world)

        // Test exception happening 
        throw NullPointerException()
    }

    catching {
        // Trigger when 'action' happening exceptions or errors.
        this.exception.printStackTrace()
    }

    finalize {
        // Trigger when all handler done (include 'catching').
        println("DSL style event handle compled! ")
    }
}.then {
    // Same as the above content.
    action {
        true
    }
}
```

## Registry attach
> Not really available now, this is a planning feature 

Maybe you can use 'register' method in the kotlin script to register your items, blocks, entities and other registerable elements.

But you never want to register or deregister in the game-running without conium reload, it will cause game crash or something problems come up.
