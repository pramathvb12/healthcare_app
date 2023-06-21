package com.example.healthcare_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    HashMap<String,String>item;
    ArrayList li;
    SimpleAdapter sa;
    TextView tv;
    ListView lv;
    private DatePickerDialog date;
    private TimePickerDialog time;
    private Button btn,back,btndate,btntime;
    String pack[][] ={};



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btndate=findViewById(R.id.buttoncartDate);
        btntime=findViewById(R.id.buttoncartTime);
        btn=findViewById(R.id.buttoncheckcart);
        back=findViewById(R.id.buttoncartback);
        tv=findViewById(R.id.textcost);
        lv=findViewById(R.id.editcartmulti);

        SharedPreferences sh = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String user=sh.getString("username","").toString();

        Database db =new Database(getApplicationContext(),"healthcare",null,1);

        float tamt=0;
        ArrayList dbarray = db.getCartdata(user,"lab");
        Toast.makeText(getApplicationContext(),""+dbarray,Toast.LENGTH_LONG).show();
        pack=new String[dbarray.size()][];
        for(int i=0;i<pack.length;i++){
            pack[i]=new String[5];
        }
        for(int i=0;i<dbarray.size();i++){
            String arrD=dbarray.get(i).toString();
            String[] strd=arrD.split(java.util.regex.Pattern.quote("$"));
            pack[i][0]=strd[0];
            pack[i][4]="Cost"+strd[1]+"/-";
            tamt=tamt+Float.parseFloat(strd[1]);
        }
        tv.setText("Total Cost : "+tamt);

        li=new ArrayList();
        for(int i=0;i<pack.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",pack[i][0]);
            item.put("line2",pack[i][1]);
            item.put("line3",pack[i][2]);
            item.put("line4",pack[i][3]);
            item.put("line5",pack[i][4]);
            li.add(item);
        }
        sa=new SimpleAdapter(this,li,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lv.setAdapter(sa);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(CartActivity.this,CheckActivity.class);
                it.putExtra("price",tv.getText());
                it.putExtra("date",btndate.getText());
                it.putExtra("time",btntime.getText());
                startActivity(it);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,LabtestActivity.class));

            }
        });
        initDatePicker();
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.show();
            }
        });

        initTimePicker();
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.show();
            }
        });

    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dset = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
               btndate.setText(i2 + "/" + i1 + "/" + i);
            }
        };
        Calendar cl = Calendar.getInstance();
        int y = cl.get(Calendar.YEAR);
        int m = cl.get(Calendar.MONTH);
        int d = cl.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        date = new DatePickerDialog(this, style, dset, y, m, d);
        date.getDatePicker().setMinDate(cl.getTimeInMillis() + 86400000);

    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener tset = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                btntime.setText(i+":"+i1);
            }
        };
        Calendar cl = Calendar.getInstance();
        int hr = cl.get(Calendar.HOUR);
        int min = cl.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        time = new TimePickerDialog(this, style, tset, hr, min, true);


    }
}
