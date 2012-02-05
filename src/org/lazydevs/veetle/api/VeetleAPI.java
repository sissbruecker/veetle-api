package org.lazydevs.veetle.api;

import org.lazydevs.veetle.api.model.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 28.05.11
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 *
 * API object that you can pass to other applications
 */
public class VeetleAPI {

    private ChannelListLoader channelListLoader;

    private ChannelLoader channelLoader;

    private ChannelParser channelParser;

    public void setChannelListLoader(ChannelListLoader channelListLoader) {
        this.channelListLoader = channelListLoader;
    }

    public void setChannelLoader(ChannelLoader channelLoader) {
        this.channelLoader = channelLoader;
    }

    public void setChannelParser(ChannelParser channelParser) {
        this.channelParser = channelParser;
    }

    public VeetleAPI() {
        this.channelListLoader = new DefaultChannelListLoader();
        this.channelLoader = new DefaultChannelLoader();
        this.channelParser = new DefaultChannelParser();
    }

    public List<String> getChannelList() throws LoadChannelListException {
        return channelListLoader.getChannelList();
    }

    public Channel getChannel(String channelId) throws LoadChannelException, ParseChannelException {
        return channelParser.parseChannel(channelLoader.load(channelId));
    }

    public List<Channel> getChannels(List<String> channelIds) throws LoadChannelException, ParseChannelException {

        List<String> channelDataList = channelLoader.load(channelIds);
        List<Channel> channels = new ArrayList<Channel>();

        for(String channelData : channelDataList) {
            channels.add(channelParser.parseChannel(channelData));
        }

        return channels;
    }
}
