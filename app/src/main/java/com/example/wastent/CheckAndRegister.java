package com.example.wastent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckAndRegister extends AppCompatActivity {
    TextView tvEmail, tvPassword, tvName, tvPhoneNumber, tvGender, tvAddress;
    Button btnYes, btnNo, btnRegister;
    TextView tvAreYouSure;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;
    String customerEmail, customerPassword, customerName, customerPhoneNumber, customerGender, customerAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_and_register);

        tvEmail = findViewById(R.id.checkAndRegister_email);
        tvPassword = findViewById(R.id.checkAndRegister_password);
        tvName = findViewById(R.id.checkAndRegister_name);
        tvPhoneNumber = findViewById(R.id.checkAndRegister_phoneNumber);
        tvGender = findViewById(R.id.checkAndRegister_gender);
        tvAddress = findViewById(R.id.checkAndRegister_address);
        btnYes = findViewById(R.id.buttonYes);
        btnNo = findViewById(R.id.buttonNo);
        btnRegister = findViewById(R.id.buttonRegister);
        tvAreYouSure = findViewById(R.id.staticAreYouSure);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference().child("users");

        Intent intRegister = getIntent();
        customerEmail = intRegister.getStringExtra("customerEmail");
        customerPassword = intRegister.getStringExtra("customerPassword");
        customerName = intRegister.getStringExtra("customerName");
        customerPhoneNumber = intRegister.getStringExtra("customerPhoneNumber");
        customerAddress = intRegister.getStringExtra("customerAddress");
        customerGender = intRegister.getStringExtra("customerGender");

        tvEmail.setText(customerEmail);
        tvPassword.setText(customerPassword);
        tvName.setText("Hello " + customerName + "!");
        tvPhoneNumber.setText(customerPhoneNumber);
        tvGender.setText(customerGender);
        tvAddress.setText(customerAddress);

        btnRegister.setVisibility(View.INVISIBLE);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegister.setVisibility(View.VISIBLE);
                btnYes.setVisibility(View.INVISIBLE);
                btnNo.setVisibility(View.INVISIBLE);
                tvAreYouSure.setVisibility(View.INVISIBLE);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToRegister = new Intent(CheckAndRegister.this, Register.class);
                startActivity(intToRegister);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.createUserWithEmailAndPassword(customerEmail, customerPassword).addOnCompleteListener(CheckAndRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!(task.isSuccessful())) {
                            Toast.makeText(CheckAndRegister.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mFirebaseUser = task.getResult().getUser();
                            String userUID = mFirebaseUser.getUid();
                            DatabaseReference newDbRef = dbRef.child(userUID);
                            newDbRef.child("email").setValue(customerEmail);
                            newDbRef.child("name").setValue(customerName);
                            newDbRef.child("address").setValue(customerAddress);
                            newDbRef.child("phoneNumber").setValue(customerPhoneNumber);
                            newDbRef.child("gender").setValue(customerGender);
                            newDbRef.child("wasteCollected").setValue(0);
                            newDbRef.child("moneyAchieved").setValue(0);
                            Intent intToLogin = new Intent(CheckAndRegister.this, Login.class);
                            startActivity(intToLogin);
                        }
                    }
                });
            }
        });
    }
}
