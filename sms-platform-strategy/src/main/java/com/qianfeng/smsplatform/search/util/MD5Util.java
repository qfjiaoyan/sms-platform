package com.qianfeng.smsplatform.search.util;

import org.springframework.util.DigestUtils;

import java.io.IOException;

public class MD5Util {
    /**
     * MD5方法
     *
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) {
        //加密后的字符串
        String encodeStr = DigestUtils.md5DigestAsHex(text.getBytes());
        System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
        return encodeStr;
    }

    public static void main(String[] args) throws IOException {
        String str = "中车青岛四方车辆研究所有限公司副总经理陈凯表示，“青岛地铁6号线的智能化程度是目前国内最好的，甚至可以" +
                "说是世界一流水平，加上系统全自主化研制，未来将成为新轨道交通样板，成为新的‘中国名片’也当之无愧。";
        System.out.println(md5(str));
    }
}
