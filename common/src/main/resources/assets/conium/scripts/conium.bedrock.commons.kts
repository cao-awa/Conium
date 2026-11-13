import com.github.cao.awa.conium.bedrock.index.*
import com.github.cao.awa.conium.script.field.ScriptExport

ScriptExport(
    "ConiumBedrockCommons",
    { handler ->
        handler("world", AbstractBedrockWorld::class) { it: Any ->
            BedrockEventContext.accessWorld(it)
        }
    }
)
