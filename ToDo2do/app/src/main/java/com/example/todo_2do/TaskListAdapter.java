package com.example.todo_2do;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ListViewHolder> {

    private final LayoutInflater mInflater;
    private List<Task> mTask;

    TaskListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_task,viewGroup);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Task taskEntry = mTask.get(position);
        String desc = taskEntry.getDesc();
        String priority = taskEntry.getPriority();
        String date = taskEntry.getDate();
        String time = taskEntry.getTime();

        holder.chkTaskTitle.setText(desc);
        holder.mDateTime.setText(date+" "+time);

    }

    @Override
    public int getItemCount() {
        if(mTask == null){
            return 0;
        }
        return mTask.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mDateStatus;
        private TextView mDateTime;
        private CheckBox chkTaskTitle;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            mDateStatus = itemView.findViewById(R.id.dateSts);
            mDateTime = itemView.findViewById(R.id.txtDateTime);
            chkTaskTitle = itemView.findViewById(R.id.chkTask);
        }
    }
}
