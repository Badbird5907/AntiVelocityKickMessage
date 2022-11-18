package dev.badbird.velocitykickmessagefix;

import com.google.inject.Inject;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.slf4j.Logger;

@Plugin(
        id = "velocitykickmessagefix",
        name = "VelocityKickMessageFix",
        version = BuildConstants.VERSION
)
public class VelocityKickMessageFix {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }

    @Subscribe
    public void onPlayerKick(KickedFromServerEvent event) {
        if (event.getResult() instanceof KickedFromServerEvent.DisconnectPlayer result) {
            Component reason = result.getReasonComponent();
            if (reason != null) {
                //logger.info("Reason: " + LegacyComponentSerializer.legacySection().serialize(reason));
                reason = Component.text("{1}");
                event.setResult(KickedFromServerEvent.DisconnectPlayer.create(reason));
            }
        }
    }
}
