package com.netty.chat.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Daifa on 2017/5/27.
 */
public class ServerApplication {

    private static final int PORT = 9000;

    public static void main(String[] args) throws Exception {
        //包含一个ServerChannel，代表服务器自身的已绑定到某个本地端口号
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //包含所有已创建的用来处理传入客户端连接的channel
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap绑定到一个本地端口号
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new TcpServerInitializer());
            // Start the server.
            ChannelFuture f = b.bind(PORT).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}