package com.example.androidproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText userGmail, userPass;
    Button login;
    private FirebaseAuth mAuth;

    ProgressBar progressbarofsetLogin;


    Switch active;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userGmail = findViewById(R.id.userGmail);
        userPass = findViewById(R.id.userPass);
        login = findViewById(R.id.login);

        progressbarofsetLogin =  findViewById(R.id.progressbarofsetLogin);

        active = findViewById(R.id.active);
        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() !=null){
            Intent intent = new Intent(MainActivity.this,UserActivity.class);
            startActivity(intent);
            finish();

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userGmail.getText().toString().equals("admin@cutm.ac.in") && userPass.getText().toString().equals("admin")){
                    progressbarofsetLogin.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                    startActivity(intent);
                    finish();
                    progressbarofsetLogin.setVisibility(View.GONE);
                }else {

                    SetValidation();

                }
            }
        });




    }

    private void SetValidation() {

        String email = userGmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();


        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressbarofsetLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Login SucessesFully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,UserActivity.class);
                            startActivity(intent);
                            progressbarofsetLogin.setVisibility(View.GONE);
                            finish();
                        }else {
                            progressbarofsetLogin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }else {
                userPass.setError("Empty Fields Are not Allowed");
            }
        }else if (email.isEmpty()){
            userGmail.setError("Empty Fields Are not Allowed");
        }else {
            userGmail.setError("Please Enter Correct Gmail");
        }



    }
}
