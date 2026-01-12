package kz.hxncus.mc.sneakjump.cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * CooldownService part of the SneakJump Minecraft plugin.
 *
 * @author Hxncus
 * @since 1.1.0
 */
public class CooldownService {
    private final Map<UUID, Long> cooldowns;
    private final long cooldown;

    public CooldownService(long cooldown) {
        this.cooldowns = new HashMap<>();
        this.cooldown = cooldown;
    }

    public boolean isOnCooldown(UUID uuid) {
        return cooldowns.containsKey(uuid) && cooldowns.get(uuid) >= System.currentTimeMillis();
    }

    public void setCooldown(UUID uuid) {
        cooldowns.put(uuid, System.currentTimeMillis() + cooldown);
    }

    public void removeCooldown(UUID uuid) {
        cooldowns.remove(uuid);
    }

    public long getCooldown(UUID uuid) {
        return cooldowns.getOrDefault(uuid, System.currentTimeMillis()) - System.currentTimeMillis();
    }
}
