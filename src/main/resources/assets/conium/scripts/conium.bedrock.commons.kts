import com.github.cao.awa.conium.bedrock.system.AbstractBedrockSystem

import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap

import com.github.cao.awa.conium.bedrock.world.AbstractBedrockWorld
import com.github.cao.awa.conium.bedrock.world.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.world.player.delegate.BedrockPlayerDelegate
import com.github.cao.awa.conium.bedrock.world.player.screen.BedrockOnScreenDisplay

import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack

import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.item.use.BedrockItemUseOnEvent
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext

import com.github.cao.awa.catheter.receptacle.Receptacle
import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.catheter.receptacle.LongReceptacle
import com.github.cao.awa.conium.bedrock.system.BedrockSystem

ScriptExport(
    "ConiumBedrockCommons",
    {
        it("world", AbstractBedrockWorld::class) { instance -> BedrockEventContext.accessWorld(instance) }
        it("system", AbstractBedrockSystem::class) { BedrockEventContext.accessSystem() }
    }
)
