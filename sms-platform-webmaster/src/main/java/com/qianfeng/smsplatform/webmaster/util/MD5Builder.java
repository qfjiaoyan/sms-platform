package com.qianfeng.smsplatform.webmaster.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Builder {

    public static String build(String origin, String charsetName) {
        if (origin == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        //生成一组length=16的byte数组
        byte[] bs = digest.digest(origin.getBytes(Charset.forName(charsetName)));

        for (byte b : bs) {
            int c = b & 0xFF; //byte转int为了不丢失符号位， 所以&0xFF
            if (c < 16) { //如果c小于16，就说明，可以只用1位16进制来表示， 那么在前面补一个0
                sb.append("0");
            }
            sb.append(Integer.toHexString(c));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String str = MD5Builder.build("hello,world", "UTF-8");

        System.out.println(str);

    }

}
