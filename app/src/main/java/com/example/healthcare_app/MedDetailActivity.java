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
import android.widget.TextView;
import android.widget.Toast;

public class MedDetailActivity extends AppCompatActivity {

    TextView tvpack,tvcost;
    EditText ed1;
    Button btn,back;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_detail);

        tvcost=findViewById(R.id.textViewmed);
        tvpack=findViewById(R.id.textViewpackmed);
        ed1=findViewById(R.id.editmedmulti);
        back=findViewById(R.id.buttonmedback);
        btn=findViewById(R.id.buttonmedcart);
//        ed1.setKeyListener(null);
        Intent it=getIntent();
        tvpack.setText(it.getStringExtra("text1"));
        ed1.setText(it.getStringExtra("text2"));
        tvcost.setText("Total cost is "+it.getStringExtra("text3")+"/-");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedDetailActivity.this,MedicineActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String user =sh.getString("username","").toString();
                String prod = tvpack.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());

                Database db =new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkcart(user,prod)==1){
                    Toast.makeText(MedDetailActivity.this, "Product already exists", Toast.LENGTH_SHORT).show();
                }else{
                    db.cart(user,prod,price,"medicine");
                    Toast.makeText(MedDetailActivity.this, "Item Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MedDetailActivity.this,MedicineActivity.class));
                }
            }
        });
    }
}