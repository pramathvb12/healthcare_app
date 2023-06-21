package com.example.healthcare_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmnetActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView t1;
    private Button bt1,bt2,back,bt3;
    private DatePickerDialog dp;
    private TimePickerDialog tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointmnet);

        t1 = findViewById(R.id.textViewtitle);
        ed1 = findViewById(R.id.editTextFullName);
        ed2 = findViewById(R.id.editTextAddress);
        ed3 = findViewById(R.id.editTextContactinfo);
        ed4 = findViewById(R.id.editTextBookfees);
        bt1 = findViewById(R.id.buttonAppDate);
        bt2 = findViewById(R.id.buttonAppTime);
        bt3=findViewById(R.id.buttonAppointment);
        back=findViewById(R.id.buttonAppointmentBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fee = it.getStringExtra("text5");

        t1.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("fee is " + fee + " /-");

        initDatePicker();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.show();
            }
        });

        initTimePicker();
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmnetActivity.this,DoctorActivity.class));
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookAppointmnetActivity.this, "Appointment Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

        private void initDatePicker(){
            DatePickerDialog.OnDateSetListener dset = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    i1 = i1 + 1;
                    bt1.setText(i2 + "/" + i1 + "/" + i);
                }
            };
            Calendar cl = Calendar.getInstance();
            int y = cl.get(Calendar.YEAR);
            int m = cl.get(Calendar.MONTH);
            int d = cl.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_DARK;
            dp = new DatePickerDialog(this, style, dset, y, m, d);
            dp.getDatePicker().setMinDate(cl.getTimeInMillis() + 86400000);

        }
        private void initTimePicker(){
            TimePickerDialog.OnTimeSetListener tset = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        bt2.setText(i+":"+i1);
                }
            };
            Calendar cl = Calendar.getInstance();
            int hr = cl.get(Calendar.HOUR);
            int min = cl.get(Calendar.MINUTE);

            int style = AlertDialog.THEME_HOLO_DARK;
            tp = new TimePickerDialog(this, style, tset, hr, min, true);


        }

    }
