package com.example.kimsoohyun.planiotver01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by kimsoohyun on 2017-08-03.
 */
public class splashActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },3000);
    }




}
