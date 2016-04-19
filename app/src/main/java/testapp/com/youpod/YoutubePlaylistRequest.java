package testapp.com.youpod;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.Playlist;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 4/17/2016.
 */
public class YoutubePlaylistRequest extends AsyncTask<Object, Void,  List<PlaylistItem>>
{
    private YoutubeListManager listMan;

    protected  List<PlaylistItem> doInBackground(Object... params)
    {
        try {
            long time = System.currentTimeMillis();
            listMan = (YoutubeListManager) params[0];
            YouTube youtube = (YouTube) params[1];
            String playlistId = (String) params[2];

            List<PlaylistItem> videoList = new ArrayList<PlaylistItem>();
            YouTube.PlaylistItems.List query;

            System.out.println("Requesting playlist data ...");

            query = youtube.playlistItems().list("id,contentDetails,snippet");
            query.setKey(YoutubeListManager.KEY);
            query.setPlaylistId(playlistId);
            query.setFields("items(contentDetails/videoId,snippet/title,snippet/publishedAt),nextPageToken,pageInfo");

            String nextToken = "";

            // Call the API one or more times to retrieve all items in the
            // list. As long as the API response returns a nextPageToken,
            // there are still more items to retrieve.
            do {
                query.setPageToken(nextToken);
                PlaylistItemListResponse response = query.execute();

                videoList.addAll(response.getItems());

                nextToken = response.getNextPageToken();
            } while (nextToken != null);

            System.out.println(System.currentTimeMillis() - time);

            return videoList;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    protected void onPostExecute( List<PlaylistItem> results)
    {
        List<VideoItem> videos = new ArrayList<VideoItem>();

        JSONObject obj;
        String id;
        String desc;

        try
        {
            for (PlaylistItem result : results)
            {
                obj = new JSONObject(result.getContentDetails());
                id = obj.getString("videoId");

                obj = new JSONObject(result.getSnippet());
                desc = obj.getString("title");

                videos.add(new VideoItem(id, desc));
            }
        }
        catch(Exception e)
        {
            Log.e(MainActivity.TAG, "Error parsing Playlist JSON. reason = " + e.getLocalizedMessage());
        }

        listMan.updatList(videos);
    }
}
