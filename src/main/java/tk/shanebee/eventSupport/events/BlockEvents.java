package tk.shanebee.eventSupport.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SpongeAbsorbEvent;
import tk.shanebee.eventSupport.EventSupport;

public class BlockEvents implements Listener {

    private final EventSupport plugin;
    private final FileConfiguration config;

    public BlockEvents(EventSupport plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    // Blocks dispensers from dispensing items
    @EventHandler
    private void onDispense(BlockDispenseEvent e) {
        if (!config.getBoolean("Block Events.Dispense.Cancel")) return;
        if (plugin.hasWorld("Block Events.Dispense.Worlds", e.getBlock().getWorld())) {
            e.setCancelled(true);
        }
    }

    // Stops blocks from forming
    @EventHandler
    private void onForm(BlockFormEvent e) {
        if (!config.getBoolean("Block Events.Block Form.Cancel")) return;
        if (plugin.hasWorld("Block Events.Block Form.Worlds", e.getBlock().getWorld())) {
            e.setCancelled(true);
        }
    }

    // Stops leaves from decaying
    @EventHandler
    private void onLeafDecay(LeavesDecayEvent e) {
        if (!config.getBoolean("Block Events.Leaf Decay.Cancel")) return;
        if (plugin.hasWorld("Block Events.Leaf Decay.Worlds", e.getBlock().getWorld())) {
            e.setCancelled(true);
        }
    }

    // Stops sponges from absorbing
    @EventHandler
    private void onSpongeAbsorb(SpongeAbsorbEvent e) {
        if (!config.getBoolean("Block Events.Sponge Absorb.Cancel")) return;
        if (plugin.hasWorld("Block Events.Sponge Absorb.Worlds", e.getBlock().getWorld())) {
            e.setCancelled(true);
        }
    }

}
