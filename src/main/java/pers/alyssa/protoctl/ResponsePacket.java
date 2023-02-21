package pers.alyssa.protocol;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.data.MessageChain;

import java.io.IOException;

public interface ResponsePacket {
    MessageChain toMessageChain() throws IOException;

    Bot getOperatorBor();
    void setOperatorBor(Bot operatorBor);
}
