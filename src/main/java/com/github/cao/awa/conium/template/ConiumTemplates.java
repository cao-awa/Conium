package com.github.cao.awa.conium.template;

import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate;
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate;

public class ConiumTemplates {
    public static final String SPAWN_EGG = "spawn_egg";
    public static final String FOOD = "food";

    public static void init() {
        ConiumTemplate.register(SPAWN_EGG, ConiumSpawnEggTemplate::create);
        ConiumTemplate.register(FOOD, ConiumFoodTemplate::create);
    }
}
