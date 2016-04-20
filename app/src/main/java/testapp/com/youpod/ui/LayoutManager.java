package testapp.com.youpod.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.common.collect.ImmutableList;

import testapp.com.youpod.R;
import testapp.com.youpod.ui.menu.UIMenu;
import testapp.com.youpod.ui.menu.UIMenuPlaylists;
import testapp.com.youpod.ui.menu.UIMenuClipList;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class LayoutManager
{
    Activity mainActivity;

    //nested menu flow. i=0 is top most menu
    public static final ImmutableList<UIMenu> layoutList = ImmutableList.of(
            new UIMenuPlaylists(),
            new UIMenuClipList()
    );

    private int currentLayout = 0;
    private LayoutInflater factory;
    private ViewGroup baseView;

    public LayoutManager(Activity mainActivity)
    {
        this.mainActivity = mainActivity;
        factory = mainActivity.getLayoutInflater();
        baseView = (ViewGroup) mainActivity.findViewById(android.R.id.content);

        //inflate base layout
        View textEntryView = factory.inflate(layoutList.get(1).getResId(), baseView);
        layoutList.get(1).didShow(mainActivity);
    }
}
