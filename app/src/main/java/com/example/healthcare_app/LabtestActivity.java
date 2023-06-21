package com.example.healthcare_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabtestActivity extends AppCompatActivity {

    private String[][] packages ={
            {"package 1 : Full Body Checkup "," "," "," ","1000"},
            {"package 2 : Blood-Sugar Test "," "," "," ","700"},
            {"package 3 : Thyroid Check "," "," "," ","600"},
            {"package 1 : Immunity Check "," "," "," ","800"}

    };
    private String[] pack_detail={
            "Blood Glucose Fasting\n"+
                    "Complete Homogram\n"+
                    "Iron Studies\n"+
                    "Kidney Test\n"+
                    "Lipid profile\n"+
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "Thyroid profile-Total (T3,T4,TSH ultrasensitive)",
            "complete Homogram\n"+
                    "Iron Studies\n"+
                    "Kidney Test\n"+
                    "Lipid profile\n"+
                    "Liver Function Test"

    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button cart,back;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest);

        cart=findViewById(R.id.buttonLT);
        back=findViewById(R.id.buttonbacklt);
        lv=findViewById(R.id.listviewLT);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabtestActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost"+packages[i][4]+"/-");
            list.add(item);

        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int []{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});


        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =new Intent(LabtestActivity.this,LabDetailActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",pack_detail[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabtestActivity.this,CartActivity.class));
            }
        });
    }
}