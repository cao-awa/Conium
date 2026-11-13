import { world, system } from "@minecraft/server";

const context = world.beforeEvents.itemUseOn.subscribe((event: ItemUseOnBeforeEvent) => {
    if (event.itemStack.typeId === "minecraft:stick") {
        event.source.runCommand("/say Hello Conium!");
    }
});

world.beforeEvents.itemUseOn.subscribe((event: ItemUseOnBeforeEvent) => {
    if (event.itemStack.typeId === "minecraft:diamond") {
        event.source.runCommand("/say Bye Bedrock!");

        world.beforeEvents.itemUseOn.unsubscribe(context);

        event.cancel = true;
    }
});


