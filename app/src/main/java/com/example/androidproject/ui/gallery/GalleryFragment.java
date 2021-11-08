package com.example.androidproject.ui.gallery;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.R;
import com.example.androidproject.UserHelper;
import com.example.androidproject.UserPaymentPage;
import com.example.androidproject.databinding.FragmentGalleryBinding;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;


public class GalleryFragment extends Fragment {

    EditText userName,userRegNo,destination,selectStartDate,selectEndDate;
    Button next;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;


    private FirebaseAuth firebaseAuth;

    // creating a variable for
    // our object class
    UserHelper userHelper;
    ProgressBar mprogressbarofsetprofile;




    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);


        binding = FragmentGalleryBinding.inflate(inflater, container, false);



        View root = binding.getRoot();

        firebaseAuth=FirebaseAuth.getInstance();

        userName = root.findViewById(R.id.userName);
        userRegNo = root.findViewById(R.id.userRegNo);
        destination = root.findViewById(R.id.destination);
        selectStartDate = root.findViewById(R.id.selectStartDate);
        selectEndDate = root.findViewById(R.id.selectEndDate);
        next = root.findViewById(R.id.next);
        mprogressbarofsetprofile=root.findViewById(R.id.progressbarofsetProfile);

        // below line is used to get the
        // instance of our FIrebase database.


        // initializing our object
        // class variable.
        userHelper = new UserHelper();





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String Regno = userRegNo.getText().toString();
                String desti = destination.getText().toString();
                String SSD = selectStartDate.getText().toString();
                String SED = selectEndDate.getText().toString();

                if (!name.isEmpty()){
                    if (!Regno.isEmpty()){
                        if (!desti.isEmpty()){
                            if (!SSD.isEmpty()){
                                if (!SED.isEmpty()){

                                    firebaseDatabase = FirebaseDatabase.getInstance();

                                    // below line is used to get reference for our database.
                                    databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getUid());
                                    mprogressbarofsetprofile.setVisibility(View.VISIBLE);

                                    addDatatoFirebase(name,Regno,desti,SSD,SED);




                                    mprogressbarofsetprofile.setVisibility(View.GONE);

                                    Intent intent = new Intent(getActivity(),UserPaymentPage.class);
                                    startActivity(intent);


















                                }else {
                                    selectEndDate.setError("Empty Field Are Not Allowed ");
                                }
                            }else {
                                selectStartDate.setError("Empty Field Are Not Allowed ");

                            }
                        }else {
                            destination.setError("Empty Field Are Not Allowed ");
                        }
                    }else {
                        userRegNo.setError("Empty Field Are Not Allowed ");
                    }
                }else {
                    userName.setError("Empty Field Are Not Allowed ");
                }

            }

        });









        return root;
    }

    private void addDatatoFirebase(String name, String regno, String desti, String ssd, String sed) {

        userHelper.setName(name);
        userHelper.setRegno(regno);
        userHelper.setDesti(desti);
        userHelper.setSSD(ssd);
        userHelper.setSED(sed);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(userHelper);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to add data", Toast.LENGTH_SHORT).show();

            }
        });



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}