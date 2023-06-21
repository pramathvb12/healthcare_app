package com.example.healthcare_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtDetailActivity extends AppCompatActivity {

    TextView t1;
    ImageView img;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_detail);

        t1=findViewById(R.id.textViewart2);
        bt=findViewById(R.id.buttonbackart2);
        img=findViewById(R.id.imageView);

        Intent it=getIntent();
        t1.setText(it.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ArtDetailActivity.this,ArticleActivity.class));
            }
        });
    }
}