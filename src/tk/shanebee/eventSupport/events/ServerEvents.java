package tk.shanebee.eventSupport.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import tk.shanebee.eventSupport.EventSupport;

public class ServerEvents implements Listener {

    private EventSupport plugin;

    public ServerEvents(EventSupport instance) {
        plugin = instance;
    }

    private FileConfiguration config() {
        return plugin.getConfig();
    }

    // Stops the tab complete event
    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        if (!config().getBoolean("Server Events.Tab Complete.Cancel")) return;
        if (!(e.getSender() instanceof Player)) return;
        if (plugin.hasWorld("Server Events.Tab Complete.Worlds", e.getLocation().getWorld())) {
            if(!e.getSender().hasPermission("eventhandler.bypass.tabcomplete")) {
                e.setCancelled(true);
                if(config().getBoolean("Server Events.Tab Complete.Message.Enabled")) {
                    String msg = config().getString("Server Events.Tab Complete.Message.Message");
                    e.getSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // Stops lightning from striking
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent e) {
        if (!config().getBoolean("Server Events.Lightning Strike.Cancel")) return;
        if (plugin.hasWorld("Server Events.Lightning Strike.Worlds", e.getWorld())) {
            e.setCancelled(true);
        }
    }

}
