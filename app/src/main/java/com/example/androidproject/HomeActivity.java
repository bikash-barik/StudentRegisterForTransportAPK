package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<UserHelper> userHelpers;

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userHelpers = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot ds:datasnapshot.getChildren()){
                    UserHelper data = ds.getValue(UserHelper.class);

                    userHelpers.add(data);
                }
                myAdapter = new MyAdapter(userHelpers);
                recyclerView.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}