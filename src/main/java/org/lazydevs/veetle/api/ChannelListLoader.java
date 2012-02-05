package org.lazydevs.veetle.api;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 28.05.11
 * Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelListLoader {

    /**
     * Loads the id's for the channels that are currently online on veetle
     * @return List of channel id's
     * @throws LoadChannelListException
     */
    List<String> getChannelList() throws LoadChannelListException;
}
