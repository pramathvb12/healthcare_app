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

public class ArticleActivity extends AppCompatActivity {

    private String S[][] ={
            {"Walk Daily","","","","Click for more"},
            {"Stop Smoking","","","","Click for more"},
            {"Eat Healthy food","","","","Click for more"},
            {"Menstrual Cramps","","","","Click for more"},
            {"Healthy Guts","","","","Click for more"},

    };
    private int[] img={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button back;
    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        back=findViewById(R.id.buttonbackart);
        ls=findViewById(R.id.editmultiart);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ArticleActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<img.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",S[i][0]);
            item.put("line2",S[i][1]);
            item.put("line3",S[i][2]);
            item.put("line4",S[i][3]);
            item.put("line5",S[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"}, new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ls.setAdapter(sa);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =new Intent(ArticleActivity.this,ArtDetailActivity.class);
                it.putExtra("text1",S[i][0]);
                it.putExtra("text2",img[i]);
                startActivity(it);
            }
        });
    }
}