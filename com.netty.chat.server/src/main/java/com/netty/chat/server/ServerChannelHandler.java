package com.netty.chat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Daifa on 2017/5/27.
 */
public class ServerChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }

    //至少一个实现了exceptionCaught
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause)
    {
        cause.printStackTrace();
        System.out.println("close channel");
        channelHandlerContext.close();
    }
}
