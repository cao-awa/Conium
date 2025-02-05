import com.github.cao.awa.conium.bedrock.index.*
import com.github.cao.awa.conium.script.index.*

request(
    SERVER_TICK
) { server ->
    BedrockEventContext.system.tick(server)

    true
}

// IMPORT: ConiumBedrockCommons
