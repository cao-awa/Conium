package com.github.cao.awa.conium.item.template;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate;

import java.util.Map;

public class ConiumTemplates {
    public static final String SPAWN_EGG = "spawn_egg";

    public static void init() {
        ConiumTemplate.register(SPAWN_EGG, ConiumSpawnEggTemplate::create);
    }
}
