package tk.shanebee.eventSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    private final EventSupport plugin;
    private final FileConfiguration config;

    Commands(EventSupport plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("eventhandler")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    plugin.getConfiguration().loadConfig();
                    plugin.saveConfig();
                    String pre = config.getString("Options.Prefix");
                    sender.sendMessage(ChatColor.AQUA + pre + ChatColor.GREEN + " Config reloaded successfully!");
                } else if (args[0].equalsIgnoreCase("about")) {
                    PluginDescriptionFile desc = plugin.getDescription();
                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "About EventHandler");
                    sender.sendMessage(ChatColor.GOLD + "Plugin Version: " + ChatColor.AQUA + desc.getVersion());
                    sender.sendMessage(ChatColor.GOLD + "Plugin Author: " + ChatColor.AQUA + desc.getAuthors());
                    sender.sendMessage(ChatColor.GOLD + "Bukkit Version: " + ChatColor.AQUA + Bukkit.getBukkitVersion().split("-")[0]);
                    sender.sendMessage(ChatColor.GOLD + "More Info: " + ChatColor.GREEN + "https://www.spigotmc.org/resources/62329/");
                } else {
                    sender.sendMessage(ChatColor.AQUA + "Correct Usage: " + ChatColor.GOLD + cmd.getUsage());
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("list")) {
                    ConfigurationSection entity = config.getConfigurationSection("Entity Events");
                    ConfigurationSection block = config.getConfigurationSection("Block Events");
                    ConfigurationSection server = config.getConfigurationSection("Server Events");
                    ConfigurationSection player = config.getConfigurationSection("Player Events");

                    if (args[1].equalsIgnoreCase("entity") || args[1].equalsIgnoreCase("all")) {
                        sender.sendMessage(ChatColor.GOLD + " -- ENTITY EVENTS:");
                        for (String key : entity.getKeys(false)) {
                            boolean bool = entity.getBoolean(key + ".Cancel");
                            if (bool) {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.GREEN + "true");
                            } else {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.RED + "false");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("block") || args[1].equalsIgnoreCase("all")) {
                        sender.sendMessage(ChatColor.GOLD + " -- BLOCK EVENTS:");
                        for (String key : block.getKeys(false)) {
                            boolean bool = block.getBoolean(key + ".Cancel");
                            if (bool) {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.GREEN + "true");
                            } else {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.RED + "false");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("server") || args[1].equalsIgnoreCase("all")) {
                        sender.sendMessage(ChatColor.GOLD + " -- SERVER EVENTS:");
                        for (String key : server.getKeys(false)) {
                            boolean bool = server.getBoolean(key + ".Cancel");
                            if (bool) {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.GREEN + "true");
                            } else {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.RED + "false");
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("player") || args[1].equalsIgnoreCase("all")) {
                        sender.sendMessage(ChatColor.GOLD + " -- PLAYER EVENTS:");
                        for (String key : player.getKeys(false)) {
                            boolean bool = player.getBoolean(key + ".Cancel");
                            if (bool) {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.GREEN + "true");
                            } else {
                                sender.sendMessage(ChatColor.AQUA + key + " = " + ChatColor.RED + "false");
                            }
                        }
                    }
                } else sender.sendMessage(ChatColor.AQUA + "Correct Usage: " + ChatColor.GOLD + cmd.getUsage());
            } else if (args.length >= 3) {
                String argNew = WordUtils.capitalize(args[2].replace("_", " "));
                String path = StringUtils.capitalize(args[1]) + " Events.";

                if (args[0].equalsIgnoreCase("enable")) {
                    if (config.isBoolean(path + argNew + ".Cancel")) {
                        if (!config.getBoolean(path + argNew + ".Cancel")) {
                            plugin.getConfig().set(path + argNew + ".Cancel", true);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.AQUA + config.getString("Options.Prefix") + ChatColor.GOLD +
                                    " Config has been updated. " + ChatColor.AQUA + argNew +
                                    ChatColor.GOLD + " is now set to true and this event will be cancelled");
                        } else {
                            sender.sendMessage(ChatColor.AQUA + config.getString("Options.Prefix") + ChatColor.GOLD +
                                    " The event " + ChatColor.AQUA + argNew + ChatColor.GOLD + " is already enabled in the config.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.AQUA + config.getString("Options.Prefix") + ChatColor.RED + " Incorrect event");
                    }
                } else if (args[0].equalsIgnoreCase("disable")) {

                    if (config.isBoolean(path + argNew + ".Cancel")) {
                        if (config.getBoolean(path + argNew + ".Cancel")) {
                            plugin.getConfig().set(path + argNew + ".Cancel", false);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.AQUA + config.getString("Options.Prefix") + ChatColor.GOLD +
                                    " Config has been updated. " + ChatColor.AQUA + argNew +
                                    ChatColor.GOLD + " is now set to false and this event will no longer be cancelled");
                        } else {
                            sender.sendMessage(ChatColor.AQUA + config.getString("Options.Prefix") + ChatColor.GOLD +
                                    " The event " + ChatColor.AQUA + argNew + ChatColor.GOLD + " is already disabled in the config.");
                        }
                    } else {
                        sender.sendMessage(ChatColor.AQUA + config.getString("Options.Prefix") + ChatColor.RED + " Incorrect event");
                    }
                }
            } else sender.sendMessage(ChatColor.AQUA + "Correct Usage: " + ChatColor.GOLD + cmd.getUsage());
        }
        return true;
    }

}
