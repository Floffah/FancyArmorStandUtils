package dev.floffah.plugins.fancyarmorstatndutils.namespace;

import dev.floffah.plugins.fancyarmorstatndutils.FancyArmorStandUtils;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.NamespacedKey;

public class NamespacedKeyMap {

    Map<String, NamespacedKey> keys;
    FancyArmorStandUtils plugin;

    public NamespacedKeyMap(FancyArmorStandUtils pl) {
        keys = new HashMap<String, NamespacedKey>();
        plugin = pl;
    }

    public NamespacedKey get(String name) {
        if (keys.containsKey(name)) {
            return keys.get(name);
        } else {
            NamespacedKey key = new NamespacedKey(plugin, name);
            keys.put(name, key);
            return key;
        }
    }
}
