package pers.alyssa.drinkmachine.protoctl;


import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import pers.alyssa.drinkmachine.Drink;
import pers.alyssa.utils.GetRandomString;

import java.io.IOException;

public class DrinkMachineResponsePacket {
    private Drink drink;

    private CommandSender sender;

    public MessageChain toMessageChain(){
        MessageChainBuilder mcb = new MessageChainBuilder();
        User user = sender.getUser();
        if(user != null) {
            mcb.append(new At(user.getId())).append("\n");
        }

        mcb.add("随着你向饮料机投入硬币，饮料机买饮料开始忙碌起来。\n" +
                "你可以听到机器内部的机械声和水流声，仿佛有一个小型工厂在生产你的饮料。\n" +
                "随着声音渐渐变得平稳，你的饮料从机器的饮料口中掉落下来。\n");
        mcb.append("你得到了").append(drink.getName()).append("\n")
                .append("一张纸条从滑落而出，上面写着：\n")
                .append("饮料编号：");
                if(drink.getId() == -1) {
                    mcb.add( GetRandomString.getRandomString(10,33,38) +"\n");
                }else {
                    mcb.append(String.valueOf(drink.getId())).append("\n");
                }
                mcb.append(drink.getDescription());

        return mcb.build();
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }
}
