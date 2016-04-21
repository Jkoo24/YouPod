package testapp.com.youpod.ui.menu;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import  testapp.com.youpod.ui.menu.adapter.PlaylistAdapter;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.Playlist;
import testapp.com.youpod.R;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class UIMenuPlaylists extends Fragment
{
    private MainActivity baseAct;

    private ListView playlistList;
    private TextView newPlayListTextEntry;

    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();

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

        Button inspect = (Button) view.findViewById(R.id.inspect_playlist);
        Button addPlaylist = (Button) view.findViewById(R.id.add_playlist);


        inspect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inspectPlaylist(v);
            }
        });
        addPlaylist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addPlaylist(v);
            }
        });
    }

    public void inspectPlaylist(View v)
    {
        System.out.println("clicked");
        baseAct.goToFragment(new UIMenuClipList(), true);
    }

    public void addPlaylist(View v)
    {
        System.out.println("clicked2");
        playlists.add(new Playlist(newPlayListTextEntry.getText().toString()));

        PlaylistAdapter itemsAdapter = new PlaylistAdapter(baseAct, playlists);
        playlistList.setAdapter(itemsAdapter);
    }
}
