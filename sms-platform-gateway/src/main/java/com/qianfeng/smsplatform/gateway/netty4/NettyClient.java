package com.qianfeng.smsplatform.gateway.netty4;

import com.qianfeng.smsplatform.gateway.netty4.entity.CmppConnect;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppMessageHeader;
import com.qianfeng.smsplatform.gateway.netty4.entity.CmppSubmit;
import com.qianfeng.smsplatform.gateway.netty4.entity.ControlWindow;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


/**
 * 封装的netty客户端
 * @author weixiang
 * @date 2018/8/2 15:37
 */
public class NettyClient{

    private Channel channel;
    private String host;
    private int port;
    private String serviceId;
    private String pwd;

    public NettyClient(String host,int port,String serviceId,String pwd){
        this.host=host;
        this.port=port;
        this.serviceId=serviceId;
        this.pwd=pwd;
    }

    public void start(){
        try {
            doConnect(host,port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean submit(CmppMessageHeader submit){
        if (isActive()){
            channel.writeAndFlush(submit);
            return true;
        }
        return false;
    }

    public boolean isActive(){
        if (channel==null){
            return false;
        }
        if (!channel.isOpen()||!channel.isActive()||!channel.isWritable()){
            //channel没开 或 没激活
            return false;
        }
        return  true;
    }


    /**
     * 重连机制 在 channelInactive 触发时调用
     * @param reConnect
     */
    public void reConnect(int reConnect){
        int times=0;
        while (true&&times<reConnect){
            try {
                if (!isActive()) {
                    start();
                }else {
                    try {
                        Thread.sleep(10 * 1000);
                        times++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception ex){
                System.out.println("尝试重连...:"+host+":"+port+" / "+serviceId);
                try {
                    Thread.sleep(10 * 1000);
                    times++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void doConnect(final String host, final int port) throws InterruptedException {

        //创建线程组 - 手动设置线程数,默认为cpu核心数2倍
        EventLoopGroup eventLoopGroup =  new NioEventLoopGroup(4);
        //创建引导程序
        Bootstrap bootstrap = new Bootstrap();
        //保持长连接
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        //将线程加入bootstrap
        bootstrap.group(eventLoopGroup)
                .remoteAddress(host,port)
                //使用指定通道类
                .channel(NioSocketChannel.class)
                //设置日志
                .handler(new LoggingHandler(LogLevel.INFO))
                //重写通道初始化方法
                .handler(new NettyClientInitializer(this));

        ChannelFuture channelFuture = bootstrap.connect().sync();

        channel= channelFuture.channel();

        //账号登陆
        CmppMessageHeader cmppConnect=new CmppConnect(serviceId,pwd);
        channel.writeAndFlush(cmppConnect);

        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

        //关闭前阻塞
        //channelFuture.channel().closeFuture().sync();
    }
}
