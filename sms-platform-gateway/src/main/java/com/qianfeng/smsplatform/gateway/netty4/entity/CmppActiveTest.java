package com.qianfeng.smsplatform.gateway.netty4.entity;


import com.qianfeng.smsplatform.gateway.netty4.Utils.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author weixiang
 * @date 2018/8/2 15:06
 */
public class CmppActiveTest extends CmppMessageHeader {

    public CmppActiveTest() {
        super(Command.CMPP_ACTIVE_TEST, Command.CMPP2_VERSION);
    }

    /**
     * 实现类必须自定义对象序列化
     *
     * @return
     */
    @Override
    public byte[] toByteArray() {
        ByteBuf buf = Unpooled.buffer(12);
        buf.writeInt(12);
        buf.writeInt(Command.CMPP_ACTIVE_TEST);
        buf.writeInt(0);
        return buf.array();
    }
}
