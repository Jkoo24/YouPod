package testapp.com.youpod.ui.menu;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import testapp.com.youpod.MainActivity;
import testapp.com.youpod.R;

/**
 * Created by Jeremy on 4/19/2016.
 */
public class UIMenuPlaylists extends Fragment
{
    MainActivity baseAct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_list,
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
        Button button = (Button) view.findViewById(R.id.inspect_playlist);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inspectPlaylist(v);
            }
        });
    }

    public void inspectPlaylist(View v)
    {
        System.out.println("clicked");
        baseAct.goToFragment(new UIMenuClipList(), true);
    }
}
