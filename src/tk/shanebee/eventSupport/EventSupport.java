package tk.shanebee.eventSupport;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import tk.shanebee.eventSupport.events.*;
import tk.shanebee.eventSupport.metrics.Metrics;

public class EventSupport extends JavaPlugin {
    private FileConfiguration config = this.getConfig();


    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this);

        PluginDescriptionFile pdfFile = getDescription();
        String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + pdfFile.getName() +
                ChatColor.DARK_GRAY + "]";
        String version = ChatColor.GRAY + " v" + pdfFile.getVersion();
        String name = pdfFile.getName();

        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GREEN + " Enabled " + name + version);
        if(!isRunningPaper()) {
            getServer().getConsoleSender().sendMessage(prefix + ChatColor.LIGHT_PURPLE + " If you run Paper, more events will be available");
        }

        Configuration.loadConfig(config);
        saveConfig();

        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        getServer().getPluginManager().registerEvents(new EntityEvents(this), this);
        getServer().getPluginManager().registerEvents(new BlockEvents(this), this);
        getServer().getPluginManager().registerEvents(new ServerEvents(this), this);
        if(isRunningPaper()) {
            getServer().getPluginManager().registerEvents(new PaperEvents(this), this);
        }
        this.getCommand("eventhandler").setExecutor(new Commands(this));
        this.getCommand("eventhandler").setTabCompleter(new TabCompleter());
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        String PluginNameAndVersion = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + pdfFile.getName() +
                ChatColor.DARK_GRAY + "]" + ChatColor.BLUE + " Disabled " + pdfFile.getName() + ChatColor.GRAY + " v" + pdfFile.getVersion()
                + ChatColor.RESET;
        getServer().getConsoleSender().sendMessage(PluginNameAndVersion);
    }

    public static boolean isRunningPaper() {
        try {
            Class.forName("co.aikar.timings.Timing");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }
}
