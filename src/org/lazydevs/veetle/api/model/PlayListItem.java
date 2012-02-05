package org.lazydevs.veetle.api.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 01.06.11
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 *
 * Data object for a playlist item of a channel
 */
public class PlayListItem implements Serializable {

    private String channelId;

    private int index;

    private String title;

    private String description;

    private long duration;

    private long startTime;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
