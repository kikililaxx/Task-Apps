package my.android.user.taskapps;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by madihatul aqilah on 6/4/2017.
 */

public class createtask extends Activity {

    Task task;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private int mode;
    private EditText textName;
    private EditText textDescription;

    private boolean needRefresh;

    private String dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_createtask);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.textName = (EditText)this.findViewById(R.id.NameET);
        this.textDescription = (EditText)this.findViewById(R.id.DescriptionET);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();

        dt = dateFormat.format(date);

        TextView txtd = (TextView) findViewById(R.id.txtdate);
        txtd.setText(dt);

        Intent intent = this.getIntent();
        this.task = (Task) intent.getSerializableExtra("task");
        if(task== null)  {
            this.mode = MODE_CREATE;
        } else  {
            this.mode = MODE_EDIT;
            this.textName.setText(task.getName());
            this.textDescription.setText(task.getDescription());
        }
    }


    // User Click on the Save button.
    public void buttonSaveClicked(View view)  {
        DBHelper db = new DBHelper(this);

        String name = this.textName.getText().toString();
        String desc = this.textDescription.getText().toString();



        if(name.equals("") || desc.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter task name & description", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode==MODE_CREATE ) {
            this.task= new Task(name,desc,dt);
            db.addTask(task);
        }

        if(mode==MODE_EDIT )
        {
            this.task.setName(name);
            this.task.setDescription(desc);
            this.task.setDate(dt);
            db.updateTask(task);
        }

        this.needRefresh = true;

        // Back to MainActivity.
        this.onBackPressed();
    }


    @Override
    public void finish() {

        // Create Intent
        Intent data = new Intent();

        // Request MainActivity refresh its ListView (or not).
        data.putExtra("needRefresh", needRefresh);

        // Set Result
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
