package com.haribooo.bookmyparcel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DestinationActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public Button ba,submit;
    public EditText dstname,dstcomp,dstmob,dststrt,dstcity,dstpin,nondocl,nondocb,nondoch;
    public EditText nondocwt,decval;
    public Spinner typespin,docwt;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        typespin = (Spinner) findViewById(R.id.typespin);
        docwt = (Spinner) findViewById(R.id.docwt);
        typespin = (Spinner) findViewById(R.id.typespin);
        docwt = (Spinner) findViewById(R.id.docwt);
        ba=(Button) findViewById(R.id.ba);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dstname.length()==0||dstcomp.length()==0||dstmob.length()!=10||dststrt.length()==0||dstcity.length()==0||
                        dstpin.length()!=6||
                        nondocl.isEnabled()&&nondocl.length()==0||nondocb.isEnabled()&&nondocb.length()==0||nondoch.isEnabled()&&nondoch.length()==0||
                        nondocwt.isEnabled()&&nondocwt.length()==0||decval.isEnabled()&&decval.length()==0 ) {
                    if(dstname.getText().length()==0) {
                        dstname.setError("Enter Receiver Name");
                    }
                    if(dstcomp.getText().length()==0) {
                        dstcomp.setError("Enter Receiver Company Name");
                    }
                    if(dstmob.getText().length()!=10) {
                        dstmob.setError("Enter Receiver Mobile Number");
                    }
                    if(dststrt.getText().length()==0) {
                        dststrt.setError("Enter Street");
                    }
                    if(dstcity.getText().length()==0) {
                        dstcity.setError("Enter City");
                    }
                    if(dstpin.getText().length()!=6) {
                        dstpin.setError("Enter Pincode");
                    }
                    if(nondocl.isEnabled()&&nondocl.getText().length()==0) {
                        nondocl.setError("Enter Length in CM");
                    }
                    if(nondocb.isEnabled()&&nondocb.getText().length()==0) {
                        nondocb.setError("Enter Breadth in CM");
                    }
                    if(nondoch.isEnabled()&&nondoch.getText().length()==0) {
                        nondoch.setError("Enter Height in CM");
                    }
                    if(nondocwt.isEnabled()&&nondocwt.getText().length()==0) {
                        nondocwt.setError("Enter Weight");
                    }
                    if(decval.isEnabled()&&decval.getText().length()==0) {
                        decval.setError("Enter Declared Value in Rupee");
                    }
                    Toast.makeText(getBaseContext(), "Fill Details properly", Toast.LENGTH_SHORT).show();


                }
                else {

                    try {
                        FileOutputStream fileout = openFileOutput("parcel.txt", MODE_APPEND);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        outputWriter.append("-Dest-");
                        outputWriter.append("\n");
                        outputWriter.append(dstname.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(dstcomp.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(dstmob.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(dststrt.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(dstcity.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(dstpin.getText().toString());
                        outputWriter.append("\n");
                        outputWriter.append(typespin.getSelectedItem().toString());
                        if(docwt.isEnabled()) {
                            outputWriter.append("\r ");
                            outputWriter.append(docwt.getSelectedItem().toString());

                        }
                        if(nondocwt.isEnabled()) {
                            outputWriter.append(" L:");
                            outputWriter.append(nondocl.getText().toString());
                            outputWriter.append(" B:");
                            outputWriter.append(nondocb.getText().toString());
                            outputWriter.append(" H:");
                            outputWriter.append(nondoch.getText().toString());
                            outputWriter.append(" Wt:");
                            outputWriter.append(nondocwt.getText().toString());
                            outputWriter.append(" Value: Rs.");
                            outputWriter.append(decval.getText().toString());
                        }
                            outputWriter.close();

                            Intent in = new Intent(DestinationActivity.this, DetailsActivity.class);
                            startActivity(in);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
                ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(DestinationActivity.this,SendnumActivity.class);
                startActivity(j);
            }
        });

        dstname = (EditText) findViewById(R.id.dstname);
        dstcomp = (EditText) findViewById(R.id.dstcomp);
        dstmob = (EditText) findViewById(R.id.dstmob);
        dststrt = (EditText) findViewById(R.id.dststrt);
        dstcity = (EditText) findViewById(R.id.dstcity);
        dstpin = (EditText) findViewById(R.id.dstpin);
        nondocl = (EditText) findViewById(R.id.nondocl);
        nondocb = (EditText) findViewById(R.id.nondocb);
        nondoch = (EditText) findViewById(R.id.nondoch);
        nondocwt = (EditText) findViewById(R.id.nondocwt);
        decval = (EditText) findViewById(R.id.decval);
        typespin.setOnItemSelectedListener(this);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,DestinationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp1= String.valueOf(typespin.getSelectedItem());
        if(sp1.contentEquals("Document")) {
            List<String> list = new ArrayList<String>();
            list.add("0-500 Kg");
            list.add("500-1000 Kg");
            list.add("1000-1500 Kg");
            list.add("1500-2000 Kg");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            docwt.setAdapter(dataAdapter);
            docwt.setEnabled(true);
            nondocl.setText("");
            nondocb.setText("");
            nondoch.setText("");
            nondocwt.setText("");
            decval.setText("");
            nondocl.setEnabled(false);
            nondocb.setEnabled(false);
            nondoch.setEnabled(false);
            nondocwt.setEnabled(false);
            decval.setEnabled(false);
        }
        if(sp1.contentEquals("Parcel")) {
            docwt.setEnabled(false);
            docwt.getContext();
            nondocl.setEnabled(true);
            nondocb.setEnabled(true);
            nondoch.setEnabled(true);
            nondocwt.setEnabled(true);
            decval.setEnabled(true);

        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
/*
    public void GetBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("parcel.txt", MODE_APPEND);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.append("-Dest-");
            outputWriter.append("\n");
            outputWriter.append(dstname.getText().toString());
            outputWriter.append("\n");
            outputWriter.append(dstcomp.getText().toString());
            outputWriter.append("\n");
            outputWriter.append(dstmob.getText().toString());
            outputWriter.append("\n");
            outputWriter.append(dststrt.getText().toString());
            outputWriter.append("\n");
            outputWriter.append(dstcity.getText().toString());
            outputWriter.append("\n");
            outputWriter.append(dstpin.getText().toString());
            outputWriter.append("\n");
            outputWriter.append(typespin.getSelectedItem().toString());
            outputWriter.append("\r");
            outputWriter.append(docwt.getSelectedItem().toString());
            outputWriter.append("\r ");
            outputWriter.append(nondocl.getText().toString());
            outputWriter.append("\r");
            outputWriter.append(nondocb.getText().toString());
            outputWriter.append("\r");
            outputWriter.append(nondoch.getText().toString());
            outputWriter.append("\r");
            outputWriter.append(nondocwt.getText().toString());
            outputWriter.append("\r");
            outputWriter.append(decval.getText().toString());
            outputWriter.close();
            Intent in = new Intent(DestinationActivity.this, DetailsActivity.class);
            startActivity(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
  */


}