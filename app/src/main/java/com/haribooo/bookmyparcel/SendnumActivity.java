package com.haribooo.bookmyparcel;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;


public class SendnumActivity extends Activity {
public Button b,proceed,help;
    TimePicker timePicker;
    TimePickerDialog picker;
    public EditText number,pickhr;
    public int am;
    static final int READ_BLOCK_SIZE = 100;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendnum);

        number = (EditText) findViewById(R.id.number);

        pickhr=(EditText) findViewById(R.id.timee);
        pickhr.setFocusable(false);
        pickhr.setInputType(InputType.TYPE_NULL);
        pickhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ampmStr;
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                int am = cldr.get(Calendar.AM_PM);
                ampmStr = (am == 1&&am ==0) ? "" : "";

                // time picker dialog
                picker = new TimePickerDialog(SendnumActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                                pickhr.setText(String.format("%02d:%02d",sHour , sMinute ));
                            }
                        }, hour, minutes, false);
                picker.show();
            }
        });

        /*timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // display a toast with changed values of time picker
            }
        });*/

        b = (Button) findViewById(R.id.about);
        proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number.getText().length() != 10||pickhr.getText().length()==0) {

                    // add-write text into file
                    if(number.getText().length() != 10) {
                        number.setError("Enter number properly");
                    }
                    if(pickhr.getText().length() == 0) {
                        pickhr.setError("Select Time");
                    }
                    }
                    else
                {
               try {
                        FileOutputStream fileout = openFileOutput("parcel.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.append("--Sender--");
                        outputWriter.append("\n");
                        outputWriter.write(number.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(pickhr.getText().toString());
                        /*outputWriter.append(pickhr.getText().toString());
                        outputWriter.append(":");
                        outputWriter.append(pickmin.getText().toString());*/
                        outputWriter.append("\n");

                        Intent intent = new Intent(SendnumActivity.this, SenderActivity.class);
                        startActivity(intent);
                        outputWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
               }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent myintent1 = new Intent(SendnumActivity.this, AboutActivity.class);
                startActivity(myintent1);
            }

        });

     /*   pickhr.addTextChangedListener(new TextWatcher() {

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // TODO Auto-generated method stub
                        if (pickhr.getText().toString().length() == 2)     //size as per your requirement
                        {
                            pickmin.requestFocus();
                        }
                    }

                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                        // TODO Auto-generated method stub

                    }

                    public void afterTextChanged(Editable s) {
                        // TODO Auto-generated method stub
                    }

                });

*/

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


        // write text to file
       /*     public void WriteBtn(View v) {
            if(number.length() ==10)
            {
                // add-write text into file
                try {
                    FileOutputStream fileout = openFileOutput("parcel.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.append("--Sender--");
                    outputWriter.append("\n");
                    outputWriter.write(number.getText().toString());
                    outputWriter.append("\n");
                    outputWriter.append(pickhr.getText().toString());
                    outputWriter.append(":");
                    outputWriter.append(pickmin.getText().toString());
                    outputWriter.append("\n");

                    Intent intent = new Intent(SendnumActivity.this, SenderActivity.class);
                    startActivity(intent);
                    outputWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            else {
                Toast.makeText(getBaseContext(), "Fill Number and Pickup time properly", Toast.LENGTH_SHORT).show();

            }
         */

    }




