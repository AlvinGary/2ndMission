package com.example.listofusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.Data;
import model.User;

public class AddUser extends AppCompatActivity {

    private TextInputLayout addUser_name, addUser_age, addUser_address;
    private Button adduser_saveData;
    private ImageView addUser_back_ic, userDetail_edit_ic;
    Loading loading;
    public static ArrayList<User> data = Data.data;
    private Toolbar toolbar_addUser;
    int id=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getSupportActionBar().hide();
        initView();
        addUser_name.getEditText().addTextChangedListener(newData);
        addUser_age.getEditText().addTextChangedListener(newData);
        addUser_address.getEditText().addTextChangedListener(newData);
        setListener();

        addUser_back_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setListener() {

        adduser_saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = addUser_name.getEditText().getText().toString().trim();
                String umur = addUser_age.getEditText().getText().toString().trim();
                int age = Integer.parseInt(umur);
                String address = addUser_address.getEditText().getText().toString().trim();

                Intent intent2 = getIntent();
                id = intent2.getIntExtra("edit", -1);
                if(id!=-1){
                    loading.startLoading();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissdialog();
                            Intent intent = new Intent(getBaseContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            User temp = new User(nama, age, address);
                            data.set(id, temp);
                            startActivity(intent);
                        }
                    }, 1000);
                }else{
                    User temp = new User(nama, age, address);
                    Intent intent = new Intent();
                    intent.putExtra("userBaru", temp);

                    setResult(200, intent);
                    loading.startLoading();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissdialog();
                            finish();
                        }
                    }, 1000);
                }
            }
        });
    }

    private void initView() {
        loading = new Loading(AddUser.this);
        addUser_name = findViewById(R.id.addUser_name);
        addUser_age = findViewById(R.id.addUser_age);
        addUser_address = findViewById(R.id.adduser_address);
        adduser_saveData = findViewById(R.id.adduser_saveData);
        addUser_back_ic = findViewById(R.id.addUser_back_ic);
        toolbar_addUser = findViewById(R.id.toolbar_addUser);

        Intent intent2 = getIntent();
        if(!data.isEmpty()){
            id = intent2.getIntExtra("edit", -1);
            if(id != -1){
                toolbar_addUser.setTitle("Edit User");
                adduser_saveData.setText("Update Data");
                addUser_name.getEditText().setText(data.get(id).getNama());
                addUser_age.getEditText().setText(String.valueOf(data.get(id).getAge()));
                addUser_address.getEditText().setText(data.get(id).getAddress());
                adduser_saveData.setEnabled(true);
            }
        }
    }

    private TextWatcher newData = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nama = addUser_name.getEditText().getText().toString().trim();
            String age = addUser_age.getEditText().getText().toString().trim();
            String address = addUser_address.getEditText().getText().toString().trim();

            if(!nama.isEmpty()){
                addUser_name.setError("");
                if(!age.isEmpty() && !address.isEmpty()){
                    adduser_saveData.setEnabled(true);
                }else{
                    adduser_saveData.setEnabled(false);
                }
            }else{
                adduser_saveData.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}