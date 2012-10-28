package org.lazydevs.veetle.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.lazydevs.veetle.api.model.Channel;
import org.lazydevs.veetle.api.model.PlayListItem;
import org.lazydevs.veetle.api.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sascha
 * Date: 08.01.12
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class DefaultChannelParser implements ChannelParser {

    public Channel parseChannel(String json) throws ParseChannelException {

        Channel channel = new Channel();

        try {
            JSONObject jsonChannelDetails = (JSONObject) JSONValue.parse(json);
            JSONObject jsonChannel = (JSONObject) jsonChannelDetails.get("payload");

            String channelId = JSONUtil.getStringValue(jsonChannel, "channelId");

            channel.setChannelId(channelId);
            channel.setDescription(JSONUtil.getStringValue(jsonChannel, "description"));
            channel.setPublic(JSONUtil.getBooleanValue(jsonChannel, "isPublic"));
            channel.setCategoryId(JSONUtil.getIntegerValue(jsonChannel, "categoryId"));
            channel.setUserId(JSONUtil.getLongValue(jsonChannel, "userId"));
            channel.setTitle(JSONUtil.getStringValue(jsonChannel, "title"));
            channel.setCreationTime(JSONUtil.getLongValue(jsonChannel, "creationTime"));
            channel.setUserName(JSONUtil.getStringValue(jsonChannel, "login"));
            channel.setUserAvatarUrl(JSONUtil.getStringValue(jsonChannel, "broadcasterAvatar"));
            channel.setUserWebsite(JSONUtil.getStringValue(jsonChannel, "website"));
            channel.setNumberOfViews(JSONUtil.getLongValue(jsonChannel, "numberOfViews"));
            channel.setNumberOfComments(JSONUtil.getLongValue(jsonChannel, "numComments"));
            channel.setStartTime(JSONUtil.getLongValue(jsonChannel, "startTime"));

            channel.setSourceType(JSONUtil.getStringValue(jsonChannel, "sourceType"));
            channel.setBitRate(JSONUtil.getLongValue(jsonChannel, "bitrate"));
            channel.setSignalQuality(JSONUtil.getIntegerValue(jsonChannel, "signalQuality"));

            channel.setPopularityIndex(JSONUtil.getIntegerValue(jsonChannel, "popularityIndex"));
            channel.setBroadCastStartTime(JSONUtil.getLongValue(jsonChannel, "broadcastStartedTime"));

            // Logos
            JSONObject jsonLogos = (JSONObject) jsonChannel.get("logo");

            channel.setSmallLogoUrl(JSONUtil.getStringValue(jsonLogos, "sm"));
            channel.setLargeLogoUrl(JSONUtil.getStringValue(jsonLogos, "lg"));

            // Parse playlist
            JSONObject jsonPlaylist = (JSONObject) jsonChannel.get("programme");
            List<PlayListItem> playList = new ArrayList<PlayListItem>();

            if (jsonPlaylist != null && JSONUtil.getBooleanValue(jsonPlaylist, "success")) {
                JSONArray jsonPlaylistList = (JSONArray) jsonPlaylist.get("payload");

                if (jsonPlaylistList != null) {

                    for (int index = 0; index < jsonPlaylistList.size(); index++) {
                        JSONObject jsonPlayListItem = (JSONObject) jsonPlaylistList.get(index);
                        PlayListItem playlistItem = new PlayListItem();

                        playlistItem.setChannelId(channelId);
                        playlistItem.setIndex(JSONUtil.getIntegerValue(jsonPlayListItem, "playOrder"));
                        playlistItem.setTitle(JSONUtil.getStringValue(jsonPlayListItem, "title"));
                        playlistItem.setDescription(JSONUtil.getStringValue(jsonPlayListItem, "description"));
                        playlistItem.setDuration(JSONUtil.getLongValue(jsonPlayListItem, "durationInSeconds") * 1000);

                        if (jsonPlayListItem.get("startTime") != null) {
                            playlistItem.setStartTime(JSONUtil.getLongValue(jsonPlayListItem, "startTime") * 1000);
                        }

                        playList.add(playlistItem);
                    }
                }
            }

            channel.setPlaylist(playList);

            // Create schedule
            long startTime = JSONUtil.getLongValue(jsonChannel, "startTime") * 1000;

            channel.setStartTime(startTime);

            ScheduleBuilder.createSchedule(channel);

            channel.setHasSchedule(channel.getSchedule() != null && channel.getSchedule().size() > 0);

        } catch (Throwable t) {
            throw new ParseChannelException("Error parsing channel", t);
        }

        return channel;
    }

}
