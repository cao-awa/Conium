import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgType
import com.github.cao.awa.conium.event.type.ConiumEventType
import net.minecraft.server.world.ServerWorld

val context = ConiumEventContextBuilder.request(
    ConiumEventType.ITEM_USE_ON_BLOCK,
    ConiumEventArgType.SERVER_WORLD
).trigger { identity, world ->
    println(world)

    true
}

context