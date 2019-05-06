package tk.shanebee.eventSupport.events;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
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
        if (!config().getBoolean("Entity Events.Spawner Spawn Mob.Cancel")) return;
        if (plugin.hasWorld("Entity Events.Spawner Spawn Mob.Worlds", e.getLocation().getWorld())) {
            e.setCancelled(true);
        }
    }

    // This cancels lightning hitting pigs and turning them into pig zombies
    @EventHandler
    public void onPigZap(PigZapEvent e) {
        if(!config().getBoolean("Entity Events.Pig Zap.Cancel")) return;
        if (plugin.hasWorld("Entity Events.Pig Zap.Worlds", e.getEntity().getWorld())) e.setCancelled(true);
    }

    // Stops entities from trampling turtle eggs
    @EventHandler
    public void onTurtleEggTrample(EntityInteractEvent e) {
        if (e.getBlock().getType().equals(Material.TURTLE_EGG)) {
            if (!config().getBoolean("Entity Events.Trample Turtle Eggs.Cancel")) return;

            if (plugin.hasWorld("Entity Events.Trample Turtle Eggs.Worlds", e.getEntity().getWorld())) e.setCancelled(true);
        }
    }

    // Stops villagers from breaking/planting crops
    @EventHandler
    public void onVillagerBreak(EntityChangeBlockEvent e) {
        if (e.getEntityType() == EntityType.VILLAGER) {
            if (e.getBlock().getType() == Material.AIR && e.getBlock().getRelative(BlockFace.DOWN).getType() == Material.FARMLAND) {
                if (!config().getBoolean("Entity Events.Villager Planting Crops.Cancel")) return;
                if (plugin.hasWorld("Entity Events.Villager Planting Crops.Worlds", e.getBlock().getWorld())) e.setCancelled(true);
            }
            switch (e.getBlock().getType()) {
                case WHEAT:
                case POTATOES:
                case CARROTS:
                case BEETROOTS:
                    if (!config().getBoolean("Entity Events.Villager Breaking Crops.Cancel")) return;
                    if (plugin.hasWorld("Entity Events.Villager Breaking Crops.Worlds", e.getBlock().getWorld())) e.setCancelled(true);
            }
        }
    }

    // Stops entities from entering boats & Minecarts
    @EventHandler
    public void onEntityEnterVehicle(VehicleEnterEvent e) {
        Entity entity = e.getEntered();
        EntityType vehicle = e.getVehicle().getType();
        if(!(entity instanceof Player)) {
            if(vehicle == EntityType.BOAT) {
                if (!config().getBoolean("Entity Events.Enter Boat.Cancel")) return;
                if (plugin.hasWorld("Entity Events.Enter Boat.Worlds", entity.getWorld())) e.setCancelled(true);
            } else if(vehicle == EntityType.MINECART) {
                if (!config().getBoolean("Entity Events.Enter Minecart.Cancel")) return;
                if (plugin.hasWorld("Entity Events.Enter Minecart.Worlds", entity.getWorld())) e.setCancelled(true);
            }
        }
    }

}
