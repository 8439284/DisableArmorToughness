package org.ajls.disableArmorToughness;

import org.bukkit.plugin.java.JavaPlugin;

public final class DisableArmorToughness extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MyListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
