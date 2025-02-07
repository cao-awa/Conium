# References
Conium has supported these alias reference of minecraft, named by [Yarn mappings](https://github.com/FabricMC/yarn):

+ net.minecraft
  + item 
    + [Items](https://mappings.dev/1.21.4/net/minecraft/world/item/Items.html) (Completed)
  + entity
    + [Entity](https://mappings.dev/1.21.4/net/minecraft/world/entity/Entity.html)
      + val world: World
      + val server: World
      + val isAlive: Boolean
    + player
      + [ServerPlayerEntity](https://mappings.dev/1.21.4/net/minecraft/server/level/ServerPlayer.html)
        + val serverWorld: ServerWorld 
        + val commandSource: ServerCommandSource
        + val commandOutput: CommandOutput
        + val advancementTracker: PlayerAdvancementTracker
        + val cameraEntity: Entity
        + val chunkFilter: ChunkFilter
        + val clientChatVisibility: ChatVisibility
        + val clientOptions: SyncedClientOptions