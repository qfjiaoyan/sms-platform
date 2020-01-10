package com.qianfeng.smsplatform.gateway.netty4.entity;

import com.qianfeng.smsplatform.gateway.netty4.Utils.MsgUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author weixiang
 * @date 2018/8/2 15:37
 */
public class CmppDeliver {

    private byte[] Msg_Id=new byte[8];
    private String Dest_Id;// 21 目的号码 String
    private String Service_Id;// 10 业务标识 String
    private byte TP_pid = 0;
    private byte TP_udhi = 0;
    private byte Msg_Fmt = 15;
    private String Src_terminal_Id;// 21 string 源终端MSISDN号码
    private byte Registered_Delivery = 0;// 是否为状态报告 0：非状态报告1：状态报告
    private byte msg_Length;// 消息长度
    private String msg_Content;// 消息内容
    private long Reserved; // 保留字段

    /**
     * 当ISMG向SP送交状态报告时，信息内容字段（Msg_Content）格式定义如下：
     */

    private long Msg_Id_DELIVRD;
    private String Stat;
    private String Submit_time;
    private String Done_time;
    private String Dest_terminal_Id;
    private int SMSC_sequence;

    private int result;// 解析结果

    /**
     * 2016年9月30日
     *
     * @throws IOException
     */
    public CmppDeliver(byte[] data) {

        System.arraycopy(data, 12, data, 0, data.length-12);

        ByteArrayInputStream bais = null;
        DataInputStream dis = null;

        try {
            if (null != data && data.length > 0) {
                bais = new ByteArrayInputStream(data);
                dis = new DataInputStream(bais);
                /**
                 * migid 需要转换
                 */
                byte[] msgid_b = new byte[8];
                dis.read(msgid_b);
                this.Msg_Id = msgid_b;
                this.Dest_Id = MsgUtils.readString(dis, 21, null);// 21bit
                this.Service_Id = MsgUtils.readString(dis, 10, null);
                this.TP_pid = dis.readByte();
                this.TP_udhi = dis.readByte();
                this.Msg_Fmt = dis.readByte();
                // 源终端MSISDN号码（状态报告时填为CMPP_SUBMIT消息的目的终端号码）
                this.Src_terminal_Id = MsgUtils.readString(dis, 21, this.Msg_Fmt == 8 ? "UTF-16BE" : "gb2312");
                // 是否为应答信息 0：非应答信息 1：状态报告
                this.Registered_Delivery = dis.readByte();
                this.msg_Length = dis.readByte();
                // 状态报告的 msg_Content_b 类型大小为协议固定8+7+10+10+21+4
                byte[] msg_Content_b = new byte[this.Registered_Delivery == 0 ? this.msg_Length
                        : 8 + 7 + 10 + 10 + 21 + 4];
                dis.read(msg_Content_b);
                // 如果是状态报告
                if (this.Registered_Delivery == 1) {
                    ByteArrayInputStream baisd = new ByteArrayInputStream(msg_Content_b);
                    DataInputStream disd = new DataInputStream(baisd);
                    // 开始解析 content中的字段
                    byte[] Msg_Id_DELIVRD_B = new byte[8];
                    disd.read(Msg_Id_DELIVRD_B);
                    this.Msg_Id_DELIVRD = MsgUtils.bytesToLong(Msg_Id_DELIVRD_B);
                    this.Msg_Id_DELIVRD=Math.abs(this.Msg_Id_DELIVRD);
                    this.Stat = MsgUtils.readString(disd, 7, this.Msg_Fmt == 8 ? "UTF-16BE" : "gb2312");
                    this.Submit_time = MsgUtils.readString(disd, 10, this.Msg_Fmt == 8 ? "UTF-16BE" : "gb2312");
                    this.Done_time = MsgUtils.readString(disd, 10, this.Msg_Fmt == 8 ? "UTF-16BE" : "gb2312");
                    this.Dest_terminal_Id = MsgUtils.readString(disd, 21, this.Msg_Fmt == 8 ? "UTF-16BE" : "gb2312");
                    this.SMSC_sequence = disd.readInt();
                    disd.close();
                    baisd.close();
                    this.result = 0;
                } else {
                    this.msg_Content = new String(msg_Content_b, this.Msg_Fmt == 8 ? "UTF-16BE" : "gb2312");
                    this.Reserved = dis.readLong();// 保留项 暂无用
                }
                this.result = 0;
            } else {
                this.result = 1;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != dis)
                    dis.close();
                if (null != bais)
                    bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @return msg_Id
     */
    public byte[] getMsg_Id() {
        return Msg_Id;
    }

    /**
     * @return dest_Id
     */
    public String getDest_Id() {
        return Dest_Id;
    }

    /**
     * @return service_Id
     */
    public String getService_Id() {
        return Service_Id;
    }

    /**
     * @return tP_pid
     */
    public byte getTP_pid() {
        return TP_pid;
    }

    /**
     * @return tP_udhi
     */
    public byte getTP_udhi() {
        return TP_udhi;
    }

    /**
     * @return msg_Fmt
     */
    public byte getMsg_Fmt() {
        return Msg_Fmt;
    }

    /**
     * @return src_terminal_Id
     */
    public String getSrc_terminal_Id() {
        return Src_terminal_Id;
    }

    /**
     * @return registered_Delivery
     */
    public byte getRegistered_Delivery() {
        return Registered_Delivery;
    }

    /**
     * @return msg_Length
     */
    public byte getMsg_Length() {
        return msg_Length;
    }

    /**
     * @return msg_Content
     */
    public String getMsg_Content() {
        return msg_Content;
    }

    /**
     * @return reserved
     */
    public long getReserved() {
        return Reserved;
    }

    /**
     * @return msg_Id_DELIVRD
     */
    public long getMsg_Id_DELIVRD() {
        return Msg_Id_DELIVRD;
    }

    /**
     * @return stat
     */
    public String getStat() {
        return Stat;
    }

    /**
     * @return submit_time
     */
    public String getSubmit_time() {
        return Submit_time;
    }

    /**
     * @return done_time
     */
    public String getDone_time() {
        return Done_time;
    }

    /**
     * @return dest_terminal_Id
     */
    public String getDest_terminal_Id() {
        return Dest_terminal_Id;
    }

    /**
     * @return sMSC_sequence
     */
    public int getSMSC_sequence() {
        return SMSC_sequence;
    }

    /**
     * @return result
     */
    public int getResult() {
        return result;
    }

    /**
     * @param msg_Id
     *            要设置的 msg_Id
     */
    public void setMsg_Id(byte[] msg_Id) {
        Msg_Id = msg_Id;
    }

    /**
     * @param dest_Id
     *            要设置的 dest_Id
     */
    public void setDest_Id(String dest_Id) {
        Dest_Id = dest_Id;
    }

    /**
     * @param service_Id
     *            要设置的 service_Id
     */
    public void setService_Id(String service_Id) {
        Service_Id = service_Id;
    }

    /**
     * @param tP_pid
     *            要设置的 tP_pid
     */
    public void setTP_pid(byte tP_pid) {
        TP_pid = tP_pid;
    }

    /**
     * @param tP_udhi
     *            要设置的 tP_udhi
     */
    public void setTP_udhi(byte tP_udhi) {
        TP_udhi = tP_udhi;
    }

    /**
     * @param msg_Fmt
     *            要设置的 msg_Fmt
     */
    public void setMsg_Fmt(byte msg_Fmt) {
        Msg_Fmt = msg_Fmt;
    }

    /**
     * @param src_terminal_Id
     *            要设置的 src_terminal_Id
     */
    public void setSrc_terminal_Id(String src_terminal_Id) {
        Src_terminal_Id = src_terminal_Id;
    }

    /**
     * @param registered_Delivery
     *            要设置的 registered_Delivery
     */
    public void setRegistered_Delivery(byte registered_Delivery) {
        Registered_Delivery = registered_Delivery;
    }

    /**
     * @param msg_Length
     *            要设置的 msg_Length
     */
    public void setMsg_Length(byte msg_Length) {
        this.msg_Length = msg_Length;
    }

    /**
     * @param msg_Content
     *            要设置的 msg_Content
     */
    public void setMsg_Content(String msg_Content) {
        this.msg_Content = msg_Content;
    }

    /**
     * @param reserved
     *            要设置的 reserved
     */
    public void setReserved(long reserved) {
        Reserved = reserved;
    }

    /**
     * @param msg_Id_DELIVRD
     *            要设置的 msg_Id_DELIVRD
     */
    public void setMsg_Id_DELIVRD(int msg_Id_DELIVRD) {
        Msg_Id_DELIVRD = msg_Id_DELIVRD;
    }

    /**
     * @param stat
     *            要设置的 stat
     */
    public void setStat(String stat) {
        Stat = stat;
    }

    /**
     * @param submit_time
     *            要设置的 submit_time
     */
    public void setSubmit_time(String submit_time) {
        Submit_time = submit_time;
    }

    /**
     * @param done_time
     *            要设置的 done_time
     */
    public void setDone_time(String done_time) {
        Done_time = done_time;
    }

    /**
     * @param dest_terminal_Id
     *            要设置的 dest_terminal_Id
     */
    public void setDest_terminal_Id(String dest_terminal_Id) {
        Dest_terminal_Id = dest_terminal_Id;
    }

    /**
     * @param sMSC_sequence
     *            要设置的 sMSC_sequence
     */
    public void setSMSC_sequence(int sMSC_sequence) {
        SMSC_sequence = sMSC_sequence;
    }

    /**
     * @param result
     *            要设置的 result
     */
    public void setResult(int result) {
        this.result = result;
    }

}
