package testapp.com.youpod;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.common.collect.Lists;

import java.util.List;
import java.io.IOException;


/**
 * Created by Jeremy on 4/16/2016.
 */
public class YoutubeListManager
{

    private static YouTube youtube;

    private List<VideoItem> searchResults;
    private UIVideoList uiVideoList;

    public static final String KEY = "AIzaSyB2m6HojAg2t3bEulltTHQHCLRRGyK9Rco";

    public YoutubeListManager(UIVideoList uiVideoList)
    {
        this.uiVideoList = uiVideoList;

        // This OAuth 2.0 access scope allows for full read/write access to the
        // authenticated user's account.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {
            // Authorize the request.
            //OAuth.authorize(scopes, "localizations");

            youtube = new YouTube.Builder(new NetHttpTransport(),
                    new JacksonFactory(), new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest hr) throws IOException {}
            }).setApplicationName("youpod").build();



            retrievePlaylistData("PL-oTJHKXHicQ0jv37mr8D9kRFXox7-PXD");

        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
    }

    public void retrievePlaylistData(String id)
    {
        new YoutubePlaylistRequest().execute(this, youtube, id);
    }

    public void updatList(List<VideoItem> items)
    {
        searchResults = items;
        uiVideoList.populate(searchResults);
    }
}
