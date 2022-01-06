package dev.floffah.plugins.fancyarmorstatndutils.command;

import dev.floffah.plugins.fancyarmorstatndutils.FancyArmorStandUtils;
import dev.floffah.plugins.fancyarmorstatndutils.action.GlowTarget;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArmorStandBookActionCommand implements CommandExecutor {
    public FancyArmorStandUtils plugin;

    public ArmorStandBookActionCommand(FancyArmorStandUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length > 0) {
            if (args[0].equals("mainMenuBook")) {
                if (args.length > 1) {
                    if (args[1].equals("checkTarget")) {
                        player.sendMessage(Component.text("Highlighting armor stand if available").color(NamedTextColor.GREEN));
                        GlowTarget.makeTargetGlow(this.plugin, player);
                    }
                }
            }
        }

        return true;
    }
}
