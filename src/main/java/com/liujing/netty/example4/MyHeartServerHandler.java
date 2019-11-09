package com.liujing.netty.example4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2019/11/9
 * @description:
 */
public class MyHeartServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();
        if(evt instanceof IdleStateEvent){
            String s = null;
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()){
                case READER_IDLE:
                    s="读";
                    break;
                case WRITER_IDLE:
                    s="写";
                    break;
                case ALL_IDLE:
                    s = "读写";
                    break;

            }
            ctx.close();
            System.out.println(channel.remoteAddress()+":"+s);
        }
    }
}
