# Veetle API

This is a simple API for checking what's running on Veetle.

What it does:
 * Load a list of channels that are currently online
 * Load details for each channel (bitrate, popularity, broadcaster, logos, playlist...)
 * Create a schedule from a channels playlist

## Getting started

Load the channel list

`List<String> channelIds = new VeetleAPI().getChannelList();`

Load details for a channel

`Channel channel = new VeetleAPI().getChannel(channelId);`