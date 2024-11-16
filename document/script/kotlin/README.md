# Kotlin script
Conium supported to run kotlin script in game running, called to 'Conium script'.

Directly write and put the '.kts' file to ```/saves/<world>/datapacks/<pack-name>/data/script```.

## Events
Conium script can handles game event used to complete what you need.

Use ```request``` to subscribe an event, first parameter is the type of event, for all events, see [Event types](/document/script/kotlin/event/README.md#event-types). 

Then other parameters is the context args, for all args type, see [Context arg types](/document/script/kotlin/event/README.md#context-args).

### Arising
``` kts
// Shorter schema.
request(
    SERVER_TICK,
    SERVER
) { _, server ->
    // Do something here.
    
    // Here must return a boolean.
    // True means this event is succeed, false then means failures.  
    true
}

// Full schema.
// The 'arise' call be shorter as missing, but 'presage' cannot.
request(
    SERVER_TICK,
    SERVER
).arise { _, server ->
    // Do something here.
    
    // Here must return a boolean.
    // True means this event is succeed, false then means failures.  
    true
}
```

### Presaging

``` kts
request(
    SERVER_TICK,
    SERVER
) { _, server ->
    // Do something here.  
    true
}.presage {
    // Here must return a boolean.
    // The presaging is called before the event really happening,
    // the whole event will be canceled when the result is false.
    // (whole mean the event after events, like 'PLACED_BLOCK' is the after event of 'PLACE_BLOCK')
    true
}
```

