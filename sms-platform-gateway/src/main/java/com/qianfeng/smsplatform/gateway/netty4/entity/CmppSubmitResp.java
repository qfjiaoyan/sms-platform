package com.qianfeng.smsplatform.gateway.netty4.entity;


import com.qianfeng.smsplatform.gateway.netty4.Utils.MsgUtils;
import org.apache.commons.lang3.ArrayUtils;


/**
 * @author weixiang
 * @date 2018/8/2 15:23
 */
public class CmppSubmitResp {

    private long msgId;

    private int state;

    private long msgId2;

    public CmppSubmitResp(byte[] bytes){
        this.msgId= MsgUtils.bytesToInt(ArrayUtils.subarray(bytes, 8, 12));
        this.msgId2=MsgUtils.bytesToLong(ArrayUtils.subarray(bytes, 12, 20));
        this.msgId2=Math.abs(this.msgId2);
        this.state=bytes[20];
    }

    public int getState() {
        return state;
    }

    public long getMsgId2() {
        return msgId2;
    }

    public long getMsgId() {

        return msgId;
    }

}
