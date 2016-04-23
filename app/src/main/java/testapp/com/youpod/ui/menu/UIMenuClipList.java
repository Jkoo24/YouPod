package testapp.com.youpod.ui.menu;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.PlaylistItem;
import testapp.com.youpod.R;
import testapp.com.youpod.ui.menu.adapter.CliplistAdapter;
import testapp.com.youpod.ui.menu.adapter.PlaylistAdapter;
import testapp.com.youpod.youtube.YoutubeListManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class UIMenuClipList  extends UIMenuFragment
{
    private MainActivity baseAct;
    private PlaylistItem currentPlaylist;

    private ListView uiVideoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clip_list,
                container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        baseAct = (MainActivity) context;

        YoutubeListManager.Instance().setDelegate(this);
    }

    @Override
    public void onDetach ()
    {
        super.onDetach();

        YoutubeListManager.Instance().removeDelegate(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uiVideoList = (ListView) view.findViewById(R.id.list_view_clips);

        //change title text and poluate clip list
        currentPlaylist = YoutubeListManager.Instance().getSelectedPlaylist();
        baseAct.updateToolbar(currentPlaylist.getMetaData().title, R.menu.menu_cliplist);
        populate();
    }

    public void inspectPlaylist(View v)
    {
        System.out.println("clicked");
        baseAct.goToFragment(new UIMenuPlaylists(), false);
    }

    @Override
    public void onPlaylistListUpdated(String id)
    {
        System.out.println("UIMenuClipList.onPlaylistListUpdated() was informed " + id);
        populate();
    }

    private void populate()
    {
        CliplistAdapter itemsAdapter = new CliplistAdapter(baseAct, currentPlaylist.getVideoList());
        uiVideoList.setAdapter(itemsAdapter);
    }
}
