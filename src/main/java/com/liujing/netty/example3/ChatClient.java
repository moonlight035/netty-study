package com.liujing.netty.example3;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @author jing.liu14@ucarinc.com
 * @date 2019/11/9
 * @description:
 */
public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).
                    handler(new ChatClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();
            Channel channel = channelFuture.channel();
            while(true){
                Scanner scanner = new Scanner(System.in);
                String s = scanner.nextLine();
                channel.writeAndFlush(s+"\n");
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
