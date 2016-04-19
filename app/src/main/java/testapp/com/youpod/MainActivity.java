package testapp.com.youpod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.transition.Scene;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "YouPod";



    private UIVideoList list;
    private YoutubeListManager listManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        Scene scene1 = Scene.getSceneForLayout(view,
                R.layout.content_main2, this);

        final Scene scene2 = Scene.getSceneForLayout(view,
                R.layout.content_main, this);

        scene1.enter();
        list = new UIVideoList(this);
        Button button= (Button) findViewById(R.id.inspect_playlist);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene2);

                YoutubeListManager y = new YoutubeListManager(list);
            }
        });
        //list = new UIVideoList(this);
        //YoutubeListManager y = new YoutubeListManager(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
