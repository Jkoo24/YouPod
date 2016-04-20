package testapp.com.youpod.ui.menu;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.R;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class UIMenuClipList  extends UIMenu
{
    public UIMenuClipList()
    {
        super(R.layout.playlist_list);
    }

    protected void onShow()
    {
        Button button= (Button) parentAct.findViewById(R.id.add_playlist);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "clicked");
            }
        });
    }
}
