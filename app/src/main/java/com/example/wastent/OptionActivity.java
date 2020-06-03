package com.example.wastent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class OptionActivity extends AppCompatActivity {
    Button btnLogout, btnChangePassword, btnToS, btnPP, btnUM;
    FirebaseAuth mFirebaseAuth;
    String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Intent userCredential = getIntent();
        userPassword = userCredential.getStringExtra("userPassword");

        btnLogout = findViewById(R.id.buttonSignOut);
        btnChangePassword = findViewById(R.id.buttonChangePassword);
        btnToS = findViewById(R.id.buttonToS);
        btnPP = findViewById(R.id.buttonPrivacyPolicy);
        btnUM = findViewById(R.id.buttonUserManual);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToChangePassword = new Intent(OptionActivity.this, ChangeUserPassword.class);
                intToChangePassword.putExtra("userPassword", userPassword);
                startActivity(intToChangePassword);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                Intent intToLogin = new Intent(OptionActivity.this, Login.class);
                startActivity(intToLogin);
            }
        });

        btnToS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToChangePassword = new Intent(OptionActivity.this, TermsOfService.class);
                intToChangePassword.putExtra("userPassword", userPassword);
                startActivity(intToChangePassword);
            }
        });

        btnPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToChangePassword = new Intent(OptionActivity.this, PrivacyPolicy.class);
                intToChangePassword.putExtra("userPassword", userPassword);
                startActivity(intToChangePassword);
            }
        });

        btnUM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToChangePassword = new Intent(OptionActivity.this, UserManual.class);
                intToChangePassword.putExtra("userPassword", userPassword);
                startActivity(intToChangePassword);
            }
        });
    }
}