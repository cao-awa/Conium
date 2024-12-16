BedrockEventContext.newSystem()

request(
    SERVER_TICK
) { server ->
    BedrockEventContext.system.tick(server)

    true
}

// IMPORT: ConiumBedrockCommons
