> 1.0.0-alpha14

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

# Performance improves
Let events won't be triggered when no listeners requested the event.

# Scripting
Use 'Cancelable' and 'NoCancelable' to prevent calling the request methods with the suffix 'Nr'.

# Molang
Basic molang parsing and translating.

# Others
Shorter event metadata impl.
Renamed conium bedrock 'Template's to "Component"s.
