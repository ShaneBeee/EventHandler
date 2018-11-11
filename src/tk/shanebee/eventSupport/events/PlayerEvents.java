package tk.shanebee.eventSupport.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import tk.shanebee.eventSupport.EventSupport;


public class PlayerEvents implements Listener {

    private EventSupport plugin;

    public PlayerEvents(EventSupport instance) {
        plugin = instance;
    }

    private FileConfiguration config() {
        return plugin.getConfig();
    }

    // This cancels the chicken spawning on a Player egg throw event
    @EventHandler
    public void onThrownEggSpawn(PlayerEggThrowEvent e) {
        if(config().getBoolean("Player Events.Thrown Egg Spawn Chicken.Cancel")) {
            if(!e.getPlayer().hasPermission("eventhandler.bypass.throwneggspawnchicken")) {
                if (e.isHatching()) {
                    e.setHatching(false);
                    if (config().getBoolean("Player Events.Thrown Egg Spawn Chicken.Message.Enabled")) {
                        String msg = config().getString("Player Events.Thrown Egg Spawn Chicken.Message.Message");
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
            }
        }
    }

    // This cancels player's being able to enter their beds
    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e) {
        if(config().getBoolean("Player Events.Enter Bed Event.Cancel")) {
            if(!e.getPlayer().hasPermission("eventhandler.bypass.enterbedevent")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Enter Bed Event.Message.Enabled")) {
                    String msg = config().getString("Player Events.Enter Bed Event.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // This cancels player's filling buckets
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        if(config().getBoolean("Player Events.Bucket Fill.Cancel")) {
            if(!e.getPlayer().hasPermission("eventhandler.bypass.bucketfill")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Bucket Fill.Message.Enabled")) {
                    String msg = config().getString("Player Events.Bucket Fill.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // This cancels player's emptying buckets
    @EventHandler
    public void onBucketFill(PlayerBucketEmptyEvent e) {
        if(config().getBoolean("Player Events.Bucket Empty.Cancel")) {
            if(!e.getPlayer().hasPermission("eventhandler.bypass.bucketempty")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Bucket Empty.Message.Enabled")) {
                    String msg = config().getString("Player Events.Bucket Empty.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // This cancels player's dropping items from their inventory
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if(config().getBoolean("Player Events.Item Drop.Cancel")) {
            if(!e.getPlayer().hasPermission("eventhandler.bypass.dropitem")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Drop Item.Message.Enabled")) {
                    String msg = config().getString("Player Events.Drop Item.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // This cancels player's breeding animals
    @EventHandler
    public void onBreed(EntityBreedEvent e) {
        if(config().getBoolean("Player Events.Breed.Cancel")) {
            if(!e.getBreeder().hasPermission("eventhandler.bypass.breed")) {
                e.setCancelled(true);
                if(config().getBoolean("Player Events.Breed.Message.Enabled")) {
                    String msg = config().getString("Player Events.Breed.Message.Message");
                    e.getBreeder().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // This cancels player's taming animals
    @EventHandler
    public void onTame(EntityTameEvent e) {
        Player player = ((Player) e.getOwner());
        if(config().getBoolean("Player Events.Tame.Cancel")) {
            if(!player.hasPermission("eventhandler.bypass.tame")) {
                e.setCancelled(true);
                if(config().getBoolean("Player Events.Tame.Message.Enabled")) {
                    String msg = config().getString("Player Events.Tame.Message.Message");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // Stops players from dropping items when in creative mode
    @EventHandler
    public void onCreativeInvDrop(InventoryCreativeEvent e) {
        Player p = ((Player) e.getWhoClicked());
        if(config().getBoolean("Player Events.Creative Inventory Drop.Cancel")) {
            if(!p.hasPermission("eventhandler.bypass.creativeinvdrop")) {
                e.setCancelled(true);
                if(config().getBoolean("Player Events.Creative Inventory Drop.Message.Enabled")) {
                    String msg = config().getString("Player Events.Creative Inventory Drop.Message.Message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

}
