package org.lazydevs.veetle.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.lazydevs.veetle.api.util.JSONUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 28.05.11
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class DefaultChannelListLoader implements ChannelListLoader {

    private static final Logger log = Logger.getLogger(DefaultChannelListLoader.class.getSimpleName());

    private static final String DEFAULT_VEETLE_CHANNEL_LISTING_URL = "http://veetle.com/channel-listing-cross-site.js";
    private static final int DEFAULT_VEETLE_TIMEOUT = 20000;

    private String veetleChannelListingUrl = DEFAULT_VEETLE_CHANNEL_LISTING_URL;

    private int timeOut = DEFAULT_VEETLE_TIMEOUT;

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getVeetleChannelListingUrl() {
        return veetleChannelListingUrl;
    }

    public void setVeetleChannelListingUrl(String veetleChannelListingUrl) {
        this.veetleChannelListingUrl = veetleChannelListingUrl;
    }

    public List<String> getChannelList() throws LoadChannelListException {

        List<String> channels = new ArrayList<String>();

        // Load channel data from Veetle
        if (veetleChannelListingUrl == null || veetleChannelListingUrl.length() == 0) {
            throw new IllegalArgumentException("URL for loading the channel list is null or empty.");
        }

        String channelRequestResult = null;

        try {
            log.fine("Start loading Veetle channel list from URL: " + veetleChannelListingUrl);

            URL veetleListingUrl = new URL(veetleChannelListingUrl + "?noCache=" + new Date().getTime());
            URLConnection veetleConnection = veetleListingUrl.openConnection();

            veetleConnection.setConnectTimeout(timeOut);
            veetleConnection.setReadTimeout(timeOut);

            BufferedReader in = new BufferedReader(new InputStreamReader(veetleConnection.getInputStream()));

            channelRequestResult = in.readLine();

            in.close();

            log.fine("Channel list loaded");

        } catch (MalformedURLException e) {
            log.log(Level.WARNING, "Invalid URL provided.", e);
            throw new LoadChannelListException("Invalid URL provided.", e);
        } catch (IOException e) {
            log.log(Level.WARNING,"Error loading channel list from URL: " + veetleChannelListingUrl, e);
            throw new LoadChannelListException("Error loading channel list from URL: " + veetleChannelListingUrl, e);
        }

        // Parse channel data to JSON
        log.fine("Start parsing channel list.");

        if (channelRequestResult != null && channelRequestResult.length() > 0) {

            try {
                JSONArray jsonChannels = (JSONArray) JSONValue.parse(channelRequestResult);

                for (int channelIndex = 0; channelIndex < jsonChannels.size(); channelIndex++) {
                    JSONObject jsonChannel = (JSONObject) jsonChannels.get(channelIndex);

                    channels.add(JSONUtil.getStringValue(jsonChannel, "channelId"));
                }

            } catch (Exception ex) {

                log.log(Level.WARNING, "Error parsing channel list.", ex);
                throw new LoadChannelListException("Error parsing channel list.", ex);
            }
        }

        log.fine("Finished parsing channel list. [count=" + channels.size() + "]");

        return channels;
    }

}
