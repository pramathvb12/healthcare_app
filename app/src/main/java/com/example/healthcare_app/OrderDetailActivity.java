package com.example.healthcare_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private String [][] oreder_det={};
    ListView lv;
    HashMap<String,String> item;
    List li;
    SimpleAdapter sa;
    Button btn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        lv=findViewById(R.id.editordermulti);
        btn=findViewById(R.id.buttonoredrback);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailActivity.this,HomeActivity.class));
            }
        });
        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sh =getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String username=sh.getString("username","").toString();
        ArrayList dbdata =db.getOrderData(username);

        oreder_det=new String[dbdata.size()][];
        for(int i=0;i<oreder_det.length;i++){
            oreder_det[i]=new String[5];
            String arrData = dbdata.get(i).toString();
            String[] strdata=arrData.split(java.util.regex.Pattern.quote("$"));
            oreder_det[i][0]=strdata[0];
            oreder_det[i][1]=strdata[1];
            if(strdata[7].compareTo("medicine")==0){
                oreder_det[i][3]="Del:"+strdata[4];
            }else{
                oreder_det[i][3]="Del:"+strdata[4] +" "+strdata[5];
            }
            oreder_det[i][2]="Rs."+strdata[6];
            oreder_det[i][4]=strdata[7];

        }
        li=new ArrayList();
        for(int i=0;i<oreder_det.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",oreder_det[i][0]);
            item.put("line2",oreder_det[i][1]);
            item.put("line3",oreder_det[i][2]);
            item.put("line4",oreder_det[i][3]);
            item.put("line5",oreder_det[i][4]);
            li.add(item);
        }
        sa=new SimpleAdapter(this,li,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lv.setAdapter(sa);

    }
}