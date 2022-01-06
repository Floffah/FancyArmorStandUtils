package dev.floffah.plugins.fancyarmorstatndutils;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.floffah.plugins.fancyarmorstatndutils.command.ArmorStandBookActionCommand;
import dev.floffah.plugins.fancyarmorstatndutils.listener.CreateBookListener;
import dev.floffah.plugins.fancyarmorstatndutils.listener.OpenStatueBookListener;
import dev.floffah.plugins.fancyarmorstatndutils.namespace.NamespacedKeyMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public final class FancyArmorStandUtils extends JavaPlugin {

    public NamespacedKeyMap namespacedKeys;

    public ThreadPoolExecutor cachedPool;
    public ScheduledThreadPoolExecutor scheduledPool;

    public ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        this.namespacedKeys = new NamespacedKeyMap(this);

        this.cachedPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.scheduledPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);

        this.getServer()
            .getPluginManager()
            .registerEvents(new CreateBookListener(this), this);
        this.getServer()
            .getPluginManager()
            .registerEvents(new OpenStatueBookListener(this), this);

        this.protocolManager = ProtocolLibrary.getProtocolManager();

        this.getCommand("armorstandbookaction").setExecutor(new ArmorStandBookActionCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
