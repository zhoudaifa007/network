package com.netty.chat.server;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Daifa on 2017/5/28.
 *  channerl的管理
 */
public class ChannelManager {
    private static ChannelManager ourInstance = new ChannelManager();

    private Map<String,Channel> map = new ConcurrentHashMap<>();

    public static ChannelManager getInstance() {
        return ourInstance;
    }

    private ChannelManager() {
    }

    public void putChannel(String userId, Channel ch)
    {
        map.put(userId,ch);
    }
}
