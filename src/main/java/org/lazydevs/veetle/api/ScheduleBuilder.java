package org.lazydevs.veetle.api;

import org.lazydevs.veetle.api.model.Channel;
import org.lazydevs.veetle.api.model.PlayListItem;
import org.lazydevs.veetle.api.model.ScheduleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sascha
 * Date: 03.01.12
 * Time: 02:16
 * To change this template use File | Settings | File Templates.
 *
 * Pretty straight-forward recreation of the JavaScript code in the veetle channel page that creates a schedule from the playlist items
 */
public class ScheduleBuilder {

    public static void createSchedule(Channel channel) {

        List<PlayListItem> playlist = channel.getPlaylist();

        if (playlist == null || playlist.size() == 0) {

            channel.setSchedule(new ArrayList<ScheduleItem>());
            return;
        }

        // Create basic schedule
        List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();

        for (PlayListItem playListItem : playlist) {
            schedule.add(new ScheduleItem(playListItem));
        }

        // Fill in start times for schedule items based on the play list item that is currently running

        // Find the item that is currently running
        int currentPlaylistItemIndex = -1;

        for (int index = 0; index < schedule.size(); index++) {
            if (schedule.get(index).getStartTime() > 0) {
                currentPlaylistItemIndex = index;
                break;
            }
        }

        if (currentPlaylistItemIndex == -1) {
            schedule.get(0).setStartTime(channel.getStartTime());
            currentPlaylistItemIndex = 0;
        }

        // Create start times for the items after the current item
        ScheduleItem lastScheduleItem = schedule.get(currentPlaylistItemIndex);

        for (int index = currentPlaylistItemIndex + 1; index < schedule.size(); index++) {
            schedule.get(index).setStartTime(lastScheduleItem.getStartTime() + lastScheduleItem.getPlayListItem().getDuration());
            lastScheduleItem = schedule.get(index);
        }

        // Create start times for the items before the current item
        lastScheduleItem = schedule.get(currentPlaylistItemIndex);

        for (int index = currentPlaylistItemIndex - 1; index >= 0; index--) {
            schedule.get(index).setStartTime(lastScheduleItem.getStartTime() - schedule.get(index).getPlayListItem().getDuration());
            lastScheduleItem = schedule.get(index);
        }

        // Loop start times until the last items end time is after the current time
        long currentMillis = System.currentTimeMillis();
        long playListLength = calculatePlayListLength(playlist);

        if (playListLength > 0) {

            while (currentMillis > schedule.get(schedule.size() - 1).getStartTime() + schedule.get(schedule.size() - 1).getPlayListItem().getDuration()) {

                for (ScheduleItem item : schedule) {
                    item.setStartTime(item.getStartTime() + playListLength);
                }
            }
        }

        channel.setSchedule(schedule);

        loopSchedule(channel);
    }

    public static void loopSchedule(Channel channel) {

        // Shift items until the currently played item is at the top if
        // current time is between start time of first item and end time of last item
        long currentMillis = System.currentTimeMillis();
        List<ScheduleItem> schedule = channel.getSchedule();

        if (schedule != null && schedule.size() > 0 &&
                currentMillis > schedule.get(0).getStartTime() &&
                currentMillis < schedule.get(schedule.size() - 1).getStartTime() + schedule.get(schedule.size() - 1).getPlayListItem().getDuration()) {

            int currentPlaylistItemIndex = 0;
            ScheduleItem lastScheduleItem;
            long playListLength = calculatePlayListLength(channel.getPlaylist());

            for (currentPlaylistItemIndex = 0; currentPlaylistItemIndex < schedule.size(); currentPlaylistItemIndex++) {

                lastScheduleItem = schedule.get(currentPlaylistItemIndex);

                if (currentMillis >= lastScheduleItem.getStartTime() && currentMillis < lastScheduleItem.getStartTime() + lastScheduleItem.getPlayListItem().getDuration())
                    break;
            }

            for (int index = 0; index < currentPlaylistItemIndex; index++) {

                lastScheduleItem = schedule.remove(0);
                lastScheduleItem.setStartTime(lastScheduleItem.getStartTime() + playListLength);
                schedule.add(lastScheduleItem);
            }
        }

        // Set current schedule item
        if (channel.getSchedule().size() > 0) {
            channel.setCurrentItem(channel.getSchedule().get(0));
        }
    }

    public static long calculatePlayListLength(List<PlayListItem> playlist) {

        long playListLength = 0;

        for (PlayListItem item : playlist) {
            playListLength += item.getDuration();
        }

        return playListLength;
    }
}
