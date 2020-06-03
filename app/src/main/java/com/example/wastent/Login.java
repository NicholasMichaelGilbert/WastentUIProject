package com.example.wastent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText etEmailId, etPassId;
    Button btnLogin;
    TextView tvSignUp;
    private FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailId = findViewById(R.id.emailInput);
        etPassId = findViewById(R.id.passwordInput);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.signUp);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailId.getText().toString();
                final String password = etPassId.getText().toString();

                if (email.isEmpty()) {
                    etEmailId.setError("Please Enter Email");
                    etEmailId.requestFocus();
                }
                else if (password.isEmpty()) {
                    etPassId.setError("Please Enter Password");
                    etPassId.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty()) {
                    etEmailId.setError("Please Enter Email");
                    etEmailId.requestFocus();
                    etPassId.setError("Please Enter Password");
                    etPassId.requestFocus();
                    Toast.makeText(Login.this, "Email and Password Fields are EMPTY", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && password.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!(task.isSuccessful())) {
                                Toast.makeText(Login.this, "Login Failed, Please Enter the Right Email or Password", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mFirebaseUser = task.getResult().getUser();
                                if (mFirebaseUser.isEmailVerified()) {
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intToHome = new Intent(Login.this, Main.class);
                                    intToHome.putExtra("userPassword", password);
                                    startActivity(intToHome);
                                }
                                else {
                                    Toast.makeText(Login.this, "You Are Not Verified Yet, Please Verify Your Account by Checking Your Email", Toast.LENGTH_SHORT).show();
                                    FirebaseAuth.getInstance().signOut();
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login.this, "Error Occurred, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToRegister = new Intent(Login.this, Register.class);
                startActivity(intToRegister);
            }
        });
    }
}