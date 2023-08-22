package com.kuro15.jo.aeindexlao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Jo on 09-10-18.
 */
public class SplashscreenActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent =  new Intent(SplashscreenActivity.this,SearchActivity.class);
                SplashscreenActivity.this.startActivity(myIntent);
            }
        },1000);

    }
}