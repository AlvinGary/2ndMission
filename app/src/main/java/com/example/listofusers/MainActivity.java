package com.example.listofusers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Data;
import model.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView Main_RecyclerView;
    public static ArrayList<User> dataUser = Data.data;
    private CardView card_view;
    private UserRVAdapter adapter;
    private FloatingActionButton Main_FAB_add;
    private TextView main_nodata_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        setupRecyclerView();
        setListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 200){
                User userBaru = data.getParcelableExtra("userBaru");
                dataUser.add(userBaru);
                adapter.notifyDataSetChanged();
                main_nodata_textview.setVisibility(View.GONE);
            }
        }
    }

    private void setListener() {
        Main_FAB_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddUser.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        Main_RecyclerView.setLayoutManager(manager);
        Main_RecyclerView.setAdapter(adapter);
    }

    private void initView() {
        Main_RecyclerView = findViewById(R.id.Main_RecyclerView);
        adapter = new UserRVAdapter(dataUser);
        Main_FAB_add = findViewById(R.id.Main_FAB_add);
        main_nodata_textview = findViewById(R.id.main_nodata_textview);
        if (!dataUser.isEmpty()){
            main_nodata_textview.setVisibility(View.GONE);
        }else{
            main_nodata_textview.setVisibility(View.VISIBLE);
        }
    }

}