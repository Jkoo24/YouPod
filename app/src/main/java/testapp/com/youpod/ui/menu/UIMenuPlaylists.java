package testapp.com.youpod.ui.menu;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import testapp.com.youpod.PlaylistItem;
import testapp.com.youpod.ui.menu.adapter.PlaylistAdapter;
import testapp.com.youpod.youtube.YoutubeListManager;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.R;
import testapp.com.youpod.youtube.YoutubeManagerListener;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class UIMenuPlaylists extends UIMenuFragment
{
    private MainActivity baseAct;

    private ListView playlistList;
    private TextView newPlayListTextEntry;

    private ArrayList<PlaylistItem.MetaData> playlistItems = new ArrayList<PlaylistItem.MetaData>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_list,
                container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        baseAct = (MainActivity) context;

        //Button button= (Button) getView().findViewById(R.id.inspect_playlist);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playlistList = (ListView) view.findViewById(R.id.list_view_playlist);
        newPlayListTextEntry = (TextView) view.findViewById(R.id.new_playlist_url);
        Button addPlaylist = (Button) view.findViewById(R.id.add_playlist);


        addPlaylist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addPlaylist(v);
            }
        });

        playlistItems = YoutubeListManager.Instance().getMetaDataList();
        populate();

        //change title text
        baseAct.updateToolbar("Playlists", R.menu.menu_main);
    }

    public void addPlaylist(View v)
    {
        YoutubeListManager.Instance().setDelegate(this);
        YoutubeListManager.Instance().requestPlaylistData(newPlayListTextEntry.getText().toString());
    }

    @Override
    public void onPlaylistAdded(PlaylistItem item)
    {
        playlistItems.add(item.getMetaData());
        YoutubeListManager.Instance().removeDelegate(this);
        populate();
    }

    private void populate()
    {
        PlaylistAdapter itemsAdapter = new PlaylistAdapter(baseAct, playlistItems);
        playlistList.setAdapter(itemsAdapter);
    }
}
