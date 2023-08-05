package com.example.rdb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.room.Room;

public class DetailsUpdateActivity extends Activity {

    EditText etName, etid, etPass, etemail;
    ImageButton btndelname, btndelid, btndelpas, btndelemail;
    Button btn_save,btn_delete;
    EmpEntity entity;
    EmpDao empDao;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etName = findViewById(R.id.userName);
        etid = findViewById(R.id.id);
        etPass = findViewById(R.id.password);
        etemail = findViewById(R.id.email);
        btn_save = findViewById(R.id.btn_saveDetails);
        btn_delete = findViewById(R.id.btn_DeleteDetails);

        btndelname = findViewById(R.id.usbtn);
        btndelid = findViewById(R.id.idbtn);
        btndelpas = findViewById(R.id.pasbtn);
        btndelemail = findViewById(R.id.ebtn);

        EmpdatabaseHelper mydb = Room.databaseBuilder(this, EmpdatabaseHelper.class, "empdetails")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        empDao = mydb.empDao();

        entity = (EmpEntity) getIntent().getSerializableExtra("studentDetails");

    }

    @Override
    protected void onResume() {
        super.onResume();
        etName.setText(entity.getUsername());
        etid.setText(String.valueOf(entity.getEmpid()));
        etPass.setText(String.valueOf(entity.getPassword()));
        etemail.setText(entity.getEmail());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entity.setUsername(etName.getText().toString());
                entity.setEmpid(etid.getText().toString());
                entity.setPassword((etPass.getText().toString()));
                entity.setEmail((etemail.getText().toString()));
                empDao.updatedetails(entity);
                startActivity(new Intent(DetailsUpdateActivity.this, EmpListActivity.class));
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                empDao.deletedetails(entity);
                startActivity(new Intent(DetailsUpdateActivity.this, EmpListActivity.class));

            }
        });

        btndelname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setUsername("");
                empDao.updatedetails(entity);
                startActivity(new Intent(DetailsUpdateActivity.this, EmpListActivity.class));
            }
        });

        btndelid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setEmpid("");
                empDao.updatedetails(entity);
                startActivity(new Intent(DetailsUpdateActivity.this, EmpListActivity.class));
            }
        });

        btndelpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setPassword("");
                empDao.updatedetails(entity);
                startActivity(new Intent(DetailsUpdateActivity.this, EmpListActivity.class));
            }
        });

        btndelemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setEmail("");
                empDao.updatedetails(entity);
                startActivity(new Intent(DetailsUpdateActivity.this, EmpListActivity.class));
            }
        });

    }

}
