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
import testapp.com.youpod.VideoItem;
import testapp.com.youpod.ui.menu.UIMenuClipList;
import testapp.com.youpod.youtube.YoutubeListManager;

/**
 * Created by Jeremy on 4/21/2016.
 */
public class CliplistAdapter extends ArrayAdapter<VideoItem>
{
    MainActivity mainAct;
    ArrayList<VideoItem> videos;

    public CliplistAdapter(MainActivity act, ArrayList<VideoItem> videosItems) {
        super(act, 0, videosItems);
        mainAct = act;
        videos = videosItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VideoItem video = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);
        }

        Button clipButton = (Button) convertView.findViewById(R.id.inspect_clip);
        clipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inspectPlaylist(v);
            }
        });


        // Populate the data into the template view using the data object
        clipButton.setText(video.getTitle());
        clipButton.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

    public void inspectPlaylist(View v)
    {
        System.out.println("CliplistAdapter.inspectPlaylist() clicked on = " + v.getTag());
    }
}
