package com.example.musicappserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Button signup_men;
    EditText men_name,men_email,men_pwd,men_pwd1;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference profileRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup_men=findViewById(R.id.user_signup);
        men_name=findViewById(R.id.signupName);
        men_email=findViewById(R.id.signupEmail);
        men_pwd=findViewById(R.id.signupPass);
        men_pwd1=findViewById(R.id.signupPassAgain);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        signup_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=men_name.getText().toString();
                String email=men_email.getText().toString();
                String pwd=men_pwd.getText().toString();
                String pwd1=men_pwd1.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "Empty credentials. Try again", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pwd.length()<=6 || pwd1.length()<=6) {
                        Toast.makeText(RegisterActivity.this, "Password too small", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (pwd.equals(pwd1)) {
                            registerUser(name, email, pwd);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void registerUser(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();

                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    profileRef = FirebaseDatabase.getInstance().getReference("profiles").child(userid);

                    HashMap<String, Object> profileData = new HashMap<>();
                    profileData.put("Username", name);
                    profileData.put("Email", email);

                    profileRef.setValue(profileData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    // intent.putExtra("m_name",name);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Registration failed. Try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Registration failed. Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}