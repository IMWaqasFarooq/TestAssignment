package com.example.testassignment.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testassignment.R;
import com.example.testassignment.TestAssignmentApplication;
import com.example.testassignment.data.APIClient;

import java.util.ArrayList;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {


    @Inject
    public SharedPreferences sharedPreferences;

    @Inject
    public Context context;


    public ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        ((TestAssignmentApplication) getApplication()).getAppComponent().inject(this);


    }


    public boolean showLoader(){
        if (APIClient.isNetworkAvailable(this)){
            if (progress != null){
                progress.cancel();
            }
            progress = ProgressDialog.show(BaseActivity.this,
                    "Loading", "Please wait...", true, false);
            return true;
        }else{
            Toast.makeText(this, "Internet not available", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean showLoader(String msg){
        if (APIClient.isNetworkAvailable(this)){
            if (progress != null){
                progress.cancel();
            }
            progress = ProgressDialog.show(BaseActivity.this,
                    "Loading", msg, true, false);
            return true;
        }else{
            Toast.makeText(this,"Internet not Available",Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public void hideLoader(){
        if (progress != null){
            progress.cancel();
        }
    }


}
