package pers.alyssa.drinkmachine;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class DrinkMachine extends JavaPlugin {
    public static final DrinkMachine INSTANCE = new DrinkMachine();

    private DrinkMachine() {
        super(new JvmPluginDescriptionBuilder("pers.alyssa.drink-machine", "1.0.1")
                .name("DrinkMachine")
                .author("MuLei_SY")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
    }
}