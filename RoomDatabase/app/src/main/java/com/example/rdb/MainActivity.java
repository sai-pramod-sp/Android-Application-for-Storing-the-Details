package com.example.rdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtTxtEmail, edid, edtTxtPassword, edtusername;
    private Button btnRegistration, btnlogin;
    EmpDao empDao;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);

        edtusername = findViewById(R.id.username);
        edid = findViewById(R.id.empidr);
        btnRegistration = findViewById(R.id.register);
        btnlogin = findViewById(R.id.login);

        EmpdatabaseHelper mydb = Room.databaseBuilder(this, EmpdatabaseHelper.class, "empdetails")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        empDao = mydb.empDao();

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent("com.example.LOGIN");
                    startActivity(intent);
                    finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername.getText().toString();
                String id = edid.getText().toString();

                if (username.isEmpty())
                    edtusername.setError("Email should'nt be empty");
                else if (id.isEmpty()){
                    edid.setError("Id No should'nt be empty");
                }else{
                    if(empDao.login(username, id)){
                        Toast.makeText(MainActivity.this, "Successfull!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, EmpListActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Username or id Not found!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}