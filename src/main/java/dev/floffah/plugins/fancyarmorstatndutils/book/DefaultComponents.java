package dev.floffah.plugins.fancyarmorstatndutils.book;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class DefaultComponents {

    public static Component runCommandHoverComponent(String command) {
        return Component
            .text("Runs command: /")
            .append(
                Component.text(command).color(TextColor.color(11, 117, 230))
            );
    }
}
