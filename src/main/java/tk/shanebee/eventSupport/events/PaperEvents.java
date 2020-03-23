package tk.shanebee.eventSupport.events;

import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import com.destroystokyo.paper.event.entity.SkeletonHorseTrapEvent;
import com.destroystokyo.paper.event.entity.TurtleLayEggEvent;
import com.destroystokyo.paper.event.entity.WitchThrowPotionEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tk.shanebee.eventSupport.EventSupport;
import tk.shanebee.eventSupport.util.Util;

public class PaperEvents implements Listener {

    private final EventSupport plugin;
    private final FileConfiguration config;

    public PaperEvents(EventSupport plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    // Stops player's from jumping
    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        if (!config.getBoolean("Player Events.Jump.Cancel")) return;
        if (plugin.hasWorld("Player Events.Jump.Worlds", player.getWorld())) {
            if (!player.hasPermission("eventhandler.bypass.jump")) {
                e.setCancelled(true);
                if (config.getBoolean("Player Events.Jump.Message.Enabled")) {
                    String msg = config.getString("Player Events.Jump.Message.Message");
                    Util.scm(player, msg);
                }
            }
        }
    }

    // Stops anvils from breaking
    @EventHandler
    public void onAnvilBreak(AnvilDamagedEvent e) {
        if (!config.getBoolean("Block Events.Anvil Break.Cancel")) return;
        if (plugin.hasWorld("Block Events.Anvil Break.Worlds", e.getView().getPlayer().getWorld())) {
            e.setCancelled(true);
        }
    }

    // Stops turtles from laying eggs
    @EventHandler
    public void onTurtleLayEgg(TurtleLayEggEvent e) {
        if (!config.getBoolean("Entity Events.Turtle Lay Eggs.Cancel")) return;
        if (plugin.hasWorld("Entity Events.Turtle Lay Eggs.Worlds", e.getEntity().getWorld())) {
            e.setCancelled(true);
        }
    }

    // Stops skeleton traps from spawning
    @EventHandler
    public void onSkeletonTrap(SkeletonHorseTrapEvent e) {
        if (!config.getBoolean("Entity Events.Skeleton Trap.Cancel")) return;
        if (plugin.hasWorld("Entity Events.Skeleton Trap.Worlds", e.getEntity().getWorld())) {
            e.setCancelled(true);
        }
    }

    // Stops witches from throwing potions
    @EventHandler
    public void onWitchThrowPotion(WitchThrowPotionEvent e) {
        if (!config.getBoolean("Entity Events.Witch Throw Potion.Cancel")) return;
        if (plugin.hasWorld("Entity Events.Witch Throw Potion.Worlds", e.getEntity().getWorld())) {
            e.setCancelled(true);
        }
    }

}
