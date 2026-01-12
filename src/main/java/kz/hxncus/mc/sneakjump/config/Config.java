package kz.hxncus.mc.sneakjump.config;

import kz.hxncus.mc.sneakjump.SneakJump;
import net.md_5.bungee.api.ChatMessageType;
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
    private List<String> helpMessages;

    public Config(SneakJump plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void reload() {
        this.plugin.reloadConfig();
        this.config = plugin.getConfig();
        this.allowedWorlds = null;
        this.helpMessages = null;
    }

    public boolean isEnabled() {
        return config.getBoolean("enabled", true);
    }

    public double getGravity() {
        return config.getDouble("gravity", 0.0882);
    }

    public int getJumpHeight() {
        return config.getInt("jump-height", 2);
    }

    public double getMultiplier() {
        return config.getDouble("multiplier", 0.997);
    }

    public long getCooldown() {
        return config.getLong("cooldown", 3000);
    }

    public ChatMessageType getCooldownMessageType() {
        try {
            return ChatMessageType.valueOf(config.getString("cooldown-message-type"));
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    public String getCooldownMessage() {
        return config.getString("cooldown-message");
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
        if (helpMessages == null) {
            helpMessages = config.getStringList("help-messages");
        }
        return helpMessages;
    }
}