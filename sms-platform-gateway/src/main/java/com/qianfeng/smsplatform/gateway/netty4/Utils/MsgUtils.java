package com.qianfeng.smsplatform.gateway.netty4.Utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgUtils {
	//序列编号起始值(起始为随机数即可)
	private static int sequenceId=  Math.abs(new Long(System.currentTimeMillis()).intValue());
	//序列峰值
	private static int MAX_VALUE=Integer.MAX_VALUE/2;

	/**
	 * 序列 自增
	 */
	public static int getSequence(){
		++sequenceId;
		if(sequenceId>MAX_VALUE){
			sequenceId=Math.abs(new Long(System.currentTimeMillis()).intValue());
		}
		return sequenceId;
	}
	/**
	 * 时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐 �?
	 */
	public static String  getTimestamp(){
		DateFormat format=new SimpleDateFormat("MMddhhmmss");
		return format.format(new Date());
	}
	/**
	 * 用于鉴别源地�?。其值�?�过单向MD5 hash计算得出，表示如下：
	 * AuthenticatorSource =
	 * MD5（Source_Addr+9 字节�?0 +shared secret+timestamp�?
	 * Shared secret 由中国移动与源地�?实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位�??
	 * @return
	 */
	public static byte[] getAuthenticatorSource(String spId,String secret){
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			byte[] data=(spId+"\0\0\0\0\0\0\0\0\0"+secret+MsgUtils.getTimestamp()).getBytes();
			return md5.digest(data);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SP链接到ISMG拼接AuthenticatorSource失败�?"+e.getMessage());
			return null;
		}
	}
	/**
	 * 向流中写入指定字节长度的字符串，不足时补0
	 * @param dous:要写入的流对�?
	 * @param s:要写入的字符�?
	 * @param len:写入长度,不足�?0
	 */
	public static void writeString(DataOutputStream dous,String s,int len){
		
		try {
			byte[] data=s.getBytes("gb2312");
			if(data.length>len){
				System.out.println("向流中写入的字符串超长！要写"+len+" 字符串是:"+s);
			}
			int srcLen=data.length;
			dous.write(data);
			while(srcLen<len){
				dous.write('\0');
				srcLen++;
			}
		} catch (IOException e) {
			System.out.println("向流中写入指定字节长度的字符串失败："+e.getMessage());
		}
	}


	public static byte[] getLenBytes(String s, int len) {
		if (s == null) {
			s = "";
		}
		byte[] rb = new byte[len];
		byte[] sb = s.getBytes();
		for (int i = sb.length; i < rb.length; i++) {
			rb[i] = 0;
		}
		if (sb.length == len) {
			return sb;
		} else {
			for (int i = 0; i < sb.length && i < len; i++) {
				rb[i] = sb[i];
			}
			return rb;
		}
	}





	
	/**
	 * 从流中读取指定长度的字节，转成字符串返回
	 * @param ins:要读取的流对�?
	 * @param len:要读取的字符串长�?
	 * @return:读取到的字符�?
	 */
	public static String readString(java.io.DataInputStream ins, int len, String charset) {
		byte[] b = new byte[len];
		try {
			ins.read(b);
			String s;
			if (charset == null)
				s = new String(b);
			else
				s = new String(b, charset);
			s = s.trim();
			return s;
		} catch (IOException e) {
			return "";
		}
	}
	/**
	 * 截取字节
	 * @param msg
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte[] getMsgBytes(byte[] msg,int start,int end){
		byte[] msgByte=new byte[end-start];
		int j=0;
		for(int i=start;i<end;i++){
			msgByte[j]=msg[i];
			j++;
		}		
		return msgByte;
	}
	/**  
	 * UCS2解码  
	 *   
	 * @param src  
	 *            UCS2 源串  
	 * @return 解码后的UTF-16BE字符�?  
	 */  
	public static String DecodeUCS2(String src) {   
	    byte[] bytes = new byte[src.length()/2];   
	    for (int i = 0; i < src.length(); i += 2) {   
	        bytes[i/2]=(byte)(Integer.parseInt(src.substring(i, i + 2), 16));   
	    }   
	    String reValue = "";   
	    try {   
	        reValue = new String(bytes, "UTF-16BE");   
	    } catch (UnsupportedEncodingException e) {   
	    	reValue="";
	    }   
	    return reValue;   
	  
	}  
	/**
	 * byte[] 转  long 
	 * 2016年9月30日
	 */
	public static long bytesToLong(byte[] b) {
		long temp = 0;
		long res = 0;
		for (int i = 0; i < 8; i++) {
			temp = b[i] & 0xff;
			temp <<= 8*i;
			res |= temp;
		}
		return res;
	}

	public static int bytesToInt(byte[] b) {
		return   b[3] & 0xFF |
				(b[2] & 0xFF) << 8 |
				(b[1] & 0xFF) << 16 |
				(b[0] & 0xFF) << 24;
	}


	/**  
	 * UCS2编码  
	 *   
	 * @param src  
	 *            UTF-16BE编码的源�?  
	 * @return 编码后的UCS2�?  
	 */  
	public static String EncodeUCS2(String src) {   
	    byte[] bytes;   
	    try {   
	        bytes = src.getBytes("UTF-16BE");   
	    } catch (UnsupportedEncodingException e) {   
	    	bytes=new byte[0]; 
	    }   
	    StringBuffer reValue = new StringBuffer();   
	    StringBuffer tem = new StringBuffer();   
	    for (int i = 0; i < bytes.length; i++) {   
	        tem.delete(0, tem.length());   
	        tem.append(Integer.toHexString(bytes[i] & 0xFF));   
	        if(tem.length()==1){   
	            tem.insert(0,'0');   
	        }   
	        reValue.append(tem);   
	    }   
	    return reValue.toString().toUpperCase();   
	}   
}
