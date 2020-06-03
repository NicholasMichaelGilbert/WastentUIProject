package com.example.wastent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeUserPassword extends AppCompatActivity {
    EditText etOldPassword, etNewPassword, etRetypeNewPassword;
    Button btnChangePassword, btnRelogin;
    TextView tvStaticChangeSuccess;
    String userPassword;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_password);

        Intent userCredential = getIntent();
        userPassword = userCredential.getStringExtra("userPassword");

        etOldPassword = findViewById(R.id.old_password);
        etNewPassword = findViewById(R.id.new_password);
        etRetypeNewPassword = findViewById(R.id.retype_newPassword);
        btnChangePassword = findViewById(R.id.buttonChangePassword);
        tvStaticChangeSuccess = findViewById(R.id.staticChangeSuccess);
        btnRelogin = findViewById(R.id.buttonRelogin);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnRelogin.setVisibility(View.INVISIBLE);
        tvStaticChangeSuccess.setVisibility(View.INVISIBLE);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = etOldPassword.getText().toString();
                String newPassword = etNewPassword.getText().toString();
                String retypeNewPassword = etRetypeNewPassword.getText().toString();
                if (oldPassword == null) {
                    etOldPassword.setError("Please Enter Your Old Password");
                    etOldPassword.requestFocus();
                }
                else if (newPassword == null) {
                    etNewPassword.setError("Please Enter Your New Password");
                    etNewPassword.requestFocus();
                }
                else if (retypeNewPassword == null) {
                    etRetypeNewPassword.setError("Please Retype Your New Password");
                    etRetypeNewPassword.requestFocus();
                }
                else if (!(oldPassword.equals(userPassword))) {
                    etOldPassword.setError("Wrong Current Password");
                    etOldPassword.requestFocus();
                }
                else if (newPassword.equals(oldPassword)) {
                    etNewPassword.setError("Your New Password is the same as Your Current Password");
                    etNewPassword.requestFocus();
                }
                else if (!(retypeNewPassword.equals(newPassword))) {
                    etRetypeNewPassword.setError("Password dont match");
                    etRetypeNewPassword.requestFocus();
                }
                else if (!(newPassword.equals("^[a-zA-Z0-9]+$"))) {
                    etNewPassword.setError("Password is not alpha-numeric");
                    etNewPassword.requestFocus();
                }
                else {
                    mFirebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            etOldPassword.setVisibility(View.INVISIBLE);
                            etNewPassword.setVisibility(View.INVISIBLE);
                            etRetypeNewPassword.setVisibility(View.INVISIBLE);
                            btnChangePassword.setVisibility(View.INVISIBLE);
                            tvStaticChangeSuccess.setVisibility(View.VISIBLE);
                            btnRelogin.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });

        btnRelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToLogin = new Intent(ChangeUserPassword.this, Login.class);
                startActivity(intToLogin);
            }
        });
    }
}