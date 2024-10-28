package com.github.cao.awa.conium

import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext

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
    }
}