package com.example.xbug2.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTask extends AppCompatActivity {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
    EditText editTask;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        database = FirebaseDatabase.getInstance();
    }

    public void addButtonClicked(View view){
        editTask = (EditText) findViewById(R.id.editText);

        String name = editTask.getText().toString();

        String dateString = sdf.format(new Date());
        myRef = database.getInstance().getReference().child("Tasks");

        DatabaseReference newTask = myRef.push();
        newTask.child("name").setValue(name);
        newTask.child("time").setValue(dateString);

        Intent mainActivity = new Intent(AddTask.this, MainActivity.class);
        startActivity(mainActivity);
    }
}
