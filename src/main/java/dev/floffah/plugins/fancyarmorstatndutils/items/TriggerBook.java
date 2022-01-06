package dev.floffah.plugins.fancyarmorstatndutils.items;

import dev.floffah.plugins.fancyarmorstatndutils.FancyArmorStandUtils;
import dev.floffah.plugins.fancyarmorstatndutils.namespace.NamespacedKeys;
import dev.floffah.plugins.fancyarmorstatndutils.namespace.NamespacedKeysValues;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class TriggerBook extends ItemStack {

    public TriggerBook(FancyArmorStandUtils plugin) {
        super(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) this.getItemMeta(); // not unsafe as the material is set to written book

        meta
            .title(Component.text("Armor Stands").color(NamedTextColor.YELLOW))
            .author(Component.text("Floffah"))
            .pages(
                Component.text(
                    "If you are seeing this either the server is lagging or armor plugin broken"
                )
            );

        meta.setGeneration(BookMeta.Generation.ORIGINAL);

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(
            plugin.namespacedKeys.get(NamespacedKeys.IS_STATUE_BOOK),
            PersistentDataType.STRING,
            NamespacedKeysValues.IS_STATUE_BOOK.SIGNED
        );

        this.setItemMeta(meta);
    }
}
