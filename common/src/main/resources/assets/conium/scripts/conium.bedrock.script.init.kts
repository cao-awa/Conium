import com.github.cao.awa.conium.bedrock.index.*
import com.github.cao.awa.conium.script.index.common.*
import com.github.cao.awa.conium.mapping.yarn.*
import com.github.cao.awa.conium.mapping.yarn.reference.*

request(
    SERVER_TICK
) { server ->
    BedrockEventContext.system.tick(server)

    true
}
