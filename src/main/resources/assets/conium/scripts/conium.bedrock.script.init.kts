BedrockEventContext.newSystem()

ConiumEventContextBuilder.request(
    ConiumEventType.SERVER_TICK,
    ConiumEventArgTypes.SERVER
).arise { _, server ->
    BedrockEventContext.system.tick(server)

    true
}

// IMPORT: ConiumBedrockCommons
