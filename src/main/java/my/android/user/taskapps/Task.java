package my.android.user.taskapps;

import java.io.Serializable;

/**
 * Created by madihatul aqilah on 6/4/2017.
 */
public class Task implements Serializable {

    //private variables
    int taskId;
    String name;
    String description;
    String date;

    // Empty constructor
    public Task(){

    }
    // constructor
    public Task(int taskId, String name, String description, String date){
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    // constructor
    public Task(String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;
    }
    // getting taskId
    public int getTaskId(){
        return this.taskId;
    }

    // setting taskId
    public void setID(int taskId){
        this.taskId = taskId;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting description
    public String getDescription(){
        return this.description;
    }

    // setting description
    public void setDescription(String description){
        this.description = description;
    }

    // getting description
    public String getDate(){
        return this.date;
    }

    // setting description
    public void setDate(String date){
        this.date = date;
    }


    @Override
    public String toString()  {
        return this.name;
    }
}
