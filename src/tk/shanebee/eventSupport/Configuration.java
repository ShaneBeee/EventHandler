package tk.shanebee.eventSupport;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {

    public static void loadConfig(FileConfiguration config) {

        config.addDefault("Options.Prefix", "[EventHandler]");

        // ENTITY EVENTS
        // Spawner spawn mob event
        config.addDefault("Entity Events.Spawner Spawn Mob.Cancel", false);

        // Pig zap event
        config.addDefault("Entity Events.Pig Zap.Cancel", false);

        // BLOCK EVENTS
        // Block dispense event
        config.addDefault("Block Events.Dispense.Cancel", false);

        // Block form event
        config.addDefault("Block Events.Block Form.Cancel", false);

        // Leaf decay event
        config.addDefault("Block Events.Leaf Decay.Cancel", false);

        // Sponge absorb event
        config.addDefault("Block Events.Sponge Absorb.Cancel", false);

        // SERVER EVENTS
        // Tab Complete Event
        config.addDefault("Server Events.Tab Complete.Cancel", false);
        config.addDefault("Server Events.Tab Complete.ByPass Perm", "eventhandler.bypass.tabcomplete");
        config.addDefault("Server Events.Tab Complete.Message.Enabled", false);
        config.addDefault("Server Events.Tab Complete.Message.Message", "Tab complete has been disabled on this server");

        // Lightning strike event
        config.addDefault("Server Events.Lightning Strike.Cancel", false);


        // PLAYER EVENTS
        // Thrown egg spawn chicken event
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.Cancel", false);
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.ByPass Perm", "eventhandler.bypass.throwneggspawnchicken");
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.Message.Enabled", false);
        config.addDefault("Player Events.Thrown Egg Spawn Chicken.Message.Message", "&cEgg hatching has been disabled on this server");


        // Enter bed event
        config.addDefault("Player Events.Enter Bed Event.Cancel", false);
        config.addDefault("Player Events.Enter Bed Event.ByPass Perm", "eventhandler.bypass.enterbedevent");
        config.addDefault("Player Events.Enter Bed Event.Message.Enabled", false);
        config.addDefault("Player Events.Enter Bed Event.Message.Message", "&cEntering a bed has been cancelled on this server");

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

        // Item drop event
        config.addDefault("Player Events.Drop Item.Cancel", false);
        config.addDefault("Player Events.Drop Item.ByPass Perm", "eventhandler.bypass.dropitem");
        config.addDefault("Player Events.Drop Item.Message.Enabled", false);
        config.addDefault("Player Events.Drop Item.Message.Message", "&cDropping items has been disabled on this server");

        // Breed event
        config.addDefault("Player Events.Breed.Cancel", false);
        config.addDefault("Player Events.Breed.ByPass Perm", "eventhandler.bypass.breed");
        config.addDefault("Player Events.Breed.Message.Enabled", false);
        config.addDefault("Player Events.Breed.Message.Message", "&cBreeding has been disabled on this server");

        // Tame event
        config.addDefault("Player Events.Tame.Cancel", false);
        config.addDefault("Player Events.Tame.ByPass Perm", "eventhandler.bypass.tame");
        config.addDefault("Player Events.Tame.Message.Enabled", false);
        config.addDefault("Player Events.Tame.Message.Message", "&cTaming has been disabled on this server");

        // Creative Inventory Drop
        config.addDefault("Player Events.Creative Inventory Drop.Cancel", false);
        config.addDefault("Player Events.Creative Inventory Drop.ByPass Perm", "eventhandler.bypass.creativeinvdrop");
        config.addDefault("Player Events.Creative Inventory Drop.Message.Enabled", false);
        config.addDefault("Player Events.Creative Inventory Drop.Message.Message", "&cDropping items while in creative mode has been disabled on this server");

        // PAPER EVENTS
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
        }

        // Header
        String header1 = "Event Handler";
        String header2 = "You can pick which events you want to cancel";
        String header3 = "You can also add/enable messages to be sent to the player when the event is cancelled";
        String header4 = "The permission nodes are just here for reference, changing them will have no effect";



        config.options().copyDefaults(true);
        config.options().header(header1 + "\n" + header2 + "\n" + header3 + "\n\n" + header4 + "\n");



    }

}
