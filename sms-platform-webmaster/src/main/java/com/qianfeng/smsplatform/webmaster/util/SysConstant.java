package com.qianfeng.smsplatform.webmaster.util;

public class SysConstant {

    public static final String CAPTCHA_KEY = "KAPTCHA";

    public static final String JOB_NAME_PREFIX = "myJob_";
    public static final String TRIGGER_NAME_PREFIX = "myTrigger_";

    public static final String SCHEDULE_JOB_KEY = "schedule_job_key";


    //枚举
    public enum ScheduleStatus {

        //定义两个常量 分别代表定时任务的两个状态  正常和暂停状态
        NOMAL((byte) 0),//正常
        PAUSE((byte) 1);//暂停

        private byte value;

        ScheduleStatus(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

}
