package com.example.listofusers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

public class Loading extends AppCompatActivity {

    private Activity activity;
    private AlertDialog dialog;

    Loading(Activity myActivity){
        activity = myActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        startLoading();
        dismissdialog();
    }
    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_loading, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }
    public void dismissdialog(){
        dialog.dismiss();
    }
}