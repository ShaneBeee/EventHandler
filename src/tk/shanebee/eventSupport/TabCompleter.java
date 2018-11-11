package tk.shanebee.eventSupport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    private static final List<String> COMMANDS = Arrays.asList("reload", "about");
    private static final List<String> BLANK = Arrays.asList("", "");

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("eventhandler")) {
            if (args.length <= 1) {
                return StringUtil.copyPartialMatches(args[0], COMMANDS, new ArrayList<>());
            }
        } return BLANK;
    }
}
