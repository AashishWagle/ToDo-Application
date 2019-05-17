package com.example.todo_2do.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.todo_2do.database.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert
    void insert (Task task);

    @Query("DELETE from task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY id desc")
    LiveData<List<Task>> getAllTask();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Task task);
}
