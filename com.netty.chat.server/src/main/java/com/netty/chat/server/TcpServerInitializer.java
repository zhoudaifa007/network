package com.netty.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Daifa on 2017/5/27.
 */
public class TcpServerInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
        pipeline.addLast("StringDecoder", new StringDecoder());
        // Encoders
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        pipeline.addLast("StringEncoder", new StringEncoder());
        pipeline.addLast("handler", new ServerChannelHandler());
    }
}
