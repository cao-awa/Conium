export(
    "awa:test_glint",
    false,
    ITEM_USED_ON_BLOCK,
    ITEM_USAGE_CONTEXT
) { item, context ->
    return@export (context.player?.isCreative ?: false)
}