package my.android.user.taskapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by madihatul aqilah on 6/4/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // database version
    private static final int DB_VERSION = 1;


    // Database Information
    private static final String DB_NAME = "TASK";

    //Table Name
    public static final String TABLE_NAME = "TASKLIST";

    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String DATE= "date";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Script.
        String script1 = "CREATE TABLE " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + DESCRIPTION + " TEXT, " + DATE + " TEXT);";

        // Execute Script.
        db.execSQL(script1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);

    }


    public void addTask (Task task) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, task.getName());
        values.put(DESCRIPTION, task.getDescription());
        values.put(DATE, task.getDate());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);

        // Closing database connection
        db.close();
    }

    public List<Task> getAllTask() {

        List<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setDate(cursor.getString(3));
                // Adding note to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        // return note list
        return taskList;
    }

    public int updateTask(Task task) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, task.getName());
        values.put(DESCRIPTION, task.getDescription());
        values.put(DATE, task.getDate());

        // updating row
        return db.update(TABLE_NAME, values, ID + " = ?",
                new String[]{String.valueOf(task.getTaskId())});

    }

    public void deleteTask(Task task) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(task.getTaskId())});
        db.close();
    }


}
