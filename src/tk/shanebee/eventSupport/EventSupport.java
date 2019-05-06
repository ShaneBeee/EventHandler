package tk.shanebee.eventSupport;

import org.bukkit.ChatColor;
import org.bukkit.World;
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
        String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + pdfFile.getName() +
                ChatColor.GRAY + "]";
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
        this.getCommand("eventhandler").setTabCompleter(new TabCompleter(this));
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        String PluginNameAndVersion = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + pdfFile.getName() +
                ChatColor.DARK_GRAY + "]" + ChatColor.BLUE + " Disabled " + pdfFile.getName() + ChatColor.GRAY + " v" + pdfFile.getVersion()
                + ChatColor.RESET;
        getServer().getConsoleSender().sendMessage(PluginNameAndVersion);
    }

    /**
     * Checks if this server is running an instance of Paper or not
     * @return True if server is running Paper
     */
    static boolean isRunningPaper() {
        try {
            Class.forName("co.aikar.timings.Timing");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    public boolean hasWorld(String path, World world) {
        if (config.getList(path) == null) {
            return false;
        }
        return config.getList(path).contains(world.getName()) || config.getList(path).contains("all");
    }
}
