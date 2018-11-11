package tk.shanebee.eventSupport.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import tk.shanebee.eventSupport.EventSupport;

public class EntityEvents implements Listener {

    private EventSupport plugin;

    public EntityEvents(EventSupport instance) {
        plugin = instance;
    }

    private FileConfiguration config() {
        return plugin.getConfig();
    }

    // This cancels mobs from spawning from spawners
    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent e) {
        if (config().getBoolean("Entity Events.Spawner Spawn Mob.Cancel")) {
            e.setCancelled(true);
        }
    }

    // This cancels lightning hitting pigs and turning them into pig zombies
    @EventHandler
    public void onPigZap(PigZapEvent e) {
        if(config().getBoolean("Entity Events.Pig Zap.Cancel")) {
            e.setCancelled(true);
        }
    }
}
