package tk.shanebee.eventSupport;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tk.shanebee.eventSupport.events.*;
import tk.shanebee.eventSupport.metrics.Metrics;

public class EventSupport extends JavaPlugin {

    private FileConfiguration config = this.getConfig();
    private String version = ChatColor.GRAY + " v" + getDescription().getVersion();
    private String name = getDescription().getName();
    private String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + name + ChatColor.GRAY + "]";

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this);

        sendConsoleMessage(prefix + "&2 Enabled " + name + version);
        if(!isRunningPaper()) {
            sendConsoleMessage(prefix + "&d If you run Paper, more events will be available");
        }
        Configuration.loadConfig(config);
        saveConfig();
        registerEvents();
    }

    @Override
    public void onDisable() {
        sendConsoleMessage(prefix + "&1 Disabled " + getDescription().getName() + version);
    }

    /**
     * Checks if this server is running an instance of Paper
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

    private void sendConsoleMessage(String message) {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    private void registerEvents() {
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

    public boolean hasWorld(String path, World world) {
        if (config.getList(path) == null) {
            return false;
        }
        return config.getList(path).contains(world.getName()) || config.getList(path).contains("all");
    }
}
