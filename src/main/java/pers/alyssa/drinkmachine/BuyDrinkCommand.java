package pers.alyssa.drinkmachine;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.*;
import org.jetbrains.annotations.NotNull;
import pers.alyssa.drinkmachine.fileIO.DrinkMachineData;
import pers.alyssa.drinkmachine.protoctl.DrinkMachineResponsePacket;
import pers.alyssa.utils.IsDigit;

import java.util.Random;

public class BuyDrinkCommand extends JRawCommand {

    public static final BuyDrinkCommand INSTANCE = new BuyDrinkCommand();

    private BuyDrinkCommand() {
        super(DrinkMachine.INSTANCE, "买饮料");
        setDescription(
                "有台有能选择饮品的触摸式屏幕,和塞钱的投币口。\n" +
                "忽然，似乎是感应到了顾客出现，屏幕上显现出了几行字。\n" +
                "随便输个1到99的序号，或者什么都不输，再随便给我枚长得像硬币或就是硬币的东西,我就能卖你个有意思的玩意，再附赠段介绍!反正库存正好也剩99件，售完即止，欲购从速!\n");
        setUsage( "买饮料 #随机获得一杯饮品\n" +
                  "买饮料 [1-99] #获得一杯指定编号的饮品\n");
        setPrefixOptional(true);

    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        int i = getIndex(args);
        System.out.println("index: " + i);
        System.out.println(args);
        Drink drink = DrinkMachineData.INSTANCE.getDrink(getIndex(args));
        DrinkMachineResponsePacket responsePacket = new DrinkMachineResponsePacket();
        responsePacket.setSender(sender);
        responsePacket.setDrink(drink);
        Contact contact = sender.getSubject();
        if(contact != null) {
            sender.getSubject().sendMessage(responsePacket.toMessageChain());
        } else {
            sender.sendMessage(responsePacket.toMessageChain());
        }
    }

    public int getIndex(MessageChain args) {
        SingleMessage msg = args.get(MessageContent.Key);
        String str;
        if(msg == null) {
            str = null;
        } else {
            str = msg.contentToString();
        }
        int index;
        if(str != null && IsDigit.isDigit(str)) {
            index = Integer.parseInt(str);
            return index > DrinkMachineData.DRINKS_MAP.size() || index < 1 ? -1 : index;
        } else {
            Random r = new Random(System.currentTimeMillis());
            return Math.abs(r.nextInt(DrinkMachineData.DRINKS_MAP.size()) + 1);
        }
    }
}
