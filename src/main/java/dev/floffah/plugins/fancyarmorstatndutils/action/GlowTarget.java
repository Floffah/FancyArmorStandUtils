package dev.floffah.plugins.fancyarmorstatndutils.action;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import dev.floffah.plugins.fancyarmorstatndutils.FancyArmorStandUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import lombok.val;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GlowTarget {

    private static PacketContainer constructGlowPacket(
        FancyArmorStandUtils plugin,
        ArmorStand entity,
        byte metadataBitfield
    ) {
        PacketContainer packet = plugin.protocolManager.createPacket(
            PacketType.Play.Server.ENTITY_METADATA
        );
        packet.getIntegers().write(0, entity.getEntityId());

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(
            Byte.class
        );
        watcher.setEntity(entity);
        watcher.setObject(0, serializer, metadataBitfield);

        packet
            .getWatchableCollectionModifier()
            .write(0, watcher.getWatchableObjects());

        return packet;
    }

    public static void makeTargetGlow(
        FancyArmorStandUtils plugin,
        Player player
    ) {
        val nearby = player.getNearbyEntities(10, 10, 10);
        ArmorStand entity = null;

        for (Entity e : nearby) {
            if (e instanceof ArmorStand found) {
                entity = found;
                break;
            }
        }

        if (entity != null) {
            try {
                plugin.protocolManager.sendServerPacket(
                    player,
                    constructGlowPacket(plugin, entity, (byte) 0x40)
                );
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                player.sendMessage(
                    Component
                        .text(
                            "Could not make the nearest target glow, check console for details."
                        )
                        .color(NamedTextColor.RED)
                );
                return;
            }
            player.sendMessage(
                Component
                    .text("Highlighted the nearest armor stand")
                    .color(NamedTextColor.GREEN)
            );
            ArmorStand finalEntity = entity;
            plugin.scheduledPool.schedule(
                () -> {
                    try {
                        plugin.protocolManager.sendServerPacket(
                            player,
                            constructGlowPacket(
                                plugin,
                                finalEntity,
                                (byte) 0
                            )
                        );
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        player.sendMessage(
                            Component
                                .text(
                                    "Could not make the nearest target glow, check console for details."
                                )
                                .color(NamedTextColor.RED)
                        );
                    }
                },
                10,
                TimeUnit.SECONDS
            );
        } else {
            player.sendMessage(
                Component
                    .text("There was no armor stand nearby")
                    .color(NamedTextColor.RED)
            );
        }
    }
}
