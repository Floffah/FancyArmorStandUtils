package dev.floffah.plugins.fancyarmorstatndutils.book;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class BookInfoIcon {

    public static Component bookInfo(Component message) {
        return Component
            .text("ⓘ")
            .color(NamedTextColor.DARK_PURPLE)
            .hoverEvent(HoverEvent.showText(message));
    }
}
