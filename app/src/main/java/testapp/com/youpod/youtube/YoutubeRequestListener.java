package testapp.com.youpod.youtube;

import testapp.com.youpod.PlaylistItem;

/**
 * Created by Jeremy on 4/22/2016.
 */
public interface YoutubeRequestListener
{
    public void onRequestPlayistRecieved(PlaylistItem data);
    public void onRequestPlayistListRecieved();
    public void onRequestFailed(String message);
}
