# Veetle API

This is a simple API for checking what's running on Veetle.

What it does:

- Load a list of channels that are currently online
- Load details for each channel (bitrate, popularity, broadcaster, logos, playlist...)
- Create a schedule from a channels playlist

## Getting started

    // Create the API object
    VeetleAPI api = new VeetleAPI();
    // Load the channel list
    List<String> channelIds = api.getChannelList();
    // Load details for a channel
    Channel channel = api.getChannel(channelIds.get(0));
    // Do something with it
    System.out.println("Title: " + channel.getTitle());
    System.out.println("Description: " + channel.getDescription());
    System.out.println("Broadcaster: " + channel.getUserName());
    System.out.println("View URL: http://veetle.com/index.php/channel/view#" + channel.getChannelId());

## Dependencies

- JSON Simple http://code.google.com/p/json-simple/
- Transmorph http://transmorph.sourceforge.net/
