package tk.shanebee.eventSupport.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.world.TimeSkipEvent;
import tk.shanebee.eventSupport.EventSupport;
import tk.shanebee.eventSupport.util.Util;

public class ServerEvents implements Listener {

    private final EventSupport plugin;
    private final FileConfiguration config;

    public ServerEvents(EventSupport plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        if (Util.classExists("org.bukkit.event.world.TimeSkipEvent")) {
            Bukkit.getPluginManager().registerEvents(new NightSkip(), plugin);
        }
    }

    // Stops the tab complete event
    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        if (!config.getBoolean("Server Events.Tab Complete.Cancel")) return;
        if (!(e.getSender() instanceof Player)) return;
        Player player = ((Player) e.getSender());
        if (plugin.hasWorld("Server Events.Tab Complete.Worlds", player.getWorld())) {
            if (!player.hasPermission("eventhandler.bypass.tabcomplete")) {
                e.setCancelled(true);
                if (config.getBoolean("Server Events.Tab Complete.Message.Enabled")) {
                    String msg = config.getString("Server Events.Tab Complete.Message.Message");
                    Util.scm(player, msg);
                }
            }
        }
    }

    // Stops lightning from striking
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent e) {
        if (!config.getBoolean("Server Events.Lightning Strike.Cancel")) return;
        if (plugin.hasWorld("Server Events.Lightning Strike.Worlds", e.getWorld())) {
            e.setCancelled(true);
        }
    }

    class NightSkip implements Listener {

        // Stop the night skip event
        @EventHandler
        private void onNightSkip(TimeSkipEvent event) {
            if (event.getSkipReason() == TimeSkipEvent.SkipReason.NIGHT_SKIP) {
                if (!config.getBoolean("Server Events.Night Skip.Cancel")) return;
                if (plugin.hasWorld("Server Events.Night Skip.Worlds", event.getWorld())) {
                    event.setCancelled(true);
                }
            }
        }

    }

}
