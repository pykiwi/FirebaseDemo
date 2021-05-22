package com.example.smile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase database;
    private Button btnFinish;
    private boolean happy, unhappy, normal;
    private ImageView imgHappy, imgUnhappy, imgNormal;
    private User user;
    private String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("users");
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        System.out.println(email);
        btnFinish = findViewById(R.id.btnFinish);
        imgHappy = findViewById(R.id.happy);
        imgNormal = findViewById(R.id.faceNormal);
        imgUnhappy = findViewById(R.id.unhappy);

        imgUnhappy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                unhappy = true;
                happy = false;
                normal = false;
                Toast.makeText(getApplicationContext(), "Unhappy", Toast.LENGTH_LONG).show();
                return unhappy;
            }
        });
        imgHappy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                happy = true;
                unhappy = false;
                normal = false;
                Toast.makeText(getApplicationContext(), "Happy", Toast.LENGTH_LONG).show();
                return happy;
            }
        });
        imgNormal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                normal = true;
                happy = false;
                unhappy = false;
                Toast.makeText(getApplicationContext(), "Normal", Toast.LENGTH_LONG).show();
                return normal;
            }
        });
        mDatabaseReference.orderByChild("email").equalTo(email)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        user = snapshot.getValue(User.class);
                        userId = snapshot.getKey();
                        System.out.println(user);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (happy == true){
                    int demp = user.getHappy() + 1;
                   mDatabaseReference.child(userId).child("happy").setValue(demp);
                   startActivity(new Intent(HomeActivity.this, MainActivity.class));
                   finish();
                }
                if (unhappy == true){
                    int demp = user.getUnhappy() + 1;
                    mDatabaseReference.child(userId).child("unhappy").setValue(demp);
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                }
                if (normal == true){
                    int demp = user.getNormal() + 1;
                    mDatabaseReference.child(userId).child("normal").setValue(demp);
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                }
        }
            });
    }
}

