package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserPaymentPage extends AppCompatActivity {

    EditText payer_name,payer_phone,payer_email;


    MaterialButton pay;

    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;


    private FirebaseAuth firebaseAuth;

    // creating a variable for
    // our object class
    UserPaymentHelper userPaymentHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment_page);

        pay = findViewById(R.id.pay);

        payer_name = findViewById(R.id.payer_name);
        payer_email = findViewById(R.id.payer_email);
        payer_phone   = findViewById(R.id.payer_phone);

        firebaseAuth=FirebaseAuth.getInstance();

        userPaymentHelper = new UserPaymentHelper();

         pay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {



                 String username = payer_name.getText().toString().trim();

                 String email = payer_email.getText().toString().trim();
                 String phone = payer_phone.getText().toString().trim();

                 if (!username.isEmpty()){
                     if (!email.isEmpty()){
                         if (!phone.isEmpty()){




                             Toast.makeText(getApplicationContext(), "Please Check Your Mobile Phone", Toast.LENGTH_SHORT).show();
                             Intent intent  = new Intent(UserPaymentPage.this,ConmformPament.class);
                             startActivity(intent);
                         }else {
                             payer_phone.setError("Empty Field Are Not Allowed");
                         }
                     }else {
                         payer_email.setError("Empty Field Are Not Allowed");

                     }
                 }else {
                     payer_name.setError("Empty Field Are Not Allowed");
                 }




             }
         });
    }





}