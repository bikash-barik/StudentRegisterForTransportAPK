package com.example.androidproject.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.example.androidproject.MainActivity;
import com.example.androidproject.R;
import com.example.androidproject.UserHelper;
import com.example.androidproject.databinding.FragmentSlideshowBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SlideshowFragment extends Fragment {

    TextView fullName,clgGmail,destinatation,sts,SED,clgRegNo,phoneNumber;

    ImageView logoutBtn;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fullName = root.findViewById(R.id.fullName);
        clgGmail = root.findViewById(R.id.clgGmail);
        destinatation = root.findViewById(R.id.destinatationshow);
        sts = root.findViewById(R.id.sts);
        SED = root.findViewById(R.id.Std);
        clgRegNo = root.findViewById(R.id.clgRegNo);
        phoneNumber = root.findViewById(R.id.phoneNumber);
        firebaseDatabase=FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        logoutBtn = root.findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                Intent logoutIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(logoutIntent);


                firebaseAuth.signOut();
            }
        });


        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper muserhelper=snapshot.getValue(UserHelper.class);
                fullName.setText(muserhelper.getName());
                destinatation.setText(muserhelper.getDesti());
                sts.setText(muserhelper.getSSD() );
                SED.setText(muserhelper.getSED());
                phoneNumber.setText(muserhelper.getNo());
                clgRegNo.setText(muserhelper.getRegno());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}