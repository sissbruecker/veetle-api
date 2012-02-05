package org.lazydevs.veetle.api;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 01.06.11
 * Time: 23:08
 *
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelLoader {

    /**
     * Loads detailed information for a channel
     * @param channelId The id of the channel
     * @return A JSON string that contains the channel data
     * @throws LoadChannelException
     */
    String load(String channelId) throws LoadChannelException;

    /**
     * Loads detailed information for multiple channels, custom implementations could load the data concurrently through multiple parallel running request
     * @param channelIds List of the channel id's
     * @return List of JSON strings
     * @throws LoadChannelException
     */
    List<String> load(List<String> channelIds) throws LoadChannelException;
}
