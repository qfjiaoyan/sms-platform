package com.qianfeng.smsplatform.gateway.netty4.entity;

import java.io.Serializable;

/**
 * @author weixiang
 * @date 2018/8/2 10:18
 */
public abstract class CmppMessageHeader implements Serializable{

    /** 消息总长度(含消息头及消息体) */
    protected int totalLength;

    /** 命令标识 */
    protected int commandId;

    /** 消息流水号，顺序累加，步长为1，循环使用（一对请求和应答消息的流水号必须相同） */
    protected int sequenceId;

    /** CMPP版本 2.0或 3.0 */
    protected byte version;



    protected CmppMessageHeader(int commandId, byte version) {
        this.commandId = commandId;
        this.version = version;
    }

    /**
     * 实现类必须自定义对象序列化
     * @return
     */
    public abstract byte[] toByteArray();



}

