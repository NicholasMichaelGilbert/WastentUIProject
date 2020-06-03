package com.example.wastent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity {
    ImageView ivOption;
    TextView tvUserName, tvWasteCollected, tvMoneyExchanged;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference dbRef;
    ImageView ivRefresh;
    String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent userCredential = getIntent();
        userPassword = userCredential.getStringExtra("userPassword");

        mFirebaseAuth = FirebaseAuth.getInstance();
        tvUserName = findViewById(R.id.username);
        tvWasteCollected = findViewById(R.id.totalWasteCollected);
        tvMoneyExchanged = findViewById(R.id.totalMoneyExchanged);
        ivOption = findViewById(R.id.imageViewOption);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        ivRefresh = findViewById(R.id.imageViewRefresh);

        dbRef = mFirebaseDatabase.getReference().child("users").child(mFirebaseUser.getUid());

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String customerName = dataSnapshot.child("name").getValue().toString();
                String customerWasteCollected = dataSnapshot.child("wasteCollected").getValue().toString();
                String customerMoneyAchieved = dataSnapshot.child("moneyAchieved").getValue().toString();
                tvUserName.setText(customerName);
                tvWasteCollected.setText(customerWasteCollected + " kg");
                tvMoneyExchanged.setText("Rp. " + customerMoneyAchieved + ".00-");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main.this, "You are not a user", Toast.LENGTH_SHORT).show();
                Intent intToLoginFailed = new Intent(Main.this, Login.class);
                startActivity(intToLoginFailed);
            }
        });

        ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToOptions = new Intent(Main.this, OptionActivity.class);
                intToOptions.putExtra("userPassword", userPassword);
                startActivity(intToOptions);
            }
        });

        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(Main.this, Main.class);
                startActivity(refresh);
            }
        });
    }
}
