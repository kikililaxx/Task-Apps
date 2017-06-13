package my.android.user.taskapps;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Created by madihatul aqilah on 6/4/2017.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void go_create (View v)
    {
        Intent intent = new Intent(getApplicationContext(), createtask.class);
        startActivity(intent);

    }

    public void go_view (View v)
    {
        Intent intent = new Intent(getApplicationContext(), viewtask.class);
        startActivity(intent);

    }
}
