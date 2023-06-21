package com.example.healthcare_app;

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

public class LabDetailActivity extends AppCompatActivity {

    TextView tvpack,tvcost;
    EditText ed1;
    Button btn,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_detail);

        tvcost=findViewById(R.id.textView7);
        tvpack=findViewById(R.id.textViewpack);
        ed1=findViewById(R.id.Edittextmulti);
        back=findViewById(R.id.button2);
        btn=findViewById(R.id.button);
        ed1.setKeyListener(null);
        Intent it=getIntent();
        tvpack.setText(it.getStringExtra("text1"));
        ed1.setText(it.getStringExtra("text2"));
        tvcost.setText("Total cost is "+it.getStringExtra("text3")+"/-");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabDetailActivity.this,LabtestActivity.class));
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
                    Toast.makeText(LabDetailActivity.this, "Product already exists", Toast.LENGTH_SHORT).show();
                }else{
                    db.cart(user,prod,price,"lab");
                    Toast.makeText(LabDetailActivity.this, "Item Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabDetailActivity.this,LabtestActivity.class));
                }
            }
        });
    }
}