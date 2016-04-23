package testapp.com.youpod.ui.menu;

import android.support.v4.app.Fragment;

import testapp.com.youpod.PlaylistItem;
import testapp.com.youpod.youtube.YoutubeManagerListener;

/**
 * Created by Jeremy on 4/22/2016.
 */
public class UIMenuFragment extends Fragment implements YoutubeManagerListener
{
    //implement these in the fragment if you need them
    public void onPlaylistAdded(PlaylistItem item){}
}
