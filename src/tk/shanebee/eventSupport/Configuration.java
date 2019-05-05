package tk.shanebee.eventSupport;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class Configuration {


    /**
     * Sets the defaults for the plugin's config file
     * @param config The plugins config file
     */
    public static void loadConfig(FileConfiguration config) {

        PluginDescriptionFile pdfFile = EventSupport.getPlugin(EventSupport.class).getDescription();
        String ver = pdfFile.getVersion();

        config.addDefault("Options.Prefix", "[EventHandler]");

        // ENTITY EVENTS
        // Enter boat event
        config.addDefault("Entity Events.Enter Boat.Cancel", false);

        // Enter minecart event
        config.addDefault("Entity Events.Enter Minecart.Cancel", false);

        // Pig zap event
        config.addDefault("Entity Events.Pig Zap.Cancel", false);

        // Spawner spawn mob event
        config.addDefault("Entity Events.Spawner Spawn Mob.Cancel", false);

        // Entity trample Turtle egg event
        config.addDefault("Entity Events.Trample Turtle Eggs.Cancel", false);

        // Villager breaking crops event
        config.addDefault("Entity Events.Villager Breaking Crops.Cancel", false);

        // Villager planting crops event
        config.addDefault("Entity Events.Villager Planting Crops.Cancel", false);

        // BLOCK EVENTS
        // Block form event
        config.addDefault("Block Events.Block Form.Cancel", false);

        // Block dispense event
        config.addDefault("Block Events.Dispense.Cancel", false);

        // Leaf decay event
        config.addDefault("Block Events.Leaf Decay.Cancel", false);

        // Sponge absorb event
        config.addDefault("Block Events.Sponge Absorb.Cancel", false);

        // SERVER EVENTS
        // Tab Complete Event
        config.addDefault("Server Events.Tab Complete.Cancel", false);
        config.addDefault("Server Events.Tab Complete.ByPass Perm", "eventhandler.bypass.tabcomplete");
        config.addDefault("Server Events.Tab Complete.Message.Enabled", false);
        config.addDefault("Server Events.Tab Complete.Message.Message", "&cTab complete has been disabled on this server");

        // Lightning strike event
        config.addDefault("Server Events.Lightning Strike.Cancel", false);


        // PLAYER EVENTS
        // Breed event
        config.addDefault("Player Events.Breed.Cancel", false);
        config.addDefault("Player Events.Breed.ByPass Perm", "eventhandler.bypass.breed");
        config.addDefault("Player Events.Breed.Message.Enabled", false);
        config.addDefault("Player Events.Breed.Message.Message", "&cBreeding has been disabled on this server");


        // Bucket fill event
        config.addDefault("Player Events.Bucket Fill.Cancel", false);
        config.addDefault("Player Events.Bucket Fill.ByPass Perm", "eventhandler.bypass.bucketfill");
        config.addDefault("Player Events.Bucket Fill.Message.Enabled", false);
        config.addDefault("Player Events.Bucket Fill.Message.Message", "&cFilling buckets has been disabled on this server");

        // Bucket empty event
        config.addDefault("Player Events.Bucket Empty.Cancel", false);
        config.addDefault("Player Events.Bucket Empty.ByPass Perm", "eventhandler.bypass.bucketempty");
        config.addDefault("Player Events.Bucket Empty.Message.Enabled", false);
        config.addDefault("Player Events.Bucket Empty.Message.Message", "&cEmptying buckets has been disabled on this server");

        // Consume potion event
        config.addDefault("Player Events.Consume Potion.Cancel", false);
        config.addDefault("Player Events.Consume Potion.ByPass Perm", "eventhandler.bypass.consumepotion");
        config.addDefault("Player Events.Consume Potion.Message.Enabled", false);
        config.addDefault("Player Events.Consume Potion.Message.Message", "&cConsumption of potions on this has been disabled");

        // Consume milk event
        config.addDefault("Player Events.Consume Milk.Cancel", false);
        config.addDefault("Player Events.Consume Milk.ByPass Perm", "eventhandler.bypass.consumemilk");
        config.addDefault("Player Events.Consume Milk.Message.Enabled", false);
        config.addDefault("Player Events.Consume Milk.Message.Message", "&cConsumption of milk on this has been disabled");

        // Creative Inventory Drop
        config.addDefault("Player Events.Creative Inventory Drop.Cancel", false);
        config.addDefault("Player Events.Creative Inventory Drop.ByPass Perm", "eventhandler.bypass.creativeinvdrop");
        config.addDefault("Player Events.Creative Inventory Drop.Message.Enabled", false);
        config.addDefault("Player Events.Creative Inventory Drop.Message.Message", "&cDropping items while in creative mode has been disabled on this server");

        // Item drop event
        config.addDefault("Player Events.Drop Item.Cancel", false);
        config.addDefault("Player Events.Drop Item.ByPass Perm", "eventhandler.bypass.dropitem");
        config.addDefault("Player Events.Drop Item.Message.Enabled", false);
        config.addDefault("Player Events.Drop Item.Message.Message", "&cDropping items has been disabled on this server");

        // Enter bed event
        config.addDefault("Player Events.Enter Bed Event.Cancel", false);
        config.addDefault("Player Events.Enter Bed Event.ByPass Perm", "eventhandler.bypass.enterbedevent");
        config.addDefault("Player Events.Enter Bed Event.Message.Enabled", false);
        config.addDefault("Player Events.Enter Bed Event.Message.Message", "&cEntering a bed has been cancelled on this server");

        // Fishing event
        config.addDefault("Player Events.Fishing.Cancel", false);
        config.addDefault("Player Events.Fishing.ByPass Perm", "eventhandler.bypass.fishing");
        config.addDefault("Player Events.Fishing.Message.Enabled", false);
        config.addDefault("Player Events.Fishing.Message.Message", "&cFishing has been disabled on this server");

        // Tame event
        config.addDefault("Player Events.Tame.Cancel", false);
        config.addDefault("Player Events.Tame.ByPass Perm", "eventhandler.bypass.tame");
        config.addDefault("Player Events.Tame.Message.Enabled", false);
        config.addDefault("Player Events.Tame.Message.Message", "&cTaming has been disabled on this server");

        // Throw egg event
        config.addDefault("Player Events.Throw Egg.Cancel", false);
        config.addDefault("Player Events.Throw Egg.ByPass Perm", "eventhandler.bypass.throwegg");
        config.addDefault("Player Events.Throw Egg.Message.Enabled", false);
        config.addDefault("Player Events.Throw Egg.Message.Message", "&cThrowing eggs has been disabled on this server");

        // Throw snowball event
        config.addDefault("Player Events.Throw Snowball.Cancel", false);
        config.addDefault("Player Events.Throw Snowball.ByPass Perm", "eventhandler.bypass.throwsnowball");
        config.addDefault("Player Events.Throw Snowball.Message.Enabled", false);
        config.addDefault("Player Events.Throw Snowball.Message.Message", "&cThrowing snowballs has been disabled on this server");

        // Thrown egg spawn chicken event
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.Cancel", false);
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.ByPass Perm", "eventhandler.bypass.throwneggspawnchicken");
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.Message.Enabled", false);
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.Message.Message", "&cEgg hatching has been disabled on this server");

        // Toggle sprint
        config.addDefault("Player Events.Toggle Sprint.Cancel", false);
        config.addDefault("Player Events.Toggle Sprint.ByPass Perm", "eventhandler.bypass.togglesprint");
        config.addDefault("Player Events.Toggle Sprint.Message.Enabled", false);
        config.addDefault("Player Events.Toggle Sprint.Message.Message", "&cSprinting has been disabled on this server");

        // Trample crops
        config.addDefault("Player Events.Trample Crops.Cancel", false);
        config.addDefault("Player Events.Trample Crops.ByPass Perm", "eventhandler.bypass.tramplecrops");
        config.addDefault("Player Events.Trample Crops.Message.Enabled", false);
        config.addDefault("Player Events.Trample Crops.Message.Message", "&cTrampling crops has been disabled on this server");

        // Trample Turtle egg event
        config.addDefault("Player Events.Trample Turtle Eggs.Cancel", false);
        config.addDefault("Player Events.Trample Turtle Eggs.ByPass Perm", "eventhandler.bypass.trampleeggs");
        config.addDefault("Player Events.Trample Turtle Eggs.Message.Enabled", false);
        config.addDefault("Player Events.Trample Turtle Eggs.Message.Message", "&cTrampling turtle eggs has been disabled on this server");

        // PAPER EVENTS
        String paper;
        if(EventSupport.isRunningPaper()) {

            // Jump event
            config.addDefault("Player Events.Jump.Cancel", false);
            config.addDefault("Player Events.Jump.ByPass Perm", "eventhandler.bypass.jump");
            config.addDefault("Player Events.Jump.Message.Enabled", false);
            config.addDefault("Player Events.Jump.Message.Message", "&cJumping has been disabled on this server");

            // Anvil Damage Event
            config.addDefault("Block Events.Anvil Break.Cancel", false);

            // Skeleton trap event
            config.addDefault("Entity Events.Skeleton Trap.Cancel", false);

            // Turtle lay egg event
            config.addDefault("Entity Events.Turtle Lay Eggs.Cancel", false);

            // Witch throw potion event
            config.addDefault("Entity Events.Witch Throw Potion.Cancel", false);

            paper = "Running Paper: True";
        }
        else {
            paper = "Running Paper: False" +
                    "\nIf you run Paper, you will gain a few more events you can cancel" +
                    "\nIf you wish to consider running Paper, check out their website for more info" +
                    "\nhttps://papermc.io";
        }

        String[] mcVer = Bukkit.getBukkitVersion().split("-");

        config.options().copyDefaults(true);
        config.options().header("Event Handler" + "\n" +
                "Version: " + ver + "\n" +
                "MC Version: " + mcVer[0] + "\n" + paper + "\n\n" +
                "You can pick which events you want to cancel [Just set Cancel: true]" + "\n" +
                "You can also add/enable messages to be sent to the player when the event is cancelled" + "\n" +
                "[MESSAGES ONLY AVAILABLE FOR PLAYER EVENTS + Support color codes]" + "\n" +
                "More info regarding these events can be found on the Spigot resource page:" + "\n" +
                "https://www.spigotmc.org/resources/eventhandler.62329/" + "\n\n" +
                "The permission nodes are just here for reference, changing them will have no affect");
    }

}
