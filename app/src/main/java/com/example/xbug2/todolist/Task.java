package com.example.xbug2.todolist;

/**
 * Created by xbug2 on 4/22/2018.
 */

public class Task {

    private String name, time;

    public Task(){

    }

    public Task(String name, String time){
        this.name = name;
        this.time = time;
    }

    public String getName(){return name;}
    public String getTime(){return time;}

    public void setTime(String time){
        this.time = time;
    }
    public void setName(String name){
        this.name = name;
    }
}
