package my.android.user.taskapps;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by madihatul aqilah on 6/4/2017.
 */

public class viewtask_detail extends Activity {

    Task task;
    private EditText textName;
    private EditText textDescription;
    private TextView dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_viewtask_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = this.getIntent();
        this.task = (Task) intent.getSerializableExtra("task1");

        this.textName = (EditText)this.findViewById(R.id.NameET);
        this.textDescription = (EditText)this.findViewById(R.id.DescriptionET);
        this.dt = (TextView)this.findViewById(R.id.txtdate);

        this.textName.setText(task.getName());
        this.textDescription.setText(task.getDescription());
        this.dt.setText(task.getDate());



    }

    public void go_back (View v)
    {
        Intent intent = new Intent(getApplicationContext(), viewtask.class);
        startActivity(intent);

    }






}
