package com.netty.chat.client;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Daifa on 2017/6/17.
 */
public class ClientChannelManager {

    private static ClientChannelManager ourInstance = new ClientChannelManager();

    private Map<String,Channel> map = new ConcurrentHashMap<>();

    public static ClientChannelManager getInstance() {
        return ourInstance;
    }

    private ClientChannelManager() {
    }

    public void putChannel(String channelId, Channel ch)
    {
        map.put(channelId,ch);
    }

    public void removeChanel(String channelId)
    {
        map.remove(channelId);
    }
}
