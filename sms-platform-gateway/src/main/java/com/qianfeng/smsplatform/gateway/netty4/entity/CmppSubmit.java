package com.qianfeng.smsplatform.gateway.netty4.entity;

import com.qianfeng.smsplatform.gateway.netty4.Utils.Command;
import com.qianfeng.smsplatform.gateway.netty4.Utils.MsgUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;

/**
 * @author weixiang
 * @date 2018/8/2 10:28
 */
public class CmppSubmit extends CmppMessageHeader {

    int msgId = 0;

    byte pkTotal = 0;

    byte pkNumber = 0;

    byte registeredDelivery = 1;

    byte msgLevel = 0;

    String serviceId;// 10位长度

    byte feeUserType = 3;

    String feeTerminalId;// 21位长度

    byte feeTerminalType = 0;

    byte tp_pid = 0;

    byte tp_udhi = 0;

    /**
     0：ASCII串
     3：短信写卡操作
     4：二进制信息
     8：UCS2编码
     15：含GB汉字
     */
    byte msgFmt = 8;

    String msgSrc;// 6位长度

    /**
      02：对“计费用户号码”按条计信息费
      03：对“计费用户号码”按包月收取信息费
      04：对“计费用户号码”的信息费封顶
      05：对“计费用户号码”的收费是由SP实现
     */
    String feeType;// 2位长度

    String feeCode;// 6位长度

    String vaildTime = "";// 17位长度

    String atTime = "";// 17位长度

    String srcId;// 21位长度

    byte destUsrTl = 1;

    String destTerminalId;// 21位长度

    byte destTerminalType = 0;

    byte msgLength; // 1位长度

    byte[] msgContent;

    String linkId = "";// 保留字

    private int terminalIdLen;
    private int linkIdLen;
    private int submitExpMsgLen;

    public CmppSubmit(byte version, String serviceId, String srcId, int SequenceId,String mobile,String content) {
        super(Command.CMPP_SUBMIT, version);
        if (version == Command.CMPP2_VERSION) {
            terminalIdLen = 21;
            linkIdLen = 8;
            submitExpMsgLen = Command.CMPP2_SUBMIT_LEN_EXPMSGLEN;
        } else {
            terminalIdLen = 32;
            linkIdLen = 20;
            submitExpMsgLen = Command.CMPP3_SUBMIT_LEN_EXPMSGLEN;
        }

        this.serviceId = serviceId;
        this.feeTerminalId = "";
        this.feeType = "02";
        this.feeCode = "000000";
        this.srcId = srcId+"1630";
        this.msgFmt=(byte) 8;
        this.linkId = "";
        try {
            msgContent=content.getBytes("UTF-16BE");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.msgId=SequenceId;
        this.sequenceId=this.msgId;
        this.destTerminalId=mobile;
    }


    @Override
    public byte[] toByteArray(){

        ByteBuf buf = Unpooled.buffer(130 + 8 + ((byte) 1) * 21 + msgContent.length);
        buf.writeInt(130 + 8 + ((byte) 1) * 21 + msgContent.length);
        buf.writeInt(commandId);
        buf.writeInt(msgId);

        buf.writeLong(0);
        buf.writeByte(0);
        buf.writeByte(0);
        buf.writeByte(1);
        buf.writeByte(0);
        buf.writeBytes(MsgUtils.getLenBytes(serviceId, 10));
        buf.writeByte((byte) 2);
        buf.writeBytes(MsgUtils.getLenBytes("", 21));
        buf.writeByte(0);
        buf.writeByte(0);
        buf.writeByte(8);

        buf.writeBytes(MsgUtils.getLenBytes(serviceId, 6));
        buf.writeBytes(MsgUtils.getLenBytes(feeType, 2));
        buf.writeBytes(MsgUtils.getLenBytes(feeCode, 6));
        buf.writeBytes(MsgUtils.getLenBytes(vaildTime, 17));
        buf.writeBytes(MsgUtils.getLenBytes(atTime, 17));
        buf.writeBytes(MsgUtils.getLenBytes(srcId, 21));
        buf.writeByte((byte) 1);
        buf.writeBytes(MsgUtils.getLenBytes(destTerminalId, 21));
        buf.writeByte(msgContent.length);
        buf.writeBytes(msgContent);
        buf.writeLong((long) 0);

        return buf.array();
    }



}
