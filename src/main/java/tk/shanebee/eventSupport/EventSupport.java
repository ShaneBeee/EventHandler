package tk.shanebee.eventSupport;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tk.shanebee.eventSupport.events.BlockEvents;
import tk.shanebee.eventSupport.events.EntityEvents;
import tk.shanebee.eventSupport.events.PaperEvents;
import tk.shanebee.eventSupport.events.PlayerEvents;
import tk.shanebee.eventSupport.events.ServerEvents;
import tk.shanebee.eventSupport.metrics.Metrics;
import tk.shanebee.eventSupport.util.Util;

@SuppressWarnings("ConstantConditions")
public class EventSupport extends JavaPlugin {

    private final FileConfiguration CONFIG = this.getConfig();
    private Configuration configuration;

    @Override
    public void onEnable() {
        new Metrics(this);

        if (!Util.isRunningPaper()) {
            Util.log("&d If you run Paper, more events will be available");
        }
        this.configuration = new Configuration(this);
        this.configuration.loadConfig();
        registerEvents();
        Util.log("&aSuccessfully enabled!!!");
    }

    @Override
    public void onDisable() {
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        getServer().getPluginManager().registerEvents(new EntityEvents(this), this);
        getServer().getPluginManager().registerEvents(new BlockEvents(this), this);
        getServer().getPluginManager().registerEvents(new ServerEvents(this), this);
        if (Util.isRunningPaper()) {
            getServer().getPluginManager().registerEvents(new PaperEvents(this), this);
        }
        this.getCommand("eventhandler").setExecutor(new Commands(this));
        this.getCommand("eventhandler").setTabCompleter(new TabCompleter(this));
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean hasWorld(String path, World world) {
        if (this.CONFIG.getList(path) == null) {
            return false;
        }
        return this.CONFIG.getList(path).contains(world.getName()) || this.CONFIG.getList(path).contains("all");
    }

}
