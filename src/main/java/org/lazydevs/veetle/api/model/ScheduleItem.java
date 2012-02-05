package org.lazydevs.veetle.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 02.06.11
 * Time: 02:27
 * To change this template use File | Settings | File Templates.
 *
 * A scheduled playlist item.
 */
public class ScheduleItem implements Serializable {

    private long id;

    private PlayListItem playListItem;

    private long startTime;

    private Date startDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlayListItem getPlayListItem() {
        return playListItem;
    }

    public void setPlayListItem(PlayListItem playListItem) {
        this.playListItem = playListItem;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;

        startDate = new Date(startTime);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        this.startTime = startDate.getTime();
    }

    public ScheduleItem() {
    }

    public ScheduleItem(PlayListItem playListItem) {
        this.playListItem = playListItem;

        if(playListItem.getStartTime() > 0) {
            this.setStartTime(playListItem.getStartTime());
        }
    }

}
