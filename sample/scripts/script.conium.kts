request(
    ITEM_USE_ON_BLOCK,
    SERVER_WORLD
).trigger { identity, world ->
    println(world)

    true
}
