package com.qianfeng.smsplatform.webmaster.dto;

import lombok.Data;

@Data
public class SmsStatusDTO {
    private Integer clientID;
    private Long startTime;
    private Long endTime;
}
