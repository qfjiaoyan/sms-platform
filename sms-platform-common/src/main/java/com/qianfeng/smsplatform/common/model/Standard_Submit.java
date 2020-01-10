package com.qianfeng.smsplatform.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public @Data
class Standard_Submit implements Serializable {
    /**
     * 客户端ID
     */
    private int clientID;
    /**
     * 下发的源号码
     */
    private String srcNumber;
    /**
     * 消息的优先级 0 最低 --- 3 最高
     */
    private short messagePriority;
    /**
     * 客户侧唯一序列号
     */
    private long srcSequenceId;
    /**
     * 下发网关ID号
     */
    private int gatewayID;
    /**
     * 产品编号
     */
    private int productID;
    /**
     * 目的手机号 （小于100个用户）
     */
    private String destMobile;
    /** 消息的长度 */
//	private short messageLength;
    /**
     * 短信内容
     */
    private String messageContent;
    /**
     * 响应返回的运营商消息编号,
     */
    private String msgid;
    /**
     * 手机接收的状态值  0 成功 1 等待 2 失败
     */
    private int reportState;
    /**
     * 状态的错误码
     */
    private String errorCode;
    /**
     * 短信发送时间
     */
    private Date sendTime;
    /**
     * 运营商
     */
    private Integer operatorId;
    /**
     * 省
     */
    private Integer provinceId;
    /**
     * 市
     */
    private Integer cityId;
    /**
     * 发送方式 1 HTTP 2 WEB
     */
    private Integer source;
}
