package com.example.wastent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Verify extends AppCompatActivity {
    Button btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        btnEnd = findViewById(R.id.buttonBack);

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(Verify.this, Login.class);
                startActivity(intToLogin);
            }
        });
    }
}