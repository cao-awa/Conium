package com.github.cao.awa.conium.mixin.server.network;

import com.github.cao.awa.conium.intermediary.server.ConiumServerEventMixinIntermediary;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerCommonNetworkHandler;
import net.minecraft.server.network.ServerConfigurationNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerConfigurationNetworkHandler.class)
abstract public class ServerConfigurationNetworkHandlerMixin extends ServerCommonNetworkHandler {
    public ServerConfigurationNetworkHandlerMixin(MinecraftServer server, ClientConnection connection, ConnectedClientData clientData) {
        super(server, connection, clientData);
    }

    @Inject(
            method = "sendConfigurations",
            at = @At("HEAD")
    )
    public void enterConfig(CallbackInfo ci) {
        ConiumServerEventMixinIntermediary.fireServerConfigurationEvent(this.server, asConfigurationHandler());
    }

    @Inject(
            method = "sendConfigurations",
            at = @At("RETURN")
    )
    public void completeConfig(CallbackInfo ci) {
        ConiumServerEventMixinIntermediary.fireServerConfiguredEvent(this.server, asConfigurationHandler());
    }

    private ServerConfigurationNetworkHandler asConfigurationHandler() {
        return Manipulate.cast(this);
    }
}
