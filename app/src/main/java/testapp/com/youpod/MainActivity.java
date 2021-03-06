package testapp.com.youpod;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import testapp.com.youpod.ui.LayoutManager;
import testapp.com.youpod.ui.UIVideoList;
import testapp.com.youpod.ui.menu.UIMenuFragment;
import testapp.com.youpod.ui.menu.UIMenuPlaylists;
import testapp.com.youpod.youtube.YoutubeListManager;


public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "YouPod";

    private Toolbar toolbar;

    private UIVideoList list;
    private LayoutManager layoutMan;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        YoutubeListManager.Instance().setContext(this); //init

        list = new UIVideoList(this);
        //layoutMan = new LayoutManager(this);
        //list = new UIVideoList(this);
        //YoutubeListManager y = new YoutubeListManager(list);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

        UIMenuPlaylists myFrag= new UIMenuPlaylists();
        fragTransaction.add(R.id.fragment_frame, myFrag , "fragment_play_list");
        fragTransaction.commit();
    }

    public void goToFragment(UIMenuFragment f, boolean transitionLeft)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(transitionLeft)
            transaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.pop_enter_left, R.anim.pop_exit_left);
        else
            transaction.setCustomAnimations(R.anim.enter_right, R.anim.exit_right, R.anim.pop_enter_right, R.anim.pop_exit_right);

        transaction.replace(R.id.fragment_frame, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void updateToolbar(String newText, int toolBarId)
    {
        toolbar.getMenu().clear();
        getMenuInflater().inflate(toolBarId, toolbar.getMenu());
        toolbar.setTitle(newText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //dont inflate meu. let fragments dictate the toolbar context using updateToolbar
        updateToolbar("Playlists", R.menu.menu_main);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else if(id == R.id.action_refresh_list)
        {
            PlaylistItem p = YoutubeListManager.Instance().getSelectedPlaylist();
            if(p != null)
                YoutubeListManager.Instance().requestPlaylistListData(p.getMetaData().id);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
