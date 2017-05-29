package com.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Daifa on 2017/5/27.
 */
public class ClientApplication {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9000;
    private static ChannelFuture f;

    public static void main(String[] args) throws Exception {

        // Configure the client.
        //一个EventLoopGroup包含一个或者多个EventLoop
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //连接到远程主机和端口号
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new TcpClientInitializer());

            // Start the client.
            f = b.connect(HOST, PORT).sync();
            sendMsg("Hello World!");
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    private static void sendMsg(String msg)
    {
        while(true)
        {
            f.channel().writeAndFlush(msg);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
