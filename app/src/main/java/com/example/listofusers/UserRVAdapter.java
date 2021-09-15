package com.example.listofusers;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Data;
import model.User;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder> {

    public static ArrayList<User> listUser = Data.data;
    private CardView card_view;

    public UserRVAdapter(ArrayList<User> listUser){
        this.listUser=listUser;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new UserViewHolder(view);
    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserRVAdapter.UserViewHolder holder, int position) {
        holder.card_name.setText(listUser.get(position).getNama());
        holder.card_age.setText(String.valueOf(listUser.get(position).getAge()));
        holder.card_address.setText(listUser.get(position).getAddress());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserDetail.class);
                String nama = holder.card_name.getText().toString().trim();
                String age = holder.card_age.getText().toString().trim();
                int age1 = Integer.parseInt(age);
                String address = holder.card_address.getText().toString().trim();
                User user = new User(nama, age1, address);
                intent.putExtra("user", user);
                intent.putExtra("index", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView card_name, card_age, card_address;
        private CardView card_view;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            card_name = itemView.findViewById(R.id.card_name);
            card_age = itemView.findViewById(R.id.card_age);
            card_address = itemView.findViewById(R.id.card_address);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
