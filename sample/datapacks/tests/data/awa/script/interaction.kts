export(
    "awa:test_glint",
    false,
    ITEM_USED_ON_BLOCK,
    ITEM_USAGE_CONTEXT
) { item, context ->
    return@export (context.player?.isCreative ?: false)
}

request(
    USE_BLOCK,
    SERVER_WORLD,
    BLOCK_ENTITY
) { block, world, blockEntity ->
    blockEntity as ConiumBlockEntity

    val currentValue: Int = blockEntity["inc"] as Int

    println("Current inc value is $currentValue")

    blockEntity["inc"] = currentValue + 1

    true
}