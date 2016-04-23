package testapp.com.youpod.youtube;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.VideoItem;

/**
 * Created by Jeremy on 4/17/2016.
 */
public class YoutubePlaylistListRequest extends AsyncTask<Object, Void, ArrayList<VideoItem>>
{
    private YoutubeRequestListener listener;
    private String playlistId = "";

    protected  ArrayList<VideoItem> doInBackground(Object... params)
    {
        try {
            long time = System.currentTimeMillis();
            listener = (YoutubeRequestListener) params[0];
            YouTube youtube = (YouTube) params[1];
            playlistId = (String) params[2];


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

            //parse json data into video data
            ArrayList<VideoItem> videos = new ArrayList<VideoItem>();

            JSONObject obj;
            String id;
            String desc;

            for (PlaylistItem result : videoList)
            {
                obj = new JSONObject(result.getContentDetails());
                id = obj.getString("videoId");

                obj = new JSONObject(result.getSnippet());
                desc = obj.getString("title");

                videos.add(new VideoItem(id, desc));
            }


            return videos;
        }
        catch(Exception e)
        {
            listener.onRequestFailed("There was retrieving the playlist");
            return null;
        }
    }

    protected void onPostExecute( ArrayList<VideoItem> results)
    {
        listener.onRequestPlayistListRecieved(playlistId, results);
    }
}
