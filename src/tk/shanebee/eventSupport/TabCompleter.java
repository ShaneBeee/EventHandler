package tk.shanebee.eventSupport;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    private final EventSupport plugin;

    TabCompleter(EventSupport instance) {
        plugin = instance;
    }

    private FileConfiguration config() {
        return plugin.getConfig();
    }

    private static final List<String> COMMANDS = Arrays.asList("reload", "about", "list", "enable", "disable");
    private static final List<String> EVENTLIST = Arrays.asList("Entity", "Block", "Server", "Player");
    private static final List<String> EVENTLISTALL = Arrays.asList("Entity", "Block", "Server", "Player", "All");
    private static final List<String> BLANK = Arrays.asList("","");

    private List<String> configArray(String string) {
        ConfigurationSection list = EventSupport.getPlugin(EventSupport.class).getConfig().getConfigurationSection(string + " Events");
        List<String> listList = new ArrayList<>();
        for (String key : list.getKeys(false)) {
            boolean bool = list.getBoolean(key + ".Cancel");
            String newString = key.replace(" ", "_");
            listList.add(newString);
        }
        return listList;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("eventhandler")) {
            if(args.length <= 1) {
                return StringUtil.copyPartialMatches(args[0], COMMANDS, new ArrayList<>());
            } else if(args.length == 2) {
                if (args[0].equalsIgnoreCase("list")) {
                    return StringUtil.copyPartialMatches(args[1], EVENTLISTALL, new ArrayList<>());
                } else if(args[0].equalsIgnoreCase("enable")) {
                    return StringUtil.copyPartialMatches(args[1], EVENTLIST, new ArrayList<>());
                } else if(args[0].equalsIgnoreCase("disable")) {
                    return StringUtil.copyPartialMatches(args[1], EVENTLIST, new ArrayList<>());
                }
            } else if(args.length == 3) {
                if(args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable")) {
                    String arg = StringUtils.capitalize(args[1]);
                    return StringUtil.copyPartialMatches(args[2], configArray(arg), new ArrayList<>());
                }
            }
        }
        return BLANK;
    }

}
