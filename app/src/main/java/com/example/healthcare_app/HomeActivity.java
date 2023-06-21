package com.example.healthcare_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sp =getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String username= sp.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome "+username, Toast.LENGTH_SHORT).show();

        CardView exit =findViewById(R.id.Logout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });

        CardView  finddoctor = findViewById(R.id.findDoctor);
        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,DoctorActivity.class));
            }
        });
        CardView  labtest = findViewById(R.id.LabTest);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,LabtestActivity.class));
            }
        });
        CardView  orderdet = findViewById(R.id.Order);
        orderdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,OrderDetailActivity.class));
            }
        });
        CardView  med = findViewById(R.id.BuyMedicine);
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MedicineActivity.class));
            }
        });
        CardView  art = findViewById(R.id.article);
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ArticleActivity.class));
            }
        });

    }
}