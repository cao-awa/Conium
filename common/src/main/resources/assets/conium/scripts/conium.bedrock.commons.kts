import com.github.cao.awa.conium.bedrock.system.AbstractBedrockSystem

import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap

import com.github.cao.awa.conium.bedrock.world.AbstractBedrockWorld
import com.github.cao.awa.conium.bedrock.world.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.world.player.delegate.BedrockPlayerDelegate
import com.github.cao.awa.conium.bedrock.world.player.screen.BedrockOnScreenDisplay

import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack

import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.item.use.BedrockItemUseOnBeforeEvent
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext

import com.github.cao.awa.catheter.receptacle.Receptacle
import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.catheter.receptacle.LongReceptacle
import com.github.cao.awa.conium.bedrock.system.BedrockSystem

import com.github.cao.awa.conium.script.javascript.std.collection.set.Set

ScriptExport(
    "ConiumBedrockCommons",
    { handler ->
        handler("world", AbstractBedrockWorld::class, BedrockEventContext::accessWorld)
    }
)
