package testapp.com.youpod.youtube;

import testapp.com.youpod.PlaylistItem;

/**
 * Created by Jeremy on 4/22/2016.
 */
public interface YoutubeManagerListener
{
    public void onPlaylistAdded(PlaylistItem item);
    public void onPlaylistListUpdated(String id);
}
