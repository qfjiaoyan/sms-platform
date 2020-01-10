package com.qianfeng.smsplatform.gateway.netty4;


import com.qianfeng.smsplatform.gateway.netty4.entity.CmppActiveTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * 心跳Handler
 * @author weixiang
 * @date 2018/8/2 15:37
 */
public class HeartHandler extends ChannelInboundHandlerAdapter {



    private NettyClient client;
    public HeartHandler(NettyClient client){
        this.client=client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("初始化创建连接。。。");
        super.channelActive(ctx);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            if (state == IdleState.WRITER_IDLE || state == IdleState.ALL_IDLE) {
                client.submit(new CmppActiveTest());
                System.out.println("心跳启动!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("掉线了~~~~~~ 开启重连机制");
        client.reConnect(10);
    }
}
