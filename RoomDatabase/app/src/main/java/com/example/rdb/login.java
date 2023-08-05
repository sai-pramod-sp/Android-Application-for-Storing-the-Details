package com.example.rdb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class login extends Activity {

    private EditText edtTxtEmail, edid, edtTxtPassword, edtusername;
    EmpDao empDao;

    RecyclerView recyclerView;
    private Button btnregistration;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtusername = findViewById(R.id.username);
        edid = findViewById(R.id.empid1);
        edtTxtPassword = findViewById(R.id.password);
        edtTxtEmail = findViewById(R.id.email);

        EmpdatabaseHelper mydb = Room.databaseBuilder(this, EmpdatabaseHelper.class, "empdetails")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        empDao = mydb.empDao();

        btnregistration =findViewById(R.id.register);

        EmpdatabaseHelper empdatabaseHelper = EmpdatabaseHelper.getEmpdatabaseHelper(this);

        btnregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername.getText().toString();
                String id = edid.getText().toString();
                String password = edtTxtPassword.getText().toString();
                String email = edtTxtEmail.getText().toString();
                if (username.isEmpty())
                    edtusername.setError("Email should'nt be empty");
                else if (id.isEmpty())
                    edid.setError("Id No should'nt be empty");
                else if (password.isEmpty())
                    edtTxtPassword.setError("Password should'nt be empty");
                else if(email.isEmpty())
                    edtTxtEmail.setError("Email should'nt be empty");
                else {
                    EmpEntity entity = new EmpEntity();
                    entity.setUsername(edtusername.getText().toString());
                    entity.setEmpid((edid.getText().toString()));
                    entity.setPassword((edtTxtPassword.getText().toString()));
                    entity.setEmail(edtTxtEmail.getText().toString());

                    empDao.insertdetails(entity);

                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

}
