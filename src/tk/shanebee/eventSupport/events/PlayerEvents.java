package tk.shanebee.eventSupport.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTameEvent;
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
        if (!config().getBoolean("Player Events.Thrown Egg Spawn Chicken.Cancel")) return;
        if (plugin.hasWorld("Player Events.Thrown Egg Spawn Chicken.Worlds", e.getPlayer().getWorld())) {
            if (!e.getPlayer().hasPermission("eventhandler.bypass.throwneggspawnchicken")) {
                if (e.isHatching()) {
                    e.setHatching(false);
                    if (config().getBoolean("Player Events.Thrown Egg Spawn Chicken.Message.Enabled")) {
                        String msg = config().getString("Player Events.Thrown Egg Spawn Chicken.Message.Message");
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                    }
                }
            }
        }
    }

    // This cancels player's being able to enter their beds
    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e) {
        if (!config().getBoolean("Player Events.Enter Bed Event.Cancel")) return;
        if (plugin.hasWorld("Player Events.Enter Bed Event.Worlds", e.getPlayer().getWorld())) {
            if (!e.getPlayer().hasPermission("eventhandler.bypass.enterbedevent")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Enter Bed Event.Message.Enabled")) {
                    String msg = config().getString("Player Events.Enter Bed Event.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // This cancels player's filling buckets
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        if (!config().getBoolean("Player Events.Bucket Fill.Cancel")) return;
        if (plugin.hasWorld("Player Events.Bucket Fill.Worlds", e.getPlayer().getWorld())) {
            if (!e.getPlayer().hasPermission("eventhandler.bypass.bucketfill")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Bucket Fill.Message.Enabled")) {
                    String msg = config().getString("Player Events.Bucket Fill.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // This cancels player's emptying buckets
    @EventHandler
    public void onBucketFill(PlayerBucketEmptyEvent e) {
        if (!config().getBoolean("Player Events.Bucket Empty.Cancel")) return;
        if (plugin.hasWorld("Player Events.Bucket Empty.Worlds", e.getPlayer().getWorld())) {
            if (!e.getPlayer().hasPermission("eventhandler.bypass.bucketempty")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Bucket Empty.Message.Enabled")) {
                    String msg = config().getString("Player Events.Bucket Empty.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // This cancels player's dropping items from their inventory
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if (!config().getBoolean("Player Events.Item Drop.Cancel")) return;
        if (plugin.hasWorld("Player Events.Item Drop.Worlds", e.getPlayer().getWorld())) {
            if (!e.getPlayer().hasPermission("eventhandler.bypass.dropitem")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Drop Item.Message.Enabled")) {
                    String msg = config().getString("Player Events.Drop Item.Message.Message");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // This cancels player's breeding animals
    @EventHandler
    public void onBreed(EntityBreedEvent e) {
        if (!config().getBoolean("Player Events.Breed.Cancel")) return;
        if (plugin.hasWorld("Player Events.Breed.Worlds", e.getBreeder().getWorld())) {
            if (!e.getBreeder().hasPermission("eventhandler.bypass.breed")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Breed.Message.Enabled")) {
                    String msg = config().getString("Player Events.Breed.Message.Message");
                    e.getBreeder().sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // This cancels player's taming animals
    @EventHandler
    public void onTame(EntityTameEvent e) {
        Player player = ((Player) e.getOwner());
        if (!config().getBoolean("Player Events.Tame.Cancel")) return;
        if (plugin.hasWorld("Player Events.Tame.Worlds", player.getWorld())) {
            if (!player.hasPermission("eventhandler.bypass.tame")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Tame.Message.Enabled")) {
                    String msg = config().getString("Player Events.Tame.Message.Message");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // Stops players from dropping items when in creative mode
    @EventHandler
    public void onCreativeInvDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!config().getBoolean("Player Events.Creative Inventory Drop.Cancel")) return;
        if (plugin.hasWorld("Player Events.Creative Inventory Drop.Worlds", p.getWorld())) {
            if (!p.hasPermission("eventhandler.bypass.creativeinvdrop") && !p.getGameMode().equals(GameMode.SURVIVAL)) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Creative Inventory Drop.Message.Enabled")) {
                    String msg = config().getString("Player Events.Creative Inventory Drop.Message.Message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // Stops players from trampling turtle eggs
    @EventHandler
    public void onPlayerTrample(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.PHYSICAL)) {
            Player p = e.getPlayer();
            assert e.getClickedBlock() != null;
            Material block = e.getClickedBlock().getType();
            if (block.equals(Material.TURTLE_EGG)) {
                if (!config().getBoolean("Player Events.Trample Turtle Eggs.Cancel")) return;
                if (plugin.hasWorld("Player Events.Trample Turtle Eggs.Worlds", p.getWorld())) {
                    if (!p.hasPermission("eventhandler.bypass.trampleeggs")) {
                        e.setCancelled(true);
                        if (config().getBoolean("Player Events.Trample Turtle Eggs.Message.Enabled")) {
                            String msg = config().getString("Player Events.Trample Turtle Eggs.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                        }}}
            } else if (block.equals(Material.FARMLAND)) {
                if (!config().getBoolean("Player Events.Trample Crops.Cancel")) return;
                if (plugin.hasWorld("Player Events.Trample Crops.Worlds", p.getWorld())) {
                    if (!p.hasPermission("eventhandler.bypass.tramplecrops")) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    // Stops players from throwing eggs
    @EventHandler
    public void onPlayerThrowEgg(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material hand = p.getInventory().getItemInMainHand().getType();
        Material offHand = p.getInventory().getItemInOffHand().getType();
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (hand.equals(Material.EGG) || offHand.equals(Material.EGG)) {
                if (!config().getBoolean("Player Events.Throw Egg.Cancel")) return;
                if (plugin.hasWorld("Player Events.Throw Egg.Worlds", p.getWorld())) {
                    if (!p.hasPermission("eventhandler.bypass.throwegg")) {
                        e.setCancelled(true);
                        if (config().getBoolean("Player Events.Throw Egg.Message.Enabled")) {
                            String msg = config().getString("Player Events.Throw Egg.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                        }
                    }
                }
                // Stops players from throwing snowballs
            } else if (hand.equals(Material.SNOWBALL) || offHand.equals(Material.SNOWBALL)) {
                if (!config().getBoolean("Player Events.Throw Snowball.Cancel")) return;
                if (plugin.hasWorld("Player Events.Throw Snowball.Worlds", p.getWorld())) {
                    if (!p.hasPermission("eventhandler.bypass.throwsnowball")) {
                        e.setCancelled(true);
                        if (config().getBoolean("Player Events.Throw Snowball.Message.Enabled")) {
                            String msg = config().getString("Player Events.Throw Snowball.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                        }
                    }
                }
            }
        }
    }

    // Stops players from fishing
    @EventHandler
    public void onPlayerFishing(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if (!config().getBoolean("Player Events.Fishing.Cancel")) return;
        if (plugin.hasWorld("Player Events.Fishing.Worlds", p.getWorld())) {
            if (!p.hasPermission("eventhandler.bypass.fishing")) {
                e.setCancelled(true);
                if (config().getBoolean("Player Events.Fishing.Message.Enabled")) {
                    String msg = config().getString("Player Events.Fishing.Message.Message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                }
            }
        }
    }

    // Stops players from consuming potions
    @EventHandler
    public void onPlayerConsumePotion(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (e.getItem().getType().equals(Material.POTION)) {
            if (!config().getBoolean("Player Events.Consume Potion.Cancel")) return;
            if (plugin.hasWorld("Player Events.Consume Potion.Worlds", p.getWorld())) {
                if (!p.hasPermission("eventhandler.bypass.consumepotion")) {
                    e.setCancelled(true);
                    if (config().getBoolean("Player Events.Consume Potion.Message.Enabled")) {
                        String msg = config().getString("Player Events.Consume Potion.Message.Message");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                    }
                }
            }
    // Stops players from consuming milk
        } else if (e.getItem().getType().equals(Material.MILK_BUCKET)) {
            if (!config().getBoolean("Player Events.Consume Milk.Cancel")) return;
            if (plugin.hasWorld("Player Events.Consume Milk.Worlds", p.getWorld())) {
                if (!p.hasPermission("eventhandler.bypass.consumemilk")) {
                    e.setCancelled(true);
                    if (config().getBoolean("Player Events.Consume Milk.Message.Enabled")) {
                        String msg = config().getString("Player Events.Consume Milk.Message.Message");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                    }
                }
            }
        }
    }

    // Stops players from sprinting
    @EventHandler
    public void onPlayerSprint(PlayerToggleSprintEvent e) {
        Player p = e.getPlayer();
        if (!config().getBoolean("Player Events.Toggle Sprint.Cancel")) return;
        if (plugin.hasWorld("Player Events.Toggle Sprint.Worlds", p.getWorld())) {
            if (!p.hasPermission("eventhandler.bypass.togglesprint")) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
                    if (config().getBoolean("Player Events.Toggle Sprint.Message.Enabled") && p.isSprinting()) {
                        String msg = config().getString("Player Events.Toggle Sprint.Message.Message");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg != null ? msg : ""));
                    }
                    p.setSprinting(false);
                }, 5L);
            }
        }
    }

}
