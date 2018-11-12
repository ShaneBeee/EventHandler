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
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
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
    public void onCreativeInvDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(config().getBoolean("Player Events.Creative Inventory Drop.Cancel")) {
            if(!p.hasPermission("eventhandler.bypass.creativeinvdrop") && !p.getGameMode().equals(GameMode.SURVIVAL)) {
                e.setCancelled(true);
                if(config().getBoolean("Player Events.Creative Inventory Drop.Message.Enabled")) {
                    String msg = config().getString("Player Events.Creative Inventory Drop.Message.Message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // Stops players from trampling turtle eggs
    @EventHandler
    public void onPlayerTrample(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.PHYSICAL)) {
            Player p = e.getPlayer();
            Material block = e.getClickedBlock().getType();
            if(block.equals(Material.TURTLE_EGG)) {
                if (config().getBoolean("Player Events.Trample Turtle Eggs.Cancel")) {
                    if(!p.hasPermission("eventhandler.bypass.trampleeggs")) {
                        e.setCancelled(true);
                        if (config().getBoolean("Player Events.Trample Turtle Eggs.Message.Enabled")) {
                            String msg = config().getString("Player Events.Trample Turtle Eggs.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                        }}}
            } else if(block.equals(Material.FARMLAND)) {
                if(config().getBoolean("Player Events.Trample Crops.Cancel")) {
                    if(!p.hasPermission("eventhandler.bypass.tramplecrops")) {
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
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(hand.equals(Material.EGG) || offHand.equals(Material.EGG)) {
                if (config().getBoolean("Player Events.Throw Egg.Cancel")) {
                    if (!p.hasPermission("eventhandler.bypass.throwegg")) {
                        e.setCancelled(true);
                        if(config().getBoolean("Player Events.Throw Egg.Message.Enabled")) {
                            String msg = config().getString("Player Events.Throw Egg.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                        }
                    }
                }
                // Stops players from throwing snowballs
            } else if(hand.equals(Material.SNOWBALL) || offHand.equals(Material.SNOWBALL)) {
                if(config().getBoolean("Player Events.Throw Snowball.Cancel")) {
                    if(!p.hasPermission("eventhandler.bypass.throwsnowball")) {
                        e.setCancelled(true);
                        if(config().getBoolean("Player Events.Throw Snowball.Message.Enabled")) {
                            String msg = config().getString("Player Events.Throw Snowball.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
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
        if(config().getBoolean("Player Events.Fishing.Cancel")) {
            if(!p.hasPermission("eventhandler.bypass.fishing")) {
                e.setCancelled(true);
                if(config().getBoolean("Player Events.Fishing.Message.Enabled")) {
                    String msg = config().getString("Player Events.Fishing.Message.Message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }

    // Stops players from consuming potions
    @EventHandler
    public void onPlayerConsumePotion(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if(e.getItem().getType().equals(Material.POTION)) {
            if(config().getBoolean("Player Events.Consume Potion.Cancel")) {
                if(!p.hasPermission("eventhandler.bypass.consumepotion")) {
                    e.setCancelled(true);
                    if(config().getBoolean("Player Events.Consume Potion.Message.Enabled")) {
                        String msg = config().getString("Player Events.Consume Potion.Message.Message");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
            }
    // Stops players from consuming milk
        } else if(e.getItem().getType().equals(Material.MILK_BUCKET)) {
            if(config().getBoolean("Player Events.Consume Milk.Cancel")) {
                if(!p.hasPermission("eventhandler.bypass.consumemilk")) {
                    e.setCancelled(true);
                    if(config().getBoolean("Player Events.Consume Milk.Message.Enabled")) {
                        String msg = config().getString("Player Events.Consume Milk.Message.Message");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
            }
        }
    }

    // Stops players from sprinting
    @EventHandler
    public void onPlayerSprint(PlayerToggleSprintEvent e) {
        Player p = e.getPlayer();
        if (config().getBoolean("Player Events.Toggle Sprint.Cancel")) {
            if (!p.hasPermission("eventhandler.bypass.togglesprint")) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                    public void run() {
                        if(config().getBoolean("Player Events.Toggle Sprint.Message.Enabled") && p.isSprinting()) {
                            String msg = config().getString("Player Events.Toggle Sprint.Message.Message");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                        }
                        p.setSprinting(false);
                    }
                }, 5L);
            }
        }
    }

}
