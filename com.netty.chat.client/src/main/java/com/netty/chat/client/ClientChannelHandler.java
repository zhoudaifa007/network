package com.netty.chat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Daifa on 2017/5/28.
 */
public class ClientChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        String channelId = incoming.id().asLongText();
        ClientChannelManager.getInstance().putChannel(channelId,incoming);
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        String channelId = incoming.id().asLongText();
        ClientChannelManager.getInstance().removeChanel(channelId);
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"掉线");
    }
}
