package com.example.smile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    final private String TAG = MainActivity.class.getSimpleName();
    private Button btnSignUp, btnSignIn;
    private DatabaseReference mFirebaseDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
//        mFirebaseInstance = FirebaseDatabase.getInstance();
//        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users");
//        userId = "123";
//        User user = new User("tanphamnhat123@gmail.com", "tan", "123123", 0 ,0,0);
//        mFirebaseDatabase.push().setValue(user);

        btnSignIn = findViewById(R.id.btnSignin);
        btnSignUp = findViewById(R.id.btnRegister);

        btnSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                finish();
            }
        });
    }
}