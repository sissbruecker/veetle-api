package org.lazydevs.veetle.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 28.05.11
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 *
 * Data object for a veetle channel.
 */
public class Channel implements Serializable {

    private boolean isPublic;

    private String channelId;
    private int categoryId;
    private String title;
    private String description;
    private String smallLogoUrl;
    private String largeLogoUrl;

    private long userId;
    private String userName;
    private String userAvatarUrl;
    private String userWebsite;

    private long creationTime;
    private long broadCastStartTime;

    private long numberOfViews;
    private long numberOfComments;
    private long popularityIndex;

    private String sourceType;
    private long bitRate;
    private int signalQuality;

    private long startTime;

    private List<PlayListItem> playlist;

    private List<ScheduleItem> schedule;

    private ScheduleItem currentItem;

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getSmallLogoUrl() {
        return smallLogoUrl;
    }

    public void setSmallLogoUrl(String smallLogoUrl) {
        this.smallLogoUrl = smallLogoUrl;
    }

    public String getLargeLogoUrl() {
        return largeLogoUrl;
    }

    public void setLargeLogoUrl(String largeLogoUrl) {
        this.largeLogoUrl = largeLogoUrl;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getUserWebsite() {
        return userWebsite;
    }

    public void setUserWebsite(String userWebsite) {
        this.userWebsite = userWebsite;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getBroadCastStartTime() {
        return broadCastStartTime;
    }

    public void setBroadCastStartTime(long broadCastStartTime) {
        this.broadCastStartTime = broadCastStartTime;
    }

    public long getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(long numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public long getPopularityIndex() {
        return popularityIndex;
    }

    public void setPopularityIndex(long popularityIndex) {
        this.popularityIndex = popularityIndex;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getBitRate() {
        return bitRate;
    }

    public void setBitRate(long bitRate) {
        this.bitRate = bitRate;
    }

    public int getSignalQuality() {
        return signalQuality;
    }

    public void setSignalQuality(int signalQuality) {
        this.signalQuality = signalQuality;
    }

    public List<PlayListItem> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlayListItem> playlist) {
        this.playlist = playlist;
    }

    public List<ScheduleItem> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleItem> schedule) {
        this.schedule = schedule;
    }

    public ScheduleItem getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(ScheduleItem currentItem) {
        this.currentItem = currentItem;
    }

    public Channel() {
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Channel && ((Channel)obj).getChannelId().equalsIgnoreCase(channelId);
    }
}
