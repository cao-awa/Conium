BedrockEventContext.newSystem()

request(
    SERVER_TICK,
    SERVER
).arise { _, server ->
    BedrockEventContext.system.tick(server)

    true
}

// IMPORT: ConiumBedrockCommons
