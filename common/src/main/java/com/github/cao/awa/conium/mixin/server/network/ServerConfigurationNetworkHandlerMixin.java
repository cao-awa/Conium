package com.github.cao.awa.conium.mixin.server.network;

import com.github.cao.awa.conium.intermediary.server.ConiumServerEventMixinIntermediary;
import com.github.cao.awa.translator.structuring.cast.Caster;
import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.server.network.ServerConfigurationPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerConfigurationPacketListenerImpl.class)
abstract public class ServerConfigurationNetworkHandlerMixin extends ServerCommonPacketListenerImpl {
    public ServerConfigurationNetworkHandlerMixin(MinecraftServer server, Connection connection, CommonListenerCookie clientData) {
        super(server, connection, clientData);
    }

    @Inject(
            method = "startConfiguration",
            at = @At("HEAD")
    )
    public void enterConfig(CallbackInfo ci) {
        ConiumServerEventMixinIntermediary.fireServerConfigurationEvent(this.server, asConfigurationHandler());
    }

    @Inject(
            method = "startConfiguration",
            at = @At("RETURN")
    )
    public void completeConfig(CallbackInfo ci) {
        ConiumServerEventMixinIntermediary.fireServerConfiguredEvent(this.server, asConfigurationHandler());
    }

    private ServerConfigurationPacketListenerImpl asConfigurationHandler() {
        return Caster.cast(this);
    }
}
