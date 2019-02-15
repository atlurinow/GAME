package com.example.loginapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.TextInputEditText;
//import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginapp2.R;
import com.example.loginapp2.helpers.InputValidation;
import com.example.loginapp2.model.User;
import com.example.loginapp2.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private EditText textInputLayoutName;
    private EditText textInputLayoutEmail;
    private EditText textInputLayoutPassword;
    private EditText textInputLayoutConfirmPassword;

    private Button appCompatButtonRegister;
    private TextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        //nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (EditText) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (EditText) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (EditText) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (EditText) findViewById(R.id.textInputLayoutConfirmPassword);

        //textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
       // textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        //textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
       // textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (Button) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (TextView) findViewById(R.id.appCompatButtonRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                Intent intentRegister = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled( textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled( textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled( textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled( textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputLayoutPassword,textInputLayoutConfirmPassword, textInputLayoutPassword,getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputLayoutEmail.getText().toString().trim())) {

            user.setName(textInputLayoutName.getText().toString().trim());
            user.setEmail(textInputLayoutEmail.getText().toString().trim());
            user.setPassword(textInputLayoutConfirmPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            //Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            //Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputLayoutName.setText("Name");
        textInputLayoutEmail.setText("Email");
        textInputLayoutPassword.setText("Password");
        textInputLayoutConfirmPassword.setText("Confirm Password");
    }
}

