package com.example.makephonecall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button call;
    EditText number;

    String userNumber;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number = findViewById(R.id.editTextNumber);
        call= findViewById(R.id.buttoncall);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 userNumber = number.getText().toString();

                 makeCall(userNumber);

            }
        });
    }

    public  void  makeCall(String userNumber)
    {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},100);
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+userNumber));
            startActivity(intent);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if(requestCode==100 && grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+userNumber));
            startActivity(intent);
        }
    }
}