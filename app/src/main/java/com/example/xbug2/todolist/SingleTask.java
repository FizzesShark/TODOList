package com.example.xbug2.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SingleTask extends AppCompatActivity {

    private String task_key = null;
    private TextView singleTask;
    private TextView singleTime;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        task_key = getIntent().getExtras().getString("TaskId");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tasks");

        singleTask = (TextView) findViewById(R.id.singleTask);
        singleTime = (TextView) findViewById(R.id.singleTime);

        mDatabase.child(task_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String task_title = (String) dataSnapshot.child("name").getValue();
                String task_time = (String) dataSnapshot.child("time").getValue();

                singleTask.setText(task_title);
                singleTime.setText(task_time);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void deleteTask(View view) {
        mDatabase.child(task_key).removeValue();

        Intent mainActivity = new Intent(SingleTask.this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void updateTask(View view) {
        Intent editTask = new Intent(SingleTask.this, EditTask.class);
        editTask.putExtra("TaskId", task_key);
        startActivity(editTask);
    }
}
