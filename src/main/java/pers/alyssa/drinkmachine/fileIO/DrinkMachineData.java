package pers.alyssa.drinkmachine;

import com.google.gson.Gson;
import net.mamoe.mirai.console.data.Value;
import net.mamoe.mirai.console.data.java.JavaAutoSavePluginData;
import net.mamoe.mirai.console.plugin.PluginFileExtensions;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrinkMachineData extends JavaAutoSavePluginData {
    public static final DrinkMachineData INSTANCE = new DrinkMachineData("drinkMachine");

    public static final Value<HashMap<Integer,Drink>> DRINKS_MAP = INSTANCE.typedValue("DRINKS_MAP",createKType(HashMap.class,createKType(Integer.class), createKType(Drink.class)));

    public DrinkMachineData(@NotNull String saveName) {
        super(saveName);
    }



    public static void juiceToJson() throws IOException {
        String packetPath = "src/main/resources/alyssa/assets/drinkmachine";
        LinkedList<Drink> drinks = new LinkedList<>();
        Drink drink = null;
        StringBuilder sb = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(DrinkMachine.INSTANCE.getResourceAsStream(packetPath + "/drinks.txt")));
        boolean flag = false;
        String patten_str = "\\b-*[1-9][0-9]?\\.";
        Pattern pattern = Pattern.compile(patten_str);
        while (reader.ready()){
            String line = reader.readLine();
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()) {
                if(drink != null) {
                    try {
                        sb.delete(sb.length() - 2, sb.length());
                        drink.setDescription(sb.toString());
                        drinks.add(drink);
                    } catch (Exception e){
                        System.out.println(drink.getId());
                    }
                }

                sb = new StringBuilder();
                String id = matcher.group(0);
                String name = line.substring(id.length());
                id = id.substring(0,id.length() - 1);
                drink = new Drink(Integer.parseInt(id),name);
                flag = true;
            } else {
                if (line.isEmpty() && !flag) {
                    sb.append('\n');
                } else {
                    sb.append(line).append("\n");
                }
                flag = false;
            }
            drink.setDescription(sb.toString());

        }
        if(drink != null) {
            sb.delete(sb.length() - 2, sb.length());
            drink.setDescription(sb.toString());
            drinks.add(drink);
        }
        File jsonFile = new File(DrinkMachine.INSTANCE.getDataFolderPath() + "/drinks.json");
        if(!jsonFile.exists()) {
            jsonFile.createNewFile();
        }
        Gson gson = new Gson();
        BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(jsonFile.toPath()));
        outputStream.write(gson.toJson(drinks).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public static void loadDrink() throws IOException {
        Gson gson = new Gson();
        Drink[] drinks = gson.fromJson(new InputStreamReader(DrinkMachine.INSTANCE.getResourceAsStream(DrinkMachine.MAIN_DRINK_MACHINE_ASSETS_DIR + "/drinks.json")), Drink[].class);
        for (Drink drink : drinks) {
            DrinkMachineData.DRINKS_MAP.get().put(drink.getId(), drink);
        }
    }

    public static void main(String[] args) throws IOException {
        juiceToJson();
//        loadDrink();
    }
}
