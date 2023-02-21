package pers.alyssa.drinkmachine;


import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import pers.alyssa.protocol.ResponsePacket;
import pers.alyssa.utils.GetRandomString;

import java.io.IOException;

public class DrinkMachineResponsePacket implements ResponsePacket {
    private Drink drink;

    private Member sender;

    private Bot operatorBor;

    @Override
    public MessageChain toMessageChain() throws IOException {
        MessageChainBuilder mcb = new MessageChainBuilder();
        mcb.append(new At(sender.getId())).append("\n");

        mcb.add("当你向饮料机投入硬币，按下选项按钮时，饮料机开始忙碌起来。\n" +
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

    @Override
    public Bot getOperatorBor() {
        return operatorBor;
    }

    @Override
    public void setOperatorBor(Bot operatorBor) {
        this.operatorBor = operatorBor;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public Member getSender() {
        return sender;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }
}
