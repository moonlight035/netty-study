package com.liujing.netty.example3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.DefaultEventExecutor;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2019/11/9
 * @description:
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(new DefaultEventExecutor());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("["+channel.remoteAddress()+"]"+msg);
        channels.forEach(ch -> {
            if(ch==channel){
                ch.writeAndFlush("[自己]"+msg+"\n");
            }
            else {
                ch.writeAndFlush("["+channel.remoteAddress()+"]"+msg+"\n");
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 用户上线\n");
        channels.writeAndFlush(channel.remoteAddress()+" 用户上线\n");
        channels.add(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 用户下线\n");
        channels.writeAndFlush(channel.remoteAddress()+" 用户下线\n");
    }
}
