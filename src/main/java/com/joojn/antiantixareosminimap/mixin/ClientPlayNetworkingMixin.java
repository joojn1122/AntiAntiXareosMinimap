package com.joojn.antiantixareosminimap.mixin;

import com.joojn.antiantixareosminimap.AntiAntiXareosMinimap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworking.class)
public class ClientPlayNetworkingMixin {
    @Inject(method = "send", at = @At("HEAD"), cancellable = true)
    private static void send(CustomPayload payload, CallbackInfo ci) {
        if(payload.getId().id().equals(AntiAntiXareosMinimap.XAEROMINIMAP_ID)) {
            ci.cancel();
        }
    }
}
