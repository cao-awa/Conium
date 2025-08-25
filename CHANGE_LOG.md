> 1.0.0-alpha13

# Events
Add event 'SERVER_CONFIGURED_CONNECTION'.
Add event 'CRAFTING_TABLE_CRAFTING'.
Add event 'CRAFTING_TABLE_CRAFTED'.
Clear event subscribers clearly.

# DSL
Make (M) to M.() .
Change 'break block' cascaded event from 'breaking block' to 'broken block'.

# Templates
Add new template "entity_placer" and bedrock component "minecraft:entity_placer".

# Bug fixes
Fix bug of null pointer exception when requesting server configured connection event.
Fix "minecraft:glint" wrongly be "glint" in template name.

# Others
Shorter event metadata impl.
Renamed conium bedrock templates to "Component"s.