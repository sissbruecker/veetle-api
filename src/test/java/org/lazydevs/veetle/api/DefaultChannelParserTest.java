package org.lazydevs.veetle.api;

import org.junit.Assert;
import org.junit.Test;
import org.lazydevs.veetle.api.model.Channel;
import org.lazydevs.veetle.api.model.PlayListItem;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 05.02.12
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class DefaultChannelParserTest {

    private static String invalidChannelJson = "{\"success\":false,\"payload\":\"no channel with channel ID blablabla exists.\"}";
    private static String channelJson = "{\"success\":true,\"payload\":{\"allowEmbed\":true,\"allowOverlayAd\":true,\"allowPrerollAd\":true,\"allowVlcPrerollAd\":true,\"isPublic\":true,\"isLive\":\"1\",\"host\":\"130.117.72.207\",\"channelId\":\"48697987e6a7b\",\"status\":\"OnAir\",\"categoryId\":90,\"userId\":1827182809,\"title\":\"Public Interest Feed\",\"description\":\"Public Interest Video Feed, Highlighting Southwestern Virginia\",\"creationTime\":1214526343,\"login\":\"Barbara\",\"numberOfViews\":351643,\"startTime\":1328257344,\"homeChannelHost\":\"130.117.72.207\",\"chost\":\"77.67.109.221\",\"chostList\":[\"77.67.109.220\",\"77.67.109.221\"],\"sourceType\":\"network\",\"isMobile\":false,\"flashEnabled\":true,\"allowComment\":true,\"showSchedule\":true,\"airTime\":11640503,\"tapeDelay\":0,\"currentNumViewers\":\"0\",\"bitrate\":204551,\"signalQuality\":\"100\",\"numSecSinceLastBufferRecved\":\"0.363358\",\"mobileStreamUrl\":\"BLAH\",\"iPadEnabled\":true,\"iPhoneEnabled\":true,\"hlsReenvelopeEnabled\":false,\"flvReenvelopeEnabled\":false,\"broadcastFlags\":[],\"popularityIndex\":-12,\"broadcastStartedTime\":1328257344,\"numComments\":0,\"logo\":{\"sm\":\"http:\\/\\/veetle.com\\/clogo\\/a7b\\/48697987e6a7b\\/logo_0_20100914150757_4c8ff23d91b80.jpg\",\"lg\":\"http:\\/\\/veetle.com\\/clogo\\/a7b\\/48697987e6a7b\\/logo_xl_0_20100914150757_4c8ff23da7117.jpg\"},\"peakViewers\":128,\"broadcasterAvatar\":\"http:\\/\\/veetle.com\\/profimg\\/defaultAvatar_md.png\",\"programme\":{\"success\":false},\"website\":\"http:\\/\\/www.google.com\"}}";
    private static String channelWithPlaylistJson = "{\"success\":true,\"payload\":{\"allowEmbed\":true,\"allowOverlayAd\":true,\"allowPrerollAd\":true,\"allowVlcPrerollAd\":true,\"isPublic\":true,\"isLive\":\"1\",\"host\":\"213.254.245.213\",\"channelId\":\"4e3f5e486080b\",\"status\":\"OnAir\",\"categoryId\":10,\"userId\":1252791627,\"title\":\"The Iron Lady+ ( 2012)\",\"description\":\"My Site Should BB Soon\",\"creationTime\":1312775752,\"login\":\"phatpicks4\",\"numberOfViews\":512697,\"startTime\":1328412102,\"homeChannelHost\":\"213.254.245.213\",\"chost\":\"77.67.109.221\",\"chostList\":[\"77.67.109.220\",\"77.67.109.221\"],\"sourceType\":\"file\",\"isMobile\":false,\"flashEnabled\":true,\"allowComment\":true,\"showSchedule\":true,\"airTime\":6088767,\"tapeDelay\":0,\"currentNumViewers\":\"128\",\"bitrate\":522302,\"signalQuality\":\"100\",\"numSecSinceLastBufferRecved\":\"0.343391\",\"mobileStreamUrl\":\"BLAH\",\"iPadEnabled\":true,\"iPhoneEnabled\":true,\"hlsReenvelopeEnabled\":false,\"flvReenvelopeEnabled\":true,\"broadcastFlags\":[],\"popularityIndex\":123,\"broadcastStartedTime\":1328412102,\"numComments\":0,\"logo\":{\"sm\":\"http:\\/\\/veetle.com\\/clogo\\/80b\\/4e3f5e486080b\\/logo_0_20120204192308_4f2df61c03e3e.jpg\",\"lg\":\"http:\\/\\/veetle.com\\/clogo\\/80b\\/4e3f5e486080b\\/logo_xl_0_20120204192308_4f2df61c0960d.jpg\"},\"peakViewers\":566,\"broadcasterAvatar\":\"http:\\/\\/veetle.com\\/profimg\\/627\\/1252791627\\/md_20120120101659_4f19af9b69c49.jpg\",\"programme\":{\"success\":true,\"payload\":[{\"playOrder\":0,\"title\":\"Is Not What U Think\",\"description\":\"\",\"durationInSeconds\":89},{\"playOrder\":1,\"title\":\"The Iron Lady+ ( 2012)\",\"description\":\"\",\"startTime\":1328444121,\"durationInSeconds\":6298}]},\"website\":\"\"}}";

    @Test(expected = ParseChannelException.class)
    public void testParseInvalidChannel() throws ParseChannelException, LoadChannelException {

        new DefaultChannelParser().parseChannel(invalidChannelJson);
    }

    @Test()
    public void testParseChannel() throws ParseChannelException, LoadChannelException {

        Channel channel = new DefaultChannelParser().parseChannel(channelJson);

        Assert.assertNotNull(channel);
        Assert.assertEquals(true, channel.isPublic());
        Assert.assertEquals("48697987e6a7b", channel.getChannelId());
        Assert.assertEquals(90, channel.getCategoryId());
        Assert.assertEquals("Public Interest Feed", channel.getTitle());
        Assert.assertEquals("Public Interest Video Feed, Highlighting Southwestern Virginia", channel.getDescription());
        Assert.assertEquals("http://veetle.com/clogo/a7b/48697987e6a7b/logo_0_20100914150757_4c8ff23d91b80.jpg", channel.getSmallLogoUrl());
        Assert.assertEquals("http://veetle.com/clogo/a7b/48697987e6a7b/logo_xl_0_20100914150757_4c8ff23da7117.jpg", channel.getLargeLogoUrl());

        Assert.assertEquals(1827182809, channel.getUserId());
        Assert.assertEquals("Barbara", channel.getUserName());
        Assert.assertEquals("http://veetle.com/profimg/defaultAvatar_md.png", channel.getUserAvatarUrl());
        Assert.assertEquals("http://www.google.com", channel.getUserWebsite());

        Assert.assertEquals(1214526343, channel.getCreationTime());
        Assert.assertEquals(1328257344, channel.getBroadCastStartTime());

        Assert.assertEquals(351643, channel.getNumberOfViews());
        Assert.assertEquals(0, channel.getNumberOfComments());
        Assert.assertEquals(-12, channel.getPopularityIndex());

        Assert.assertEquals("network", channel.getSourceType());
        Assert.assertEquals(204551, channel.getBitRate());
        Assert.assertEquals(100, channel.getSignalQuality());
    }

    @Test
    public void testParsePlaylist() throws ParseChannelException {

        Channel channel = new DefaultChannelParser().parseChannel(channelWithPlaylistJson);

        Assert.assertNotNull(channel);
        Assert.assertNotNull(channel.getPlaylist());
        Assert.assertEquals(2, channel.getPlaylist().size());

        PlayListItem item;

        item = channel.getPlaylist().get(0);

        Assert.assertEquals("4e3f5e486080b", item.getChannelId());
        Assert.assertEquals(0, item.getIndex());
        Assert.assertEquals("Is Not What U Think", item.getTitle());
        Assert.assertEquals("", item.getDescription());
        Assert.assertEquals(89000, item.getDuration());
        Assert.assertEquals(0, item.getStartTime());

        item = channel.getPlaylist().get(1);

        Assert.assertEquals("4e3f5e486080b", item.getChannelId());
        Assert.assertEquals(1, item.getIndex());
        Assert.assertEquals("The Iron Lady+ ( 2012)", item.getTitle());
        Assert.assertEquals("", item.getDescription());
        Assert.assertEquals(6298000, item.getDuration());
        Assert.assertEquals(1328444121000l, item.getStartTime());

    }
}
