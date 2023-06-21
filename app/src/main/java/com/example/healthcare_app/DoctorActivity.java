package com.example.healthcare_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        CardView exit = findViewById(R.id.cardback);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorActivity.this,HomeActivity.class));
            }
        });

        CardView famdoc = findViewById(R.id.familyDoc);
        famdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(DoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
            }
        });
        CardView dietdoc = findViewById(R.id.DietDoc);
        dietdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(DoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Dietitian");
                startActivity(it);
            }
        });
        CardView cardoc = findViewById(R.id.CarDoc);
        cardoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(DoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });
        CardView dendoc = findViewById(R.id.DenDoc);
        dendoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(DoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });
        CardView surdoc = findViewById(R.id.SurDoc);
       surdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(DoctorActivity.this,DoctorDetailActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });
    }
}