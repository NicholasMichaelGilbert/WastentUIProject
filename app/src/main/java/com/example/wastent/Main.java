package com.example.wastent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Button btnLogOut;
    TextView tvUserName, tvWasteCollected, tvMoneyExchanged;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference dbRef;
    ImageView btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        tvUserName = findViewById(R.id.username);
        tvWasteCollected = findViewById(R.id.totalWasteCollected);
        tvMoneyExchanged = findViewById(R.id.totalMoneyExchanged);
        btnLogOut = findViewById(R.id.btnLogout);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        btnRefresh = findViewById(R.id.imageView6);

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
                databaseError.getMessage();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(Main.this, Login.class);
                startActivity(intToLogin);
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(Main.this, Main.class);
                startActivity(refresh);
            }
        });
    }
}
