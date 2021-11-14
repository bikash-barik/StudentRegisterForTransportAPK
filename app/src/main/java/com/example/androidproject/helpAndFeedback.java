package com.example.androidproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class helpAndFeedback extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    EditText feedback;
    Button btnSend;

    UserHelper userHelper;

    ProgressBar sendProgressBar;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;

    public helpAndFeedback() {
        // Required empty public constructor
    }


    public static helpAndFeedback newInstance(String param1, String param2) {
        helpAndFeedback fragment = new helpAndFeedback();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_help_and_feedback, container, false);

        feedback = view.findViewById(R.id.feedback);
        btnSend = view.findViewById(R.id.btnSend);
        sendProgressBar = view.findViewById(R.id.sendProgressBar);
        firebaseAuth=FirebaseAuth.getInstance();

        userHelper = new UserHelper();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mfeedback =feedback.getText().toString();

                if (!mfeedback.isEmpty()){
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    sendProgressBar.setVisibility(View.VISIBLE);
                    databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getUid()).child(" HELP AND FEEDBACK ");
                    addDataonFirebase(mfeedback);

                }else {
                    feedback.setError("Empty Field Are Not Allowed");
                }

            }
        });







        return view;
    }

    private void addDataonFirebase(String mfeedback) {

        userHelper.setFeedback(mfeedback);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(userHelper);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Please Submit Your Feedback And Help Again", Toast.LENGTH_SHORT).show();


            }
        });



    }
}