package com.example.xbug2.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTask extends AppCompatActivity {

    private String task_key = null;
    private DatabaseReference mDatabase;
    private EditText editTaskName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        task_key = getIntent().getExtras().getString("TaskId");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tasks");
        editTaskName = (EditText) findViewById(R.id.editTaskName);

        mDatabase.child(task_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String taskTitle = (String) dataSnapshot.child("name").getValue();
                editTaskName.setText(taskTitle);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void editTaskFinished(View view){
        String taskName = editTaskName.getText().toString();
        mDatabase.child(task_key).child("name").setValue(taskName);

        Intent mainActivity = new Intent(EditTask.this, MainActivity.class);
        startActivity(mainActivity);
    }
}
