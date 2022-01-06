package dev.floffah.plugins.fancyarmorstatndutils.listener;

import dev.floffah.plugins.fancyarmorstatndutils.FancyArmorStandUtils;
import dev.floffah.plugins.fancyarmorstatndutils.items.TriggerBook;
import java.util.Objects;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

public class CreateBookListener implements Listener {

    FancyArmorStandUtils plugin;

    public CreateBookListener(FancyArmorStandUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSign(PlayerEditBookEvent e) {
        if (!e.isSigning()) return;
        BookMeta book = e.getNewBookMeta();
        if (Objects.equals(book.getTitle(), "Statue")) {
            Player player = e.getPlayer();
            PlayerInventory playerInv = player.getInventory();

            e.setCancelled(true);
            int indexToReplace = playerInv.getHeldItemSlot(); // to make it thread safe
            this.plugin.cachedPool.execute(() -> {
                    // it has to be executed like this because if it is set in the event then
                    // although the event is cancelled the item is a phantom writable book as it
                    // appears as the right item but as soon as you use it or move it, it turns
                    // back into a writable book
                    playerInv.setItem(
                        indexToReplace,
                        new TriggerBook(this.plugin)
                    );
                });
        }
    }
}
