package dev.badbird.velocitykickmessagefix;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.slf4j.Logger;

import java.util.Optional;

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
            Component reasonComponent = result.getReasonComponent();
            Optional<Component> optReason = event.getServerKickReason();
            if (reasonComponent != null && optReason.isPresent()) {
                /*
                String reasonString = LegacyComponentSerializer.legacySection().serialize(reasonComponent); // TODO there's probally a better way to do this
                logger.info("Reason: " + reasonString);
                if (reasonString.contains("velocity.error.moved-to-new-server")) {

                }
                event.setResult(KickedFromServerEvent.DisconnectPlayer.create(reasonComponent));
                 */
                //wow im stupid, i could have just done the below:
                event.setResult(KickedFromServerEvent.DisconnectPlayer.create(optReason.get()));
            }
        }
    }
}
