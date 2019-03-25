package com.haribooo.bookmyparcel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class SenderActivity extends Activity {
    public Button ba,send;
    public EditText sndname,sndcomp,sndstrt,sndarea,sndcity,sndpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        sndname = (EditText) findViewById(R.id.sndname);
        sndcomp = (EditText) findViewById(R.id.sndcomp);
        sndstrt = (EditText) findViewById(R.id.sndstrt);
        sndarea = (EditText) findViewById(R.id.sndarea);
        sndcity = (EditText) findViewById(R.id.sndcity);
        sndpin = (EditText) findViewById(R.id.sndpin);
        ba=(Button) findViewById(R.id.ba);
        send=(Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sndname.length()==0||sndcomp.length()==0||sndstrt.length()==0||sndarea.length()==0||sndcity.length()==0||sndpin.length()!=6)
                {
                if (sndname.getText().length()==0)
                {
                    sndname.setError("Enter Name");

                }
                if (sndcomp.getText().length()==0){
                    sndcomp.setError("Enter Company Name");

                }
                if (sndstrt.getText().length()==0){
                    sndstrt.setError("Enter Street");

                }
                if(sndarea.getText().length()==0){
                    sndarea.setError("Enter Area");

                }
                if(sndcity.getText().length()==0){
                    sndcity.setError("Enter City");

                }
                if(sndpin.getText().length()!=6){
                    sndpin.setError("Enter Pincode");

                }

                    Toast.makeText(getBaseContext(), "Fill Details properly", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    try {
                        FileOutputStream fileout = openFileOutput("parcel.txt", MODE_APPEND);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.append(sndname.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(sndcomp.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(sndstrt.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(sndarea.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(sndcity.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(sndpin.getText().toString());
                        outputWriter.append("\n");

                        outputWriter.close();
                        Intent intent = new Intent(SenderActivity.this, DestinationActivity.class);
                        startActivity(intent);

                    } catch(Exception e){
                        e.printStackTrace();
                    }
                       }
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(SenderActivity.this,SendnumActivity.class);
                startActivity(j);
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,SenderActivity.class);
        startActivity(intent);
    }
/*
         public void NxtBtn(View v) {

            try {
                FileOutputStream fileout = openFileOutput("parcel.txt", MODE_APPEND);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.append(sndname.getText().toString());
                outputWriter.append("\n");
                outputWriter.append(sndcomp.getText().toString());
                outputWriter.append("\n");
                outputWriter.append(sndstrt.getText().toString());
                outputWriter.append("\n");
                outputWriter.append(sndarea.getText().toString());
                outputWriter.append("\n");
                outputWriter.append(sndcity.getText().toString());
                outputWriter.append("\n");
                outputWriter.append(sndpin.getText().toString());
                outputWriter.append("\n");

                outputWriter.close();
                Intent intent = new Intent(SenderActivity.this, DestinationActivity.class);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
  */

}
