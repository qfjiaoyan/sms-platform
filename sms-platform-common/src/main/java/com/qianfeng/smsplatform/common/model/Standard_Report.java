package com.qianfeng.smsplatform.common.model;

import lombok.Data;

import java.io.Serializable;

public @Data
class Standard_Report implements Serializable {
    /**
     * 目的号码
     */
    private String mobile;

    /**
     * 定长4个字节 发送状态 0 发送成功 1 等待发送 2 发送失败 (只适用于移动联通)
     */
    private int state;

    /**
     * 具体发送状态 (详细见移动联通电信协议)
     */
    private String errorCode;

    /**
     * 客户侧唯一序列号
     */
    private long srcID;
    /**
     * 客户ID
     */
    private long clientID;
    /**
     * 响应返回的运营商消息编号,
     */
    private String msgId;
    /**状态报告推送次数*/
    private int sendCount;
}
