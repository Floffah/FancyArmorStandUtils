package dev.floffah.plugins.fancyarmorstatndutils.book;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class StatueMainMenuBook {

    public static Book getBook() {
        return DefaultBook
            .getDefault()
            .pages(
                Component
                    .text("     Armor Stands ")
                    .color(NamedTextColor.BLUE)
                    .append(
                        BookInfoIcon.bookInfo(
                            Component
                                .text("Anytime you see a purple info icon ")
                                .color(NamedTextColor.GREEN)
                                .append(
                                    Component
                                        .text("ⓘ")
                                        .color(NamedTextColor.DARK_PURPLE)
                                )
                                .append(
                                    Component
                                        .text(
                                            ", this will show you information about how to use something in this book"
                                        )
                                        .color(NamedTextColor.GREEN)
                                )
                        )
                    )
                    .append(
                        Component
                            .text("\n\n      Check target\n\n")
                            .color(NamedTextColor.GREEN)
                            .clickEvent(
                                ClickEvent.runCommand(
                                    "/armorstandbookaction mainMenuBook checkTarget"
                                )
                            )
                            .hoverEvent(
                                HoverEvent.showText(
                                    DefaultComponents.runCommandHoverComponent(
                                        "armorstandbookaction mainMenuBook checkTarget"
                                    )
                                )
                            )
                    )
                    .append(
                        Component
                            .text("          Options")
                            .color(NamedTextColor.DARK_AQUA)
                    )
            )
            .build();
    }
}
