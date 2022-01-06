package dev.floffah.plugins.fancyarmorstatndutils.listener;

import dev.floffah.plugins.fancyarmorstatndutils.FancyArmorStandUtils;
import dev.floffah.plugins.fancyarmorstatndutils.book.StatueMainMenuBook;
import dev.floffah.plugins.fancyarmorstatndutils.namespace.NamespacedKeys;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class OpenStatueBookListener implements Listener {

    FancyArmorStandUtils plugin;

    public OpenStatueBookListener(FancyArmorStandUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (
            e.getAction().equals(Action.RIGHT_CLICK_AIR) ||
                e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
        ) {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            if (item != null && item.getType().equals(Material.WRITTEN_BOOK)) {
                BookMeta meta = (BookMeta) item.getItemMeta(); // not unsafe as material is checked and this material can have this meta type
                if (
                    item.getType().equals(Material.WRITABLE_BOOK) &&
                        !meta.hasTitle()
                ) return;
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                if (
                    pdc.has(
                        this.plugin.namespacedKeys.get(
                            NamespacedKeys.IS_STATUE_BOOK
                        ),
                        PersistentDataType.STRING
                    )
                ) {
                    e.setCancelled(true);
                    player.openBook(StatueMainMenuBook.getBook());
                }
            }
        }
    }
}
