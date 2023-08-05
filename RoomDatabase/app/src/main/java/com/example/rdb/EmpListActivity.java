package com.example.rdb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EmpListActivity extends Activity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    EmpDao empDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_list);
        recyclerView = findViewById(R.id.rv_studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmpListActivity.this, SaveEmpDetails.class);
                startActivity(intent);
                finish();
            }
        });

        EmpdatabaseHelper mydb = Room.databaseBuilder(this, EmpdatabaseHelper.class, "empdetails")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        empDao = mydb.empDao();

        List<EmpEntity> taskList = empDao.getdetails();

        EmplistAdapter listAdapter = new EmplistAdapter(EmpListActivity.this, taskList);
        recyclerView.setAdapter(listAdapter);

    }

}
