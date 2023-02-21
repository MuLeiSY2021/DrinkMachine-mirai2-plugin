package pers.alyssa.drinkmachine;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import pers.alyssa.drinkmachine.fileIO.DrinkMachineData;

import java.io.IOException;

public final class DrinkMachine extends JavaPlugin {
    public static final String MAIN_DRINK_MACHINE_ASSETS_DIR = "alyssa/assets/drinkmachine";

    public static final DrinkMachine INSTANCE = new DrinkMachine();

    private DrinkMachine() {
        super(new JvmPluginDescriptionBuilder("pers.alyssa.drink-machine", "1.1.4")
                .name("DrinkMachine")
                .author("MuLei_SY")
                .build());
    }

    @Override
    public void onEnable() {
        try {
            DrinkMachineData.loadDrink();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommandManager.INSTANCE.registerCommand(BuyDrinkCommand.INSTANCE,false);
        getLogger().info("DrinkMachine Plugin loaded!");


    }
}