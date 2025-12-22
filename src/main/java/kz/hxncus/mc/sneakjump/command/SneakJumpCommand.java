package kz.hxncus.mc.sneakjump.command;

import kz.hxncus.mc.sneakjump.SneakJump;
import kz.hxncus.mc.sneakjump.config.Config;
import lombok.NonNull;
import org.bukkit.command.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SneakJumpCommand part of the SneakJump Minecraft plugin.
 *
 * @author Hxncus
 * @since 1.0.0
 */
public class SneakJumpCommand implements TabExecutor {

    private final SneakJump plugin;
    private final Config config;

    public SneakJumpCommand(SneakJump plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (args.length == 0 || "help".equalsIgnoreCase(args[0])) {
            for (String message : config.getHelpMessages()) {
                sender.sendMessage(message.replace("{0}", label));
            }
            return true;
        } else if ("reload".equalsIgnoreCase(args[0])) {
            config.reload();
            sender.sendMessage(config.getReloadMessage());
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String alias, String @NonNull [] args) {
        if (args.length == 1) {
            return Arrays.asList("help", "reload");
        }
        return Collections.emptyList();
    }
}