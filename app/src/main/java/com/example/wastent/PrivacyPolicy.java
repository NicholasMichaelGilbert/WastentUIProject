package com.example.wastent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        TextView terms = findViewById(R.id.pp);
        String pp = null;

        try{
            InputStream is = getAssets().open("pp.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            pp = new String(buffer);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        terms.setText(pp);
    }
}