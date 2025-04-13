package com.joojn.antiantixareosminimap;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class AntiAntiXareosMinimap implements ModInitializer {
    public static final Identifier XAEROMINIMAP_ID = Identifier.of("xaerominimap", "main");
    public static final Logger LOGGER = LoggerFactory.getLogger(AntiAntiXareosMinimap.class.getSimpleName());

    public static final String[] BANNED_MESSAGES = {
        "§r§e§s§e§t§x§a§e§r§o",
        "§n§o§m§i§n§i§m§a§p",
        "§f§a§i§r§x§a§e§r§o"
    };

    @Override
    public void onInitialize() {

        // Unregister Xaero Minimap play channel
        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> {
            if (ClientPlayNetworking.getGlobalReceivers().contains(XAEROMINIMAP_ID)) {
                ClientPlayNetworking.unregisterGlobalReceiver(XAEROMINIMAP_ID);
                LOGGER.info("Unregistered Xaero Minimap play channel: {}", XAEROMINIMAP_ID);
            }
        });
    }
}
