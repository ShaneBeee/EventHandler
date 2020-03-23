package tk.shanebee.eventSupport.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {

    /**
     * Log message to console
     * <p>Prefixed with the plugin name</p>
     *
     * @param log Message to log
     */
    public static void log(String log) {
        String prefix = "&7[&bEvent&3Handler&7] ";
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + log));
    }

    public static void scm(CommandSender receiver, String message) {
        if (message == null) return;
        receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Checks if this server is running an instance of Paper
     *
     * @return True if server is running Paper
     */
    public static boolean isRunningPaper() {
        try {
            Class.forName("co.aikar.timings.Timing");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    /** Check if a class exists
     * @param className The {@link Class#getCanonicalName() canonical name} of the class
     * @return True if the class exists
     */
    public static boolean classExists(final String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

}
