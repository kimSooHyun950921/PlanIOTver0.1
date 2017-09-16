package com.example.kimsoohyun.planiotver01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Item.MyPlantItem;

public class MenuActivity extends AppCompatActivity {

    private Intent intentFromMyPlantItem;
    private MyPlantItem plantItem;
    private ImageView view;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFromMyPlantItem = getIntent();

        view = (ImageView)findViewById(R.id.imageView);
        text = (EditText)findViewById(R.id.plantName);

        text.setText(intentFromMyPlantItem.getStringExtra("name"));




        Glide.with(this)
                .load(intentFromMyPlantItem.getStringExtra("img"))
                .into(view);








        /*식물이름바꿔주기*/


    }
}
