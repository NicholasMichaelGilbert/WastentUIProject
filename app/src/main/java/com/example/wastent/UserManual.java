package com.example.wastent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class UserManual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        TextView um1 = findViewById(R.id.um1);
        TextView um2 = findViewById(R.id.um2);
        TextView um3 = findViewById(R.id.um3);
        TextView um4 = findViewById(R.id.um4);
        TextView um5 = findViewById(R.id.um5);
        TextView um6 = findViewById(R.id.um6);
        TextView um7 = findViewById(R.id.um7);
        TextView um8 = findViewById(R.id.um8);
        TextView um9 = findViewById(R.id.um9);
        TextView um10 = findViewById(R.id.um10);
        TextView um11 = findViewById(R.id.um11);
        TextView um12 = findViewById(R.id.um12);
        TextView um13 = findViewById(R.id.um13);
        TextView um14 = findViewById(R.id.um14);

        um1.setText(getText("um1"));
        um2.setText(getText("um2"));
        um3.setText(getText("um3"));
        um4.setText(getText("um4"));
        um5.setText(getText("um5"));
        um6.setText(getText("um6"));
        um7.setText(getText("um7"));
        um8.setText(getText("um8"));
        um9.setText(getText("um9"));
        um10.setText(getText("um10"));
        um11.setText(getText("um11"));
        um12.setText(getText("um12"));
        um13.setText(getText("um13"));
        um14.setText(getText("um14"));
    }

    private String getText(String name){
        String textUM = null;
        try{
            InputStream is = getAssets().open(name+".txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            textUM = new String(buffer);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return textUM;
    }
}