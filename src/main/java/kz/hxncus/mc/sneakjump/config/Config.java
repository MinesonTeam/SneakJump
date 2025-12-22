package kz.hxncus.mc.sneakjump.config;

import kz.hxncus.mc.sneakjump.SneakJump;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Config part of the SneakJump Minecraft plugin.
 *
 * @author Hxncus
 * @since 1.0.0
 */
public class Config {
    private final SneakJump plugin;
    private FileConfiguration config;
    private Set<String> allowedWorlds;

    public Config(SneakJump plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void reload() {
        this.plugin.reloadConfig();
        this.config = plugin.getConfig();
        this.allowedWorlds = null;
    }

    public boolean isEnabled() {
        return config.getBoolean("enabled", true);
    }

    public double getGravity() {
        return config.getDouble("gravity", 0.8);
    }

    public int getJumpHeight() {
        return config.getInt("jump-height", 2);
    }

    public double getMultiplier() {
        return config.getDouble("multiplier", 0.99);
    }

    public Set<String> getAllowedWorlds() {
        if (allowedWorlds == null) {
            allowedWorlds = new HashSet<>(config.getStringList("allowed-worlds"));
        }
        return allowedWorlds;
    }

    public String getPermission() {
        return config.getString("permission", "sneakjump.jump");
    }

    public String getReloadMessage() {
        return config.getString("reload-message");
    }

    public List<String> getHelpMessages() {
        return config.getStringList("help-messages");
    }
}