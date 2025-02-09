# References
Conium has supported these alias reference of minecraft, named by [Yarn mappings](https://github.com/FabricMC/yarn):

+ net.minecraft
  + item 
    + [Items](https://mappings.dev/1.21.4/net/minecraft/world/item/Items.html) (Completed)
    + [Item](https://mappings.dev/1.21.4/net/minecraft/world/item/Item.html)
      + val ``breakSound``
      + val ``components``
      + val ``defaultStack``
      + val ``maxCount``
      + val ``recipeRemainder``
      + val ``translationKey``
      + val ``name``
  + block
    + [AbstractBlock](https://mappings.dev/1.21.4/net/minecraft/world/level/block/state/BlockBehaviour.html)
      + val ``defaultMapColor``
      + val ``hardness``
      + val ``lootTableKey``
      + val ``translationKey``
      + val ``settings``
    + [Block](https://mappings.dev/1.21.4/net/minecraft/world/level/block/Block.html)
      + val ``blastResistance``
      + val ``defaultState``
      + val ``name``
      + val ``slipperiness``
      + val ``velocityMultiplier``
      + val ``stateManager``
      + val ``jumpVelocityMultiplier``
  + entity
    + [Entity](https://mappings.dev/1.21.4/net/minecraft/world/entity/Entity.html)
      + val ``chunkPos``
      + val ``maxAir``
      + val ``movement``
      + val ``facing``
      + val ``commandTags``
      + val ``blockStateAtPos``
      + val ``damageSources``
      + val ``isAlive``
      + val ``blockX``
      + val ``air``
      + val ``vehicle``
      + val ``blockZ``
      + val ``fireTicks``
      + val ``firstPassenger``
      + val ``bodyYaw``
      + val ``finalGravity``
      + val ``attachments``
      + val ``eyeY``
      + val ``blockPos``
      + val ``server``
      + val ``velocity``
      + val ``controllingPassenger``
      + val ``minFreezeDamageTicks``
      + val ``boundingBox``
      + val ``blockY``
      + val ``controllingVehicle``
      + val ``freezingScale``
      + val ``isSpectator``
      + val ``world``
      + val ``styledDisplayName``
      + val ``isOnFire``
      + val ``frozenTicks``
      + val ``id``
      + val ``eyePos``
      + val ``entityWorld``
      + val ``displayName``
      + val ``defaultPortalCooldown``
      + val ``customName``
    + [LivingEntity](https://mappings.dev/1.21.4/net/minecraft/world/entity/LivingEntity.html)
      + val ``armorVisibility``
      + val ``allArmorItems``
      + val ``activeItem``
      + val ``offHandStack``
      + val ``movementSpeed``
      + val ``mainHandStack``
      + val ``despawnCounter``
      + val ``damageTracker``
      + val ``climbingPos``
      + val ``damageTiltYaw``
      + val ``brain``
      + val ``blockingItem``
      + val ``attacking``
      + val ``attacker``
      + val ``attributes``
      + val ``armor``
      + val ``activeStatusEffects``
    + player
      + [PlayerEntity](https://mappings.dev/1.21.4/net/minecraft/world/entity/player/Player.html)
        + val ``armorItems``
        + val ``activeHand``
        + val ``absorptionAmount``
        + val ``damageTiltYaw``
        + val ``styledDisplayName``
        + val ``mainArm``
        + val ``abilities``
        + val ``permissionLevel``
        + val ``enderChestInventory``
        + val ``equippedItems``
        + val ``isCreative``
        + val ``isGliding``
        + val ``glidingTicks``
        + val ``entityInteractionRange``
        + val ``displayName``
        + val ``enchantingTableSeed``
        + val ``defaultPortalCooldown``
        + val ``attackCooldownProgressPerTick``
        + val ``blockInteractionRange``
      + [ServerPlayerEntity](https://mappings.dev/1.21.4/net/minecraft/server/level/ServerPlayer.html)
        + val ``spawnPointDimension``
        + val ``chunkFilter``
        + val ``cameraEntity``
        + val ``session``
        + val ``isDisconnected``
        + val ``viewDistance``
        + val ``clientOptions``
        + val ``startRaidPos``