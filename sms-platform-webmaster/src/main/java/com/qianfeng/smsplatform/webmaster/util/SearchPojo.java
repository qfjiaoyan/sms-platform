package com.qianfeng.smsplatform.webmaster.util;

import lombok.Data;

/**
 * @author menglili
 * 调用搜索服务用到的对象，与搜索服务中的数据格式对应
 */
@Data
public class SearchPojo {
    private String keyword;
    private Integer clientID;
    private String mobile;
    private Long startTime;
    private Long endTime;

    private Integer start;
    private Integer rows;
    private String highLightPreTag;
    private String highLightPostTag;

    private String messageContent;

}
