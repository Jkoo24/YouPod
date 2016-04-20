package testapp.com.youpod.ui.menu;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class UIMenuClipList  extends Fragment
{
    MainActivity baseAct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clip_list,
                container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        baseAct = (MainActivity) context;

        //Button button= (Button) getView().findViewById(R.id.inspect_playlist);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.test_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inspectPlaylist(v);
            }
        });
    }

    public void inspectPlaylist(View v)
    {
        System.out.println("clicked");
        baseAct.goToFragment(new UIMenuPlaylists(), false);
    }


}
