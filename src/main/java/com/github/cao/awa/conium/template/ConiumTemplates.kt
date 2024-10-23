package com.github.cao.awa.conium.template;

import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate;
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate;
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemDiamondAxeTemplate;
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemGoldenAxeTemplate;
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemIronAxeTemplate;
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemNetheriteAxeTemplate;
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemStoneAxeTemplate;
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemWoodenAxeTemplate;

public class ConiumTemplates {
    public static final String SPAWN_EGG = "spawn_egg";

    public static final String FOOD = "food";

    public static final String WOODEN_PICKAXE = "wooden_pickaxe";
    public static final String STONE_PICKAXE = "stone_pickaxe";
    public static final String IRON_PICKAXE = "iron_pickaxe";
    public static final String GOLDEN_PICKAXE = "golden_pickaxe";
    public static final String DIAMOND_PICKAXE = "diamond_pickaxe";
    public static final String NETHERITE_PICKAXE = "netherite_pickaxe";

    public static void init() {
        // Spawn egg.
        ConiumTemplate.register(SPAWN_EGG, ConiumSpawnEggTemplate::create);

        // Food.
        ConiumTemplate.register(FOOD, ConiumFoodTemplate::create);

        // Pickaxes.
        ConiumTemplate.register(WOODEN_PICKAXE, ConiumItemWoodenAxeTemplate::create);
        ConiumTemplate.register(STONE_PICKAXE, ConiumItemStoneAxeTemplate::create);
        ConiumTemplate.register(IRON_PICKAXE, ConiumItemIronAxeTemplate::create);
        ConiumTemplate.register(GOLDEN_PICKAXE, ConiumItemGoldenAxeTemplate::create);
        ConiumTemplate.register(DIAMOND_PICKAXE, ConiumItemDiamondAxeTemplate::create);
        ConiumTemplate.register(NETHERITE_PICKAXE, ConiumItemNetheriteAxeTemplate::create);
    }
}
