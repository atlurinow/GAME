package com.example.loginapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginapp2.R;
import com.example.loginapp2.helpers.InputValidation;
import com.example.loginapp2.sql.DatabaseHelper;


public class SuccessLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Inside success login");
        setContentView(R.layout.success_login);
        System.out.println("After success login");
        getSupportActionBar().hide();

    }
}
