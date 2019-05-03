package com.example.todo_2do;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mid;

    @NonNull
    @ColumnInfo(name = "desc")
    private String mDesc;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "time")
    private String mTime;

    @ColumnInfo(name = "repeat")
    private String mRepeat;

    @NonNull
    @ColumnInfo(name = "priority")
    private String mPriority;

    public Task( @NonNull String Desc, @NonNull String Date, String Time, String Repeat, @NonNull String Priority) {
        this.mDesc = Desc;
        this.mDate = Date;
        this.mTime = Time;
        this.mRepeat = Repeat;
        this.mPriority = Priority;
    }

    public int getMid() {
        return mid;
    }

    @NonNull
    public String getmDesc() {
        return mDesc;
    }

    @NonNull
    public String getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmRepeat() {
        return mRepeat;
    }

    @NonNull
    public String getmPriority() {
        return mPriority;
    }


}
