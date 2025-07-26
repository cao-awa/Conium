package com.github.cao.awa.conium.intermediary.server

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.Companion.fireEvent
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.Companion.fireEventCancelable
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import net.minecraft.util.Unit

/**
 * Conium server event intermediary triggers.
 *
 * @see MinecraftServer
 * @see ConiumEventType.SERVER_TICK
 * @see ConiumEventType.SERVER_RANDOM
 * @see ConiumEventType.SERVER_TICK_TAIL
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumServerEventMixinIntermediary {
    companion object {
        /**
         * Trigger the server tick start event on server ticking.
         *
         * @see MinecraftServer
         * @see ConiumEventType.SERVER_TICK
         *
         * @param server the minecraft server
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireServerTickEvent(server: MinecraftServer) {
            fireEvent(
                ConiumEventType.SERVER_TICK,
                server,
            ) { /* No contexts arg need to add */ }
        }

        /**
         * Trigger the server random event on server ticking.
         *
         * @see MinecraftServer
         * @see ConiumEventType.SERVER_TICK
         * @see ConiumEventType.SERVER_RANDOM
         *
         * @param server the minecraft server
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireServerRandomEvent(server: MinecraftServer) {
            if (!fireEventCancelable(
                    ConiumEventType.RANDOM,
                    Unit.INSTANCE
                ) { /* No contexts arg need to add */ }
            ) {
                fireEvent(
                    ConiumEventType.SERVER_RANDOM,
                    server,
                ) { /* No contexts arg need to add */ }
            }
        }

        /**
         * Trigger the server tick tail event on server tick completed.
         *
         * @see MinecraftServer
         * @see ConiumEventType.SERVER_TICK
         *
         * @param server the minecraft server
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireServerTickTailEvent(server: MinecraftServer) {
            fireEvent(
                ConiumEventType.SERVER_TICK_TAIL,
                server,
            ) { /* No contexts arg need to add */ }
        }

        /**
         * Trigger the enter server configuration connection.
         *
         * @see MinecraftServer
         * @see ConiumEventType.SERVER_CONFIGURATION_CONNECTION
         *
         * @param server the minecraft server
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireServerConfigurationEvent(server: MinecraftServer, handler: ServerConfigurationNetworkHandler) {
            fireEvent(
                ConiumEventType.SERVER_CONFIGURATION_CONNECTION,
                handler
            ) { context ->
                context[ConiumEventArgTypes.SERVER] = server
            }
        }
    }
}
