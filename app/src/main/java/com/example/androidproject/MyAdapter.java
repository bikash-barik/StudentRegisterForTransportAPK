package com.example.androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter{

    List<UserHelper> userHelperList;


    public MyAdapter(List<UserHelper> userHelperList) {
        this.userHelperList = userHelperList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        myViewHolder myViewHolder = (myViewHolder)holder;
       UserHelper userHelper = userHelperList.get(position);
       myViewHolder.nameofuser.setText(userHelper.getName());
       myViewHolder.regNoofUser.setText(userHelper.getRegno());

    }

    @Override
    public int getItemCount() {
        return userHelperList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView nameofuser;
        TextView regNoofUser;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nameofuser = itemView.findViewById(R.id.nameofuser);
            regNoofUser = itemView.findViewById(R.id.regNoofUser);
        }
    }


}
