package com.example.kimsoohyun.planiotver01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by kimsoohyun on 2017-08-03.
 */
public class splashActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_splash);

        super.onCreate(savedInstanceState);
        try{
            Thread.sleep(8000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }




}
