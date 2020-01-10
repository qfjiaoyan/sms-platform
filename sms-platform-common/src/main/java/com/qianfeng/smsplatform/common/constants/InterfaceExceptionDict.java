package com.qianfeng.smsplatform.common.constants;

/**
 * Created by Administrator on 2019/5/17.
 */
public class InterfaceExceptionDict {

    //成功
    public static final String RETURN_STATUS_SUCCESS = "0";

    //认证错：clientId错误
    public static final String RETURN_STATUS_CLIENTID_ERROR = "101";

    //密码错误
    public static final String RETURN_STATUS_PWD_ERROR = "102";

    //IP校验错误
    public static final String RETURN_STATUS_IP_ERROR = "103";

    //消息长度错，为空或超长（目前定为500个字）
    public static final String RETURN_STATUS_MESSAGE_ERROR = "104";

    //手机号错误
    public static final String RETURN_STATUS_MOBILE_ERROR = "105";

    //下行编号（srcID）错误
    public static final String RETURN_STATUS_SRCID_ERROR = "106";

}
