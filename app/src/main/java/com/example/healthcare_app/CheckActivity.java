package com.example.healthcare_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        e1 =findViewById(R.id.editTextBookUserName);
        e2 =findViewById(R.id.editTextBookPhone);
        e3 =findViewById(R.id.editTextBookAdd);
        e4 =findViewById(R.id.editTextBookpin);
        btn = findViewById(R.id.buttonbook);

        Intent it =getIntent();
        String[] price =it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = it.getStringExtra("date");
        String time = it.getStringExtra("time");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh =getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String user=sh.getString("username","").toString();
                Database db =new Database(getApplicationContext(),"healthcare",null,1);
                db.addorder(user,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),Integer.parseInt(e4.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removecart(user,"lab");

                Toast.makeText(CheckActivity.this, "Your Booking Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CheckActivity.this,HomeActivity.class));

            }
        });
    }
}