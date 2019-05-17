package com.example.todo_2do.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String desc;

    @NonNull
    private String date;

    private String time;

    private String repeat;

    @NonNull
    private String priority;

    public Task(int id, @NonNull String desc, @NonNull String date, String time, String repeat, @NonNull String priority) {
        this.id = id;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.priority = priority;
    }

    @Ignore
    public Task(@NonNull String desc, @NonNull String date, String time, String repeat, @NonNull String priority) {
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public void setPriority(@NonNull String priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRepeat() {
        return repeat;
    }

    @NonNull
    public String getPriority() {
        return priority;
    }
}
