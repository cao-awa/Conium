package com.github.cao.awa.conium

import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.system.AbstractBedrockSystem
import com.github.cao.awa.conium.bedrock.world.AbstractBedrockWorld
import com.github.cao.awa.conium.script.ScriptExport
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor

object Main {
    val world get() = BedrockEventContext.accessWorld(this)
    val system get() = BedrockEventContext.accessSystem()

    @JvmStatic
    fun main(args: Array<String>) {
        BedrockEventContext.post(this) {
            // Typescript import: [world, system] from "@minecraft/server"
            fun countdown(){
                val players=world.getPlayers();
                players[0].onScreenDisplay.setTitle("Get ready!",
                    BedrockScriptAnonymousObjectMap().add("stayDuration",220).add("subtitle","10").add("fadeOutDuration",4).add("fadeInDuration",2),);
                var countdown=10;
                val intervalId=IntegerReceptacle(0);
                intervalId.set(system.runInterval({
                    countdown--;
                    players[0].onScreenDisplay.updateSubtitle(countdown.toString(),);
                    if(countdown==0){
                        system.clearRun(intervalId,);
                    }
                },20,));
            }

            world.beforeEvents.itemUseOn.subscribe({event->
                if(event.itemStack.typeId=="minecraft:stick"){
                    countdown();
                }
            },);
        }
        BedrockEventContext.clearPost()

        val exported = CollectionFactor.hashMap<String, ScriptExport>()

        val ex = ScriptExport(
            "ConiumBedrockCommons",
            {
                it("world", AbstractBedrockWorld::class, { _ -> this.world })
                it("system", AbstractBedrockSystem::class, { this.system })
            }
        )

        exported[ex.name] = ex

        println(ScriptExport.import(exported, "// IMPORT: ConiumBedrockCommons"))
    }
}