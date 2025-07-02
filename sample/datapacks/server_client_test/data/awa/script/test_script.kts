request(
    USE_BLOCK,
    CLIENT_WORLD,
    BLOCK_ENTITY
) { block, world, blockEntity ->
    blockEntity as ConiumBlockEntity

    val currentValue: Int = blockEntity["inc"] as Int

    println("Current inc value is $currentValue")

    blockEntity["inc"] = currentValue + 1

    true
}