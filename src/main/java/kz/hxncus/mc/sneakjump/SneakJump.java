package kz.hxncus.mc.sneakjump;

import kz.hxncus.mc.sneakjump.command.SneakJumpCommand;
import kz.hxncus.mc.sneakjump.config.Config;
import kz.hxncus.mc.sneakjump.cooldown.CooldownService;
import kz.hxncus.mc.sneakjump.listener.PlayerListener;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SneakJump extends JavaPlugin {
    private CooldownService cooldownService;
    private Config config;

    @Override
    public void onEnable() {
        setupConfigs();
        setupServices();
        setupListeners();
        setupCommands();
        setupMetrics();
        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled");
    }

    private void setupConfigs() {
        this.saveDefaultConfig();
        this.config = new Config(this);
    }

    private void setupServices() {
        this.cooldownService = new CooldownService(config.getCooldown());
    }

    private void setupListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this, config, cooldownService), this);
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
        metrics.addCustomChart(
            new SimplePie("gravity", () -> config.getGravity() + " blocks per tick")
        );
        metrics.addCustomChart(
            new SimplePie("multiplier", () -> config.getMultiplier() + " multiplier")
        );
        metrics.addCustomChart(
            new SimplePie("allowedWorlds", () -> config.getAllowedWorlds().toString())
        );
        metrics.addCustomChart(
            new SimplePie("enabled", () -> config.isEnabled() ? "true" : "false")
        );
    }
}
