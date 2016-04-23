package testapp.com.youpod.ui.menu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.PlaylistItem;
import testapp.com.youpod.R;
import testapp.com.youpod.ui.menu.UIMenuClipList;

/**
 * Created by Jeremy on 4/21/2016.
 */
public class PlaylistAdapter extends ArrayAdapter<PlaylistItem>
{
    MainActivity mainAct;

    public PlaylistAdapter(MainActivity act, ArrayList<PlaylistItem> playlistItems) {
        super(act, 0, playlistItems);
        mainAct = act;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlaylistItem playlistItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_list_item, parent, false);
        }

        Button playlistButton = (Button) convertView.findViewById(R.id.inspect_playlist);
        playlistButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inspectPlaylist(v);
            }
        });


        // Populate the data into the template view using the data object
        playlistButton.setText(playlistItem.name);
        // Return the completed view to render on screen
        return convertView;
    }

    public void inspectPlaylist(View v)
    {
        mainAct.goToFragment(new UIMenuClipList(), true);
    }
}
