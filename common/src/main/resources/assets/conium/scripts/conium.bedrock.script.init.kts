import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext

request(
    SERVER_TICK
) { server ->
    BedrockEventContext.system.tick(server)

    true
}

// IMPORT: ConiumBedrockCommons
