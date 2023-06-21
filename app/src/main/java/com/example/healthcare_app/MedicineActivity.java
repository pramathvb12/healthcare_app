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

import java.util.ArrayList;
import java.util.HashMap;

public class MedicineActivity extends AppCompatActivity {

    private String[][] pack={
            {"Healthvit capsule "," "," "," ","345"},
            {"Dolo-650 "," "," "," ","500"},
            {"Vitamin B Complex "," "," "," ","200"},
            {"Vitamin E capsule "," "," "," ","500"},
            {"Strepsils  "," "," "," ","120"},
            {"Calcium+Vitamin D3"," "," "," ","600"},
    };
//    private String[] det={
//            "Helps for insulin regulation",
//            "Dolo helps for relieve pain and fever",
//            "Relief from vit-B deficiency",
//            "Health and skin benifits",
//            "Reduce cold and sore throat",
//            "Reduce calcium deficiency and others"
//    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button cart,back;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

//        cart=findViewById(R.id.buttoncheckmed);
        back=findViewById(R.id.buttonmedback);
        lv=findViewById(R.id.editmedmulti);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<pack.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",pack[i][0]);
            item.put("line2",pack[i][1]);
            item.put("line3",pack[i][2]);
            item.put("line4",pack[i][3]);
            item.put("line5","Total cost"+pack[i][4]+"/-");
            list.add(item);

        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int []{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});


        lv.setAdapter(sa);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent it =new Intent(MedicineActivity.this,MedDetailActivity.class);
//                it.putExtra("text1",pack[i][0]);
////                it.putExtra("text2",det[i]);
//                it.putExtra("text3",pack[i][4]);
//                startActivity(it);
//            }
//        });
//        cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MedicineActivity.this,MedCartActivity.class));
//            }
//        });
    }
}