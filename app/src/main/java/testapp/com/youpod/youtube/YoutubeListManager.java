package testapp.com.youpod.youtube;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import testapp.com.youpod.PlaylistItem;
import testapp.com.youpod.VideoItem;
import testapp.com.youpod.ui.UIVideoList;


/**
 * Created by Jeremy on 4/16/2016.
 */
public class YoutubeListManager implements YoutubeRequestListener
{
    public static final String KEY = "AIzaSyB2m6HojAg2t3bEulltTHQHCLRRGyK9Rco";

    private static YoutubeListManager instance;
    private static YouTube youtube;

    private ArrayList<YoutubeManagerListener> listeners = new ArrayList<YoutubeManagerListener>();
    private ArrayList<PlaylistItem> playLists = new ArrayList<PlaylistItem>();
    private boolean waitingOnResponse = false;
    private ProgressDialog fetchingDialog;
    private Context context;
    private PlaylistItem selectedPlaylist;

    //returns list of meta data for all saved playlists
    public ArrayList<PlaylistItem.MetaData> getMetaDataList()
    {
        ArrayList<PlaylistItem.MetaData> metaList = new  ArrayList<PlaylistItem.MetaData>();

        for(int i=0; i< playLists.size(); i++)
        {
            metaList.add(playLists.get(i).getMetaData());
        }

        return metaList;
    }

    public static YoutubeListManager Instance()
    {
        if(instance == null || youtube == null)
            instance = new YoutubeListManager();
        return instance;
    }

    public void setContext(Context c)
    {
        context = c;
    }

    public YoutubeListManager()
    {
        try {
            // Authorize the request.
            //OAuth.authorize(scopes, "localizations");

            youtube = new YouTube.Builder(new NetHttpTransport(),
                    new JacksonFactory(), new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest hr) throws IOException {}
            }).setApplicationName("youpod").build();

        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
            youtube = null;
        }
    }

    public void setDelegate(YoutubeManagerListener l)
    {
        if(!listeners.contains(l))
            listeners.add(l);
    }

    public void removeDelegate(YoutubeManagerListener l)
    {
        listeners.remove(l);
    }

    public void requestPlaylistData(String id)
    {
        if(!waitingOnResponse)
        {
            showFetchingDialog();
            new YoutubePlaylistRequest().execute(this, youtube, id, this);
        }
    }

    public void requestPlaylistListData(String id)
    {
        if(!waitingOnResponse)
        {
            showFetchingDialog();
            new YoutubePlaylistListRequest().execute(this, youtube, id, this);
        }
    }

    public void selectedPlaylist(String id)
    {
        selectedPlaylist = getItemById(id);
    }

    public PlaylistItem getSelectedPlaylist()
    {
        return selectedPlaylist;
    }

    private void showFetchingDialog()
    {
        waitingOnResponse = true;

        fetchingDialog = new ProgressDialog(context);
        fetchingDialog.setTitle("Loading");
        fetchingDialog.setMessage("Wait while loading...");
        fetchingDialog.show();
    }

    private void hideFetchingDialog()
    {
        waitingOnResponse = false;

        fetchingDialog.dismiss();
    }

    private PlaylistItem getItemById(String id)
    {
        for(int i=0; i < playLists.size(); i++)
        {
            if(playLists.get(i).getMetaData().id.equals(id))
                return playLists.get(i);
        }

        return null;
    }

    //listeners
    public void onRequestPlayistRecieved(PlaylistItem newItem)
    {
        boolean newEntry = true;

        //see if playlist is already in there
        for (PlaylistItem p: playLists)
        {
            if(p.getMetaData().id.equals(newItem.getMetaData().id))
                newEntry = false;
        }


        //add to list if its a new item
        if(newEntry)
        {
            playLists.add(newItem);

            //notify listeners
            for (int i=0; i < listeners.size(); i++)
                listeners.get(i).onPlaylistAdded(newItem);
        }

        hideFetchingDialog();
    }

    public void onRequestPlayistListRecieved(String playlistId, ArrayList<VideoItem> videos)
    {
        PlaylistItem p = getItemById(playlistId);

        if(p != null)
        {
            p.updateVideoData(videos);

            //notify listeners
            for (int i = 0; i < listeners.size(); i++)
                listeners.get(i).onPlaylistListUpdated(playlistId);

        }
        hideFetchingDialog();
    }

    public void onRequestFailed(String message)
    {
        hideFetchingDialog();
    }

}
