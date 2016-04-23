package testapp.com.youpod.youtube;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.PlaylistItem;

/**
 * Created by Jeremy on 4/17/2016.
 */
public class YoutubePlaylistRequest extends AsyncTask<Object, Void,  PlaylistItem>
{
    private YoutubeRequestListener listener;

    protected  PlaylistItem doInBackground(Object... params)
    {
        try {
            listener = (YoutubeRequestListener) params[0];
            YouTube youtube = (YouTube) params[1];
            String playlistId = (String) params[2];

            PlaylistItem playListData;
            YouTube.Playlists.List query;

            System.out.println("Requesting playlist data ...");

            query = youtube.playlists().list("snippet");
            query.setKey(YoutubeListManager.KEY);
            query.setId(playlistId);

            PlaylistListResponse response = query.execute();

            //create playlist item object
            JSONObject obj = new JSONObject(response.getItems().get(0).getSnippet());
            obj = obj.getJSONObject("localized");
            playListData = new PlaylistItem(playlistId, obj.getString("title"));

            return playListData;
        }
        catch(Exception e)
        {
            Log.e(MainActivity.TAG, "There was a issue parsing the playlist response. reason = " + e.getLocalizedMessage());
            listener.onRequestPlayistRecieved(null);
            return null;
        }
    }

    protected void onPostExecute( PlaylistItem results)
    {
        listener.onRequestPlayistRecieved(results);
    }
}
