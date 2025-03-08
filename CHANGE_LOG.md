> 1.0.0-alpha10

# Conium event
1. Renamed events, remove suffixes 'event' in the events list.
2. Move more event trigger to intermediary.
3. Add 'attachPreparation' in event context, used to pre-vary raw inputted args.
4. Add dynamic register APIs for item, block and entity.

## New event support
Add events support:
1. ITEM_USE
2. ITEM_USED

# Bedrock event
1. Add 'dimension' field to bedrock entity. (``Entity#dimension``)
2. Use 'runCommand' to execute 'runCommandAsync' method. (``Entity.runCommandAsync``)
3. Let bedrock entity can use raycast to get a block hit. (``Entity.getBlockFromViewDirection``)
4. Make bedrock world(dimension) able to create the explosions. (``Dimension.createExplosion``)
5. Renamed 'BedrockWorld' to correctly name 'BedrockDimension' to prevent ambiguity. 

## New event support
Add event support:
1. itemUseBeforeEvent
2. itemUseAfterEvent

# Configuration
Add config file ``config/conium.json`` used to toggle conium features, currently toggleable: ``enable_debug`` and ``enable_bedrock_script``.