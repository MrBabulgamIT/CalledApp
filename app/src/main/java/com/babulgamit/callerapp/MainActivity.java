package com.babulgamit.callerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button callButton;
    private EditText numberEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        callButton=findViewById(R.id.callbutton_id);
        numberEdittext=findViewById(R.id.edittext_id);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Intent.ACTION_CALL);
                String number= numberEdittext.getText().toString();

                if (number.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Inter the Number", Toast.LENGTH_SHORT).show();

                }
                else {
                    intent.setData(Uri.parse("tel:"+number));
                }



                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "Please Grant Permission", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }

            }
        });
    }
    private void requestPermission(){

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);

    }
}