package testapp.com.youpod;

import java.util.ArrayList;

/**
 * Created by Jeremy on 4/21/2016.
 */
public class PlaylistItem
{
    //class for storing meta data about playlist
    public class MetaData
    {
        public String id, title;

        public MetaData(String id, String title)
        {
            this.id = id;
            this.title = title;
        }
    }

    private ArrayList<VideoItem> videoList = new ArrayList<VideoItem>();
    public ArrayList<VideoItem> getVideoList(){return videoList;}

    private MetaData metaData;
    public MetaData getMetaData(){return metaData;}

    public PlaylistItem(String id, String title)
    {
        metaData = new MetaData(id, title);
    }

    public void updateVideoData (ArrayList<VideoItem> list)
    {
        videoList = list;
    }
}
