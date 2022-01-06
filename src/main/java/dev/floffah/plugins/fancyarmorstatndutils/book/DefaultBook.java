package dev.floffah.plugins.fancyarmorstatndutils.book;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class DefaultBook {
    public static Book.Builder getDefault() {
        return Book.builder()
                .title(Component.text("Armor Stands"))
                .author(Component.text("Floffah"));
    }
}
