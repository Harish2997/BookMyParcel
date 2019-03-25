package com.haribooo.bookmyparcel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Thank extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);
        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(5*1000);

                    Intent i=new Intent(getBaseContext(),SendnumActivity.class);
                    startActivity(i);

                    finish();

                } catch (Exception e) {

                }
            }
        };


        background.start();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
