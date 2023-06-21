package com.example.healthcare_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {

    private String [][] doc_detail1 ={
            {"Doctor_name : Ajit Saste","Hospital_address : Bengaluru","exp : 8","mob :9019563412","900"},
            {"Doctor_name : Ram charan","Hospital_address : Chennai","exp : 12","mob :9563412123","800"},
            {"Doctor_name : Guru Dixit","Hospital_address : Delhi","exp : 15","mob :9019563654","600"},
            {"Doctor_name : Swaroop","Hospital_address : Ududpi","exp : 4","mob :9563412987","400"}
    };
    private String [][] doc_detail2 ={
            {"Doctor_name : Pranav B","Hospital_address : Banavara","exp : 18","mob :6341213434","300"},
            {"Doctor_name : Prajwal N","Hospital_address : Madras","exp : 12","mob :9563412188","800"},
            {"Doctor_name : Dinesh Dixit","Hospital_address : Kumta","exp : 15","mob :9019563645","800"},
            {"Doctor_name : Jimmy James","Hospital_address : Kundapura","exp : 14","mob :9563412967","400"}
    };
    private String [][] doc_detail3 ={
            {"Doctor_name : Sarvjit Saste","Hospital_address : Bengaluru","exp : 8","mob :9019563421","700"},
            {"Doctor_name : Nandan S","Hospital_address : Chennai","exp : 15","mob :9563412187","800"},
            {"Doctor_name : Sameer Dixit","Hospital_address : Belagavi","exp : 16","mob :9019563653","500"},
            {"Doctor_name : Shiva Gowda","Hospital_address : Mandya","exp : 14","mob :9563412927","300"}
    };
    private String [][] doc_detail4 ={
            {"Doctor_name : Janaki P","Hospital_address : Mysore","exp : 16","mob :9019563413","1000"},
            {"Doctor_name : Sai charan","Hospital_address : Chennai","exp : 11","mob :9563412123","600"},
            {"Doctor_name : Jeeva N","Hospital_address : Goa","exp : 17","mob :9019563634","400"},
            {"Doctor_name : Nitin J N","Hospital_address : Shimogga","exp : 10","mob :9563412919","800"}
    };
    private String [][] doc_detail5 ={
            {"Doctor_name : Maria B S","Hospital_address : Pune","exp : 8","mob :9019562412","1500"},
            {"Doctor_name : Kush Raj","Hospital_address : Bihar","exp : 11","mob :9563412102","1800"},
            {"Doctor_name : Mahesh J","Hospital_address : Banglore","exp : 19","mob :9019563612","1600"},
            {"Doctor_name : Manoj S","Hospital_address : Karwar","exp : 5","mob :9563412923","1400"}
    };
    TextView t1;
    Button bt;
    String [][] doc_detail = {};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        t1=findViewById(R.id.textdd);
        bt=findViewById(R.id.buttonback);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        t1.setText(title);

        if(title.compareTo("Family Physician")==0){
            doc_detail=doc_detail1;
        }
        else if(title.compareTo("Dietitian")==0){
            doc_detail=doc_detail2;
        }
        else if(title.compareTo("Dentist")==0){
            doc_detail=doc_detail3;
        }
        else if(title.compareTo("Surgeon")==0){
            doc_detail=doc_detail4;
        }
        else{
            doc_detail=doc_detail5;
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this,DoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<doc_detail.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doc_detail[i][0]);
            item.put("line2",doc_detail[i][1]);
            item.put("line3",doc_detail[i][2]);
            item.put("line4",doc_detail[i][3]);
            item.put("line5","Cons fee "+doc_detail[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int []{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

       ListView ls = findViewById(R.id.listviewDD);
        ls.setAdapter(sa);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailActivity.this,BookAppointmnetActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doc_detail[i][0]);
                it.putExtra("text3",doc_detail[i][1]);
                it.putExtra("text4",doc_detail[i][3]);
                it.putExtra("text5",doc_detail[i][4]);
                startActivity(it);
            }
        });

    }
}