package testapp.com.youpod.youtube;

import java.util.ArrayList;

import testapp.com.youpod.PlaylistItem;
import testapp.com.youpod.VideoItem;

/**
 * Created by Jeremy on 4/22/2016.
 */
public interface YoutubeRequestListener
{
    public void onRequestPlayistRecieved(PlaylistItem data);
    public void onRequestPlayistListRecieved(String playlistId, ArrayList<VideoItem> a);
    public void onRequestFailed(String message);
}
