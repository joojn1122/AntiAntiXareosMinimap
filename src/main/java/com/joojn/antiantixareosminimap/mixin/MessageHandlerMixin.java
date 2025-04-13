package com.joojn.antiantixareosminimap.mixin;

import com.joojn.antiantixareosminimap.AntiAntiXareosMinimap;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {MessageHandler.class}, priority = 2000)
public class MessageHandlerMixin {
    @Inject(method = "onGameMessage", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;)V",
        shift = At.Shift.BEFORE
    ), cancellable = true)
    private void handleSystemMessage(Text message, boolean overlay, CallbackInfo ci) {
        String textString = message.getString();

        for(String bannedMessage : AntiAntiXareosMinimap.BANNED_MESSAGES) {
            if (textString.contains(bannedMessage)) {
                ci.cancel();
                return;
            }
        }
    }
}
