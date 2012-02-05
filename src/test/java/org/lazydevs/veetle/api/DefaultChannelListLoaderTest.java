package org.lazydevs.veetle.api;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 05.02.12
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class DefaultChannelListLoaderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUrl() throws LoadChannelListException {

        DefaultChannelListLoader loader = new DefaultChannelListLoader();

        loader.setVeetleChannelListingUrl(null);

        loader.getChannelList();
    }

    @Test(expected = LoadChannelListException.class)
    public void testInvalidUrl() throws LoadChannelListException {

        DefaultChannelListLoader loader = new DefaultChannelListLoader();

        loader.setVeetleChannelListingUrl("http://blablabla.bla");

        loader.getChannelList();
    }

    @Test
    public void testLoadChannelList() throws LoadChannelListException {

        new DefaultChannelListLoader().getChannelList();
    }


}
