package com.example.kimsoohyun.planiotver01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Item.DicItem;

import static com.example.kimsoohyun.planiotver01.R.id.pname;

/**
 * Created by tjdwp on 2017-08-08.
 */

public class DicInfoActivity extends AppCompatActivity{
    TextView plantName;
    ImageView plantImage;
    TextView plantExplanation;
    TextView plantHumidity;
    TextView plantLight;
    TextView plantTemper;
    Intent intentFromDicMenu;
    Object humidity;
    Object temper;
    Object light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dic_info);

        intentFromDicMenu = getIntent();
        DicItem item= intentFromDicMenu.getParcelableExtra("DicItem");

        plantName = (TextView)findViewById(pname);
        plantImage= (ImageView)findViewById(R.id.tulip);
        plantExplanation = (TextView)findViewById(R.id.pdetail) ;
        plantHumidity = (TextView)findViewById(R.id.dic_water_info_i);
        plantLight= (TextView)findViewById(R.id.dic_light_info);
        plantTemper = (TextView)findViewById(R.id.dic_temper_info);


        plantName.setText(item.getName());
        plantExplanation.setText(item.getExplanation());
        Log.i("메뉴정보습도",String.valueOf(item.getHumidity()));
        plantHumidity.setText(String.valueOf(item.getHumidity()));
        plantLight.setText(String.valueOf(item.getLight()));
        plantTemper.setText(String.valueOf(item.getTemperature()));


        Glide.with(this)
                .load(item.getDicPlantImage())
                .into(plantImage);




    }




    public void addPlant(View v){
        /*db에 저장하고 저장한내용으로 바꿔주기*/
        String plantNameToString = plantName.getText().toString();


        Intent intent = new Intent(DicInfoActivity.this,MainActivity.class);
        intent.putExtra("PLANTNAME",plantNameToString);
        intent.putExtra("PLANTIMAGE",R.drawable.tulip);
        startActivity(intent);
    }





}
