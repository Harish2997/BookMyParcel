package com.haribooo.bookmyparcel;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DetailsActivity extends Activity {
public Button button1, button2, button3, button4, button5,bat;
    public EditText sndr;
public CheckBox ck;
    static final int REQUEST_CODE = 1;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sndr = (EditText) findViewById(R.id.sndr);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        bat = (Button) findViewById(R.id.bat);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "xxxxx"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Details");
                intent.putExtra(Intent.EXTRA_TEXT,sndr.getText().toString());
                startActivityForResult(intent,REQUEST_CODE);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "9876543210");
                smsIntent.putExtra("sms_body",sndr.getText().toString());
                startActivityForResult(smsIntent, REQUEST_CODE);
            }
        });

        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this,SendnumActivity.class);
                startActivity(i);
            }
        });

        addListenerOnButton();
        /*View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.button1:
                        opengmail();
                        break;
                    case R.id.button2:
                        sharesms();
                        break;

                    case R.id.button3:
                        shareTextUrl();
                        break;
                }
            }
        };
        findViewById(R.id.button1).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler); */



            sndr.setCursorVisible(false);
            sndr.setEnabled(false);
            button5.setEnabled(false);
            button5.setFocusable(true);
            button5.setFocusableInTouchMode(true);
            button5.requestFocus();

    }
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE ) {
        // Make sure the request was successful
        if (resultCode == RESULT_OK) {

         Intent comp = new Intent(DetailsActivity.this,Thank.class);
         startActivity(comp);
        }
        if(resultCode == RESULT_CANCELED){
            Intent comp = new Intent(DetailsActivity.this,Thank.class);
            startActivity(comp);

        }
    }
}
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
    public void addListenerOnButton()

    {

        ck = (CheckBox) findViewById(R.id.ck);
        ck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    button5.setEnabled(true);

                }

            }
        });
    }


    public void readmore(View v){
        Intent in = new Intent(DetailsActivity.this,TermsActivity.class);
        startActivity(in);
    }


    public void read(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("parcel.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s += readstring;
            }
            InputRead.close();
            sndr.setText(s);
            button1.setEnabled(true);
            button2.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*

    private void shareTextUrl() {

        }
    private void sharesms(){
        }

    private void opengmail(){
            }
*/
}


