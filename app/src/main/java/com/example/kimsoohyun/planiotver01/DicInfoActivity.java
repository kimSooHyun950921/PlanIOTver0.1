package com.example.kimsoohyun.planiotver01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.example.kimsoohyun.planiotver01.Item.MyPlantItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    DatabaseReference databaseReference;
    DatabaseReference myPlantCountRef;
    DicItem item;
    String InputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dic_info);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("myPlant");
        myPlantCountRef = FirebaseDatabase.getInstance().getReference().child("myPlant").child("size");
        intentFromDicMenu = getIntent();
        item= intentFromDicMenu.getParcelableExtra("DicItem");

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
/*
*
*  String uid;
    String myPlantGallery;
    String myPlantName;
    float myPlantHumidity;
    float myPlantLight;
    float myPlantTemperatuer;
*
*
* */



    public void addPlant(View v){
        /*db에 저장하고 저장한내용으로 바꿔주기*/
        String plantNameToString = plantName.getText().toString();
        startDialog();

    }

    private void startDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DicInfoActivity.this);
        final EditText text = new EditText(this);
        dialog.setTitle("식물이름을 설정해주세요!");
        dialog.setView(text);
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addPlantCount();
                InputValue = text.getText().toString();
                long now = System.currentTimeMillis();
                String getTime = setDate(now);
                saveDataBase(getTime);
                startIntent();
            }
        });
        dialog.show();
    }

    private void addPlantCount() {
        myPlantCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object plantCount = dataSnapshot.getValue();
                Long changeToIntegerPlantCount = 0L;
                while(!plantCount.equals(changeToIntegerPlantCount)){
                    changeToIntegerPlantCount++;
                }
                myPlantCountRef.setValue(changeToIntegerPlantCount+1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void startIntent() {
        Intent intent = new Intent(DicInfoActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void saveDataBase(String getTime) {
        MyPlantItem myItem = new MyPlantItem(item.getDicPlantImage(),InputValue,item.getName(),getTime);
        databaseReference.push().setValue(myItem);

        Toast.makeText(getApplication(),InputValue,Toast.LENGTH_LONG).show();
    }

    private String setDate(long now) {
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
        return sdf.format(date);
    }


}
