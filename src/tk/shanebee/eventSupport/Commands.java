package tk.shanebee.eventSupport;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class Commands implements CommandExecutor {

    private final EventSupport plugin;

    Commands(EventSupport instance) {
        plugin = instance;
    }

    private FileConfiguration config() {
        return plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("eventhandler")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    Configuration.loadConfig(plugin.getConfig());
                    plugin.saveConfig();
                    String pre = config().getString("Options.Prefix");
                    sender.sendMessage(ChatColor.AQUA + pre + ChatColor.GREEN + " Config reloaded successfully!");
                } else if (args[0].equalsIgnoreCase("about")) {
                    PluginDescriptionFile desc = plugin.getDescription();
                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "About EventHandler");
                    sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.AQUA + desc.getVersion());
                    sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.AQUA + desc.getAuthors());
                } else sender.sendMessage(ChatColor.AQUA + "Correct Usage: " + ChatColor.GOLD + cmd.getUsage());
            } else sender.sendMessage(ChatColor.AQUA + "Correct Usage: " + ChatColor.GOLD + cmd.getUsage());
        }
        return true;
    }

}