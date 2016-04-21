package testapp.com.youpod.ui.menu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.services.youtube.model.PlaylistItem;

import java.util.ArrayList;

import testapp.com.youpod.Playlist;
import testapp.com.youpod.R;

/**
 * Created by Jeremy on 4/21/2016.
 */
public class PlaylistAdapter extends ArrayAdapter<Playlist>
{
    public PlaylistAdapter(Context context, ArrayList<Playlist> playlists) {
        super(context, 0, playlists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Playlist playlist = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(playlist.url);
        tvHome.setText("");
        // Return the completed view to render on screen
        return convertView;
    }
}
