package org.lazydevs.veetle.api;

import org.lazydevs.veetle.api.model.Channel;

/**
 * Created by IntelliJ IDEA.
 * User: sascha
 * Date: 08.01.12
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelParser {

    /**
     * Parses a JSON string into a chanel data object
     * @param json The JSON that holds the channel data
     * @return Channel object
     * @throws ParseChannelException
     */
    public Channel parseChannel(String json) throws ParseChannelException;
}
