package com.example.listofusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import model.Data;
import model.User;

public class UserDetail extends AppCompatActivity {

    private TextView userDetail_name, userDetail_age, userDetail_address;
    private ImageView userDetail_edit_ic, userDetail_delete_ic, userDetail_back_ic;
    int index;
    public static ArrayList<User> data = Data.data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().hide();
        initView();
        getData();

        userDetail_back_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userDetail_delete_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                Toast.makeText(UserDetail.this, String.valueOf(index), Toast.LENGTH_SHORT).show();
                data.remove(index);
                startActivity(intent);
                finish();
            }
        });
        userDetail_edit_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), AddUser.class);
                intent1.putExtra("edit", index);
                startActivity(intent1);
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        userDetail_name.setText(user.getNama());
        userDetail_age.setText(String.valueOf(user.getAge()));
        userDetail_address.setText(user.getAddress());
        index = intent.getIntExtra("index", -1);
    }

    private void initView() {
        userDetail_name = findViewById(R.id.userDetail_name);
        userDetail_age = findViewById(R.id.userDetail_age);
        userDetail_address = findViewById(R.id.userDetail_address);
        userDetail_edit_ic = findViewById(R.id.userDetail_edit_ic);
        userDetail_delete_ic = findViewById(R.id.userDetail_delete_ic);
        userDetail_back_ic = findViewById(R.id.userDetail_back_ic);
    }


}