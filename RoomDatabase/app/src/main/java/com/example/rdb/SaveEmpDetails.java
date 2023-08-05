package com.example.rdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;

public class SaveEmpDetails extends Activity {

    EditText etName, etAge, etPhone, etAddress;
    Button btn_save;
    EmpDao empDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_details);

        etName = findViewById(R.id.studentName);
        etAge = findViewById(R.id.studentAge);
        etPhone = findViewById(R.id.studentPhoneNumber);
        etAddress = findViewById(R.id.studentAddress);
        btn_save = findViewById(R.id.saveDetails);

        EmpdatabaseHelper mydb = Room.databaseBuilder(this, EmpdatabaseHelper.class, "empdetails")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        empDao = mydb.empDao();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmpEntity entity = new EmpEntity();
                entity.setUsername(etName.getText().toString());
                entity.setEmpid((etAge.getText().toString()));
                entity.setPassword((etPhone.getText().toString()));
                entity.setEmail(etAddress.getText().toString());

                empDao.insertdetails(entity);

                Intent intent = new Intent(SaveEmpDetails.this, EmpListActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });

    }
}
