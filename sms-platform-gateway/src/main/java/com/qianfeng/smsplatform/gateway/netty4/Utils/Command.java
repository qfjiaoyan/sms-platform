package com.qianfeng.smsplatform.gateway.netty4.Utils;

/**
 * 以下常量均为CMPP2.0协议中规定的值
 */
public interface Command {
	/**
	 * 请求连接
	 */
	public final int CMPP_CONNECT = 0x00000001;
	/**
	 * 请求连接应答
	 */
	public final int CMPP_CONNECT_RESP = 0x80000001;
	/**
	 * 终止连接
	 */
	public final int CMPP_TERMINATE = 0x00000002;
	/**
	 * 终止连接应答
	 */
	public final int CMPP_TERMINATE_RESP = 0x80000002;
	/**
	 * 提交短信
	 */
	public final int CMPP_SUBMIT = 0x00000004;
	/**
	 * 提交短信应答
	 */
	public final int CMPP_SUBMIT_RESP = 0x80000004;
	/**
	 * 短信下发
	 */
	public final int CMPP_DELIVER = 0x00000005;
	/**
	 * + 下发短信应答
	 */
	public final int CMPP_DELIVER_RESP = 0x80000005;
	/**
	 * 发送短信状态查询
	 */
	public final int CMPP_QUERY = 0x00000006;
	/**
	 * 发送短信状态查询应答
	 */
	public final int CMPP_QUERY_RESP = 0x80000006;
	/**
	 * 删除短信
	 */
	public final int CMPP_CANCEL = 0x00000007;
	/**
	 * 删除短信应答
	 */
	public final int CMPP_CANCEL_RESP = 0x80000007;
	/**
	 * 激活测试
	 */
	public final int CMPP_ACTIVE_TEST = 0x00000008;
	/**
	 * 激活测试应答
	 */
	public final int CMPP_ACTIVE_TEST_RESP = 0x80000008;
	/**
	 * 消息前转
	 */
	public final int CMPP_FWD = 0x00000009;
	/**
	 * 消息前转应答
	 */
	public final int CMPP_FWD_RESP = 0x80000009;
	/**
	 * MT路由请求
	 */
	public final int CMPP_MT_ROUTE = 0x00000010;
	/**
	 * MT路由请求应答
	 */
	public final int CMPP_MT_ROUTE_RESP = 0x80000010;
	/**
	 * MO路由请求
	 */
	public final int CMPP_MO_ROUTE = 0x00000011;
	/**
	 * MO路由请求应答
	 */
	public final int CMPP_MO_ROUTE_RESP = 0x80000011;
	/**
	 * 获取MT路由请求
	 */
	public final int CMPP_GET_MT_ROUTE = 0x00000012;
	/**
	 * 获取MT路由请求应答
	 */
	public final int CMPP_GET_MT_ROUTE_RESP = 0x80000012;
	/**
	 * MT路由更新
	 */
	public final int CMPP_MT_ROUTE_UPDATE = 0x00000013;
	/**
	 * MT路由更新应答
	 */
	public final int CMPP_MT_ROUTE_UPDATE_RESP = 0x80000013;
	/**
	 * MO路由更新
	 */
	public final int CMPP_MO_ROUTE_UPDATE = 0x00000014;
	/**
	 * MO路由更新应答
	 */
	public final int CMPP_MO_ROUTE_UPDATE_RESP = 0x80000014;
	/**
	 * MT路由更新
	 */
	public final int CMPP_PUSH_MT_ROUTE_UPDATE = 0x00000015;
	/**
	 * MT路由更新应答
	 */
	public final int CMPP_PUSH_MT_ROUTE_UPDATE_RESP = 0x80000015;
	/**
	 * MO路由更新
	 */
	public final int CMPP_PUSH_MO_ROUTE_UPDATE = 0x00000016;
	/**
	 * MO路由更新应答
	 */
	public final int CMPP_PUSH_MO_ROUTE_UPDATE_RESP = 0x80000016;
	/**
	 * 获取MO路由请求
	 */
	public final int CMPP_GET_MO_ROUTE = 0x00000017;
	/**
	 * 获取MO路由请求应答
	 */
	public final int CMPP_GET_MO_ROUTE_RESP = 0x80000017;


	/** Cmpp协议版本字节常量 */
	public static final byte CMPP2_VERSION = (byte) 32;
	/** Cmpp协议版本字节常量 */
	public static final byte CMPP3_VERSION = (byte) 48;

	/** 下行长度（不包含短信内容长度） */
	public static final int CMPP2_SUBMIT_LEN_EXPMSGLEN = 8 + 1 + 1 + 1 + 1 + 10 + 1 + 21 + 1 + 1 + 1
			+ 6 + 2 + 6 + 17 + 17 + 21 + 1 + 1 + 8;

	public static final int CMPP3_SUBMIT_LEN_EXPMSGLEN = 8 + 1 + 1 + 1 + 1 + 10 + 1 + 32 + 1 + 1
			+ 1 + 1 + 6 + 2 + 6 + 17 + 17 + 21 + 1 + 1 + 1 + 20;
}
