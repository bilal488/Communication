package com.example.smarttechmardan.communication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////COMPOSE MESSAGE////////////////////////////

        sendBtn = (Button)findViewById(R.id.buttonSend);
        txtphoneNo = (EditText)findViewById(R.id.editTextNumber);
        txtMessage = (EditText)findViewById(R.id.editTextMessage);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
            }
        });

        ////////////////////////MESSAGE APP///////////////////////////////
        Button startBtn = (Button)findViewById(R.id.buttonMessage);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });

        ////////////////////////CALL APP///////////////////////////////
        Button button = (Button)findViewById(R.id.buttonCall);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:"));
                startActivity(call);
            }
        });

        //////////////////////////WEB APP/////////////////////////////
        Button buttonweb = (Button)findViewById(R.id.buttonWeb);
        buttonweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http:..www.google.com"));
                startActivity(i);
            }
        });

        //////////////////////////EMAIL APP//////////////////////////
        Button startBtn1 = (Button)findViewById(R.id.buttonEmail);
        startBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }////////////////////ON CREATE END//////////////////////////

    ///////////////////////////COMPOSE MESSAGE///////////////////////////////////
    protected void sendSMSMessage(){
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();
        Toast.makeText(this, "sending message", Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo,null,message,null,null);
        Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
    }
    //////////////////////////MESSAGE APP////////////////////////////////////
    protected void sendSMS(){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address",new String(""));
        smsIntent.putExtra("sms_body","");

        try{
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...","");
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(), "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
    //////////////////////////EMAIL APP/////////////////////////////////////
    protected void sendEmail(){
        Log.i("Seand email","");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"subbject");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Write Here");

        try{
            startActivity(Intent.createChooser(emailIntent,"Send mail..."));
            finish();
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    ///////////////////////////////////////////////////////////////////////
}
