import { world, system, DimensionLocation } from "@minecraft/server";

function countdown(targetLocation: DimensionLocation) {
    const players = world.getPlayers();

    players[0].onScreenDisplay.setTitle("Get ready!", {
        stayDuration: 220,
        fadeInDuration: 2,
        fadeOutDuration: 4,
        subtitle: "10",
    });

    let countdown = 10;

    const intervalId = system.runInterval(() => {
        countdown--;
        players[0].onScreenDisplay.updateSubtitle(countdown.toString());

        if (countdown == 0) {
            system.clearRun(intervalId);
        }
    }, 20);
}
