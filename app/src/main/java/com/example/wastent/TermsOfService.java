package com.example.wastent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class TermsOfService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_service);

        TextView terms = findViewById(R.id.terms);
        String tos = null;

        try{
            InputStream is = getAssets().open("terms.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            tos = new String(buffer);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        terms.setText(tos);
    }
}