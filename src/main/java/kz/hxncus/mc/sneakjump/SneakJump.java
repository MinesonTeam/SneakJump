package kz.hxncus.mc.sneakjump;

import kz.hxncus.mc.sneakjump.command.SneakJumpCommand;
import kz.hxncus.mc.sneakjump.config.Config;
import kz.hxncus.mc.sneakjump.listener.PlayerListener;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SneakJump extends JavaPlugin {
    private Config config;

    @Override
    public void onEnable() {
        setupConfigs();
        setupListeners();
        setupCommands();
        setupMetrics();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupConfigs() {
        this.saveDefaultConfig();
        config = new Config(this);
    }

    private void setupListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this, config), this);
    }

    private void setupCommands() {
        PluginCommand command = this.getCommand("sneakjump");
        if (command != null) {
            SneakJumpCommand sneakJumpCommand = new SneakJumpCommand(this, config);
            command.setExecutor(sneakJumpCommand);
            command.setTabCompleter(sneakJumpCommand);
        }
    }

    private void setupMetrics() {
        int pluginId = 28316;
        Metrics metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(
            new SimplePie("jumpHeight", () -> config.getJumpHeight() + " blocks")
        );
    }
}
