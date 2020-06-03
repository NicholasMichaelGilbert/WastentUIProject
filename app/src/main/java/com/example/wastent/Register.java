package com.example.wastent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText etRegisterEmail, etRegisterPassword;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterEmail = findViewById(R.id.register_email);
        etRegisterPassword = findViewById(R.id.register_password);
        btnNext = findViewById(R.id.buttonNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etRegisterEmail.getText().toString();
                String password = etRegisterPassword.getText().toString();

                if (email.isEmpty()) {
                    etRegisterEmail.setError("Please Enter Email");
                    etRegisterEmail.requestFocus();
                }
                else if (password.isEmpty()) {
                    etRegisterPassword.setError("Please Enter Password");
                    etRegisterPassword.requestFocus();
                }
                else if (!(etRegisterPassword.equals("^[a-zA-Z0-9]+$"))) {
                    etRegisterPassword.setError("Password is not alpha-numeric");
                    etRegisterPassword.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty()) {
                    etRegisterEmail.setError("Please Enter Email");
                    etRegisterEmail.requestFocus();
                    etRegisterPassword.setError("Please Enter Password");
                    etRegisterPassword.requestFocus();
                    Toast.makeText(Register.this, "Email and Password Fields are EMPTY", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && password.isEmpty())) {
                    Intent intToAddIdentity = new Intent(Register.this, AddIdentity.class);
                    intToAddIdentity.putExtra("registerEmail", email);
                    intToAddIdentity.putExtra("registerPassword", password);
                    startActivity(intToAddIdentity);
                }
            }
        });
    }
}
