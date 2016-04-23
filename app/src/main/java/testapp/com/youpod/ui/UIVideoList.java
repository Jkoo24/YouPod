package testapp.com.youpod.ui;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import java.util.List;

import testapp.com.youpod.R;
import testapp.com.youpod.VideoItem;

/**
 * Created by Jeremy on 4/17/2016.
 */
public class UIVideoList
{
    private ListView list;
    private Activity activity;

    public UIVideoList(Activity activity)
    {
        this.activity = activity;

        //list = (ListView) activity.findViewById(R.id.listView);
    }

    public void populate(List<VideoItem> items)
    {
        ArrayList<String> a = new ArrayList<String>();

        for(VideoItem item: items)
        {
            a.add(item.getTitle());
        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                activity,
                android.R.layout.simple_list_item_1,
                a );

        list.setAdapter(arrayAdapter);
    }
}
