package com.liujing.netty.example2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2019/11/8
 * @description:
 */
public class MyServerHandle extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.hashCode());
        System.out.println(ctx.channel().remoteAddress()+":"+msg);
        ctx.channel().writeAndFlush("hello world");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("wsnd");
    }
}
