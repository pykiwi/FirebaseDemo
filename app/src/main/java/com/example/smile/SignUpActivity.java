package com.example.smile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText inputEmail, inputName;
    private EditText inputPassword, inputRepeatPassword;
    private Button btnSignUp;
    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        auth = FirebaseAuth.getInstance();
        btnSignUp = (Button) findViewById(R.id.btnRegister);
        inputEmail = (EditText) findViewById(R.id.etEmail);
        inputName = (EditText) findViewById(R.id.etName);
        inputPassword = (EditText) findViewById(R.id.etPassword);
        inputRepeatPassword = (EditText) findViewById(R.id.etRepeatPassword);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String name = inputName.getText().toString().trim();
                String repeat = inputRepeatPassword.getText().toString().trim();
                createUser(email, name, password);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals(repeat)){
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()){

                                        Toast.makeText(SignUpActivity.this, "Authentication failed" + task.getException(), Toast.LENGTH_LONG).show();
                                    }else{
                                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Passwords must match!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void createUser(String email, String name, String password) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }
        User user = new User(email, name, password, 0,0,0);
        user.setId(userId);
        mFirebaseDatabase.child(userId).setValue(user);
        Log.d(TAG, "Create user");
    }
}
