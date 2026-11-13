import com.github.cao.awa.conium.bedrock.impl.event.context.BedrockEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.request
import com.github.cao.awa.conium.event.type.ConiumEventType.Companion.SERVER_TICK
import com.github.cao.awa.conium.script.field.ScriptExport

request(
    SERVER_TICK
) { server ->
    BedrockEventContext.system.tick(server)

    true
}

ScriptExport("ConiumBedrockScriptInit")
