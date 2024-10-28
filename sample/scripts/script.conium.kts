request(
    ITEM_USE_ON_BLOCK,
    SERVER_WORLD
).trigger { _, world ->
    println(world)

    true
}
