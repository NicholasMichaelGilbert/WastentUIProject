package com.example.wastent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddIdentity extends AppCompatActivity {
    String userEmail, userPassword;
    EditText etName, etPhoneNumber, etAddress;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    Button btnNext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_identity);

        Intent registerIntent = getIntent();
        userEmail = registerIntent.getStringExtra("registerEmail");
        userPassword = registerIntent.getStringExtra("registerPassword");

        etName = findViewById(R.id.addIdentity_name);
        etPhoneNumber = findViewById(R.id.addIdentity_phoneNumber);
        etAddress = findViewById(R.id.addIdentity_address);
        rgGender = findViewById(R.id.genderPick);
        rbMale = findViewById(R.id.gender_male);
        rbFemale = findViewById(R.id.gender_female);
        btnNext2 = findViewById(R.id.buttonNext2);

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                String gender = null;
                String address = etAddress.getText().toString();

                int selectedId = rgGender.getCheckedRadioButtonId();
                if (selectedId == rbMale.getId()) {
                    gender = "Male";
                }
                else if (selectedId == rbFemale.getId()) {
                    gender = "Female";
                }

                if (name.isEmpty()) {
                    etName.setError("Please Enter Name");
                    etName.requestFocus();
                }
                else if (phoneNumber.isEmpty()) {
                    etPhoneNumber.setError("Please Enter Phone Number");
                    etPhoneNumber.requestFocus();
                }
                else if (address.isEmpty()) {
                    etAddress.setError("Please Address Name");
                    etAddress.requestFocus();
                }
                else if (gender == null) {
                    Toast.makeText(AddIdentity.this,"Please Pick Gender Option", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intToCheckAndRegister = new Intent(AddIdentity.this, CheckAndRegister.class);
                    intToCheckAndRegister.putExtra("customerEmail", userEmail);
                    intToCheckAndRegister.putExtra("customerPassword", userPassword);
                    intToCheckAndRegister.putExtra("customerName", name);
                    intToCheckAndRegister.putExtra("customerPhoneNumber", phoneNumber);
                    intToCheckAndRegister.putExtra("customerAddress", address);
                    intToCheckAndRegister.putExtra("customerGender", gender);
                    startActivity(intToCheckAndRegister);
                }
            }
        });
    }
}
