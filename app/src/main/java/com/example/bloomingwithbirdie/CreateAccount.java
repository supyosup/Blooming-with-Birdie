package com.example.bloomingwithbirdie;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CreateAccount extends AppCompatActivity {

    EditText userName;
    EditText password;
    EditText verifyPassword;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getSupportActionBar().setTitle("Create Account");

        userName = findViewById(R.id.nameField);
        password = findViewById(R.id.passwordField);
        verifyPassword = findViewById(R.id.verifyPasswordField);
        email = findViewById(R.id.emailField);
    }

    /** Take the account info and create a User here **/
    public void confirmAccountCreation(View view) {

        if (password.getText().toString().compareTo(verifyPassword.getText().toString()) != 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match, please try again.", Toast.LENGTH_LONG);
            toast.show();
        } else if (DatabaseClass.getDatabase(getApplicationContext()).daoClass().
                loadAllByEmail(email.getText().toString()).size()>=1){
            Toast toast = Toast.makeText(getApplicationContext(), "Email is already registered to another account.", Toast.LENGTH_LONG);
            toast.show();
        } else if (userName.getText().toString() != null && password.getText().toString() != null && verifyPassword.getText().toString() != null && email.getText().toString() != null){
            User newUser = new User(userName.getText().toString(), email.getText().toString(), password.getText().toString());
            DatabaseClass.getDatabase(getApplicationContext()).daoClass().insertAll(newUser);
            Toast toast = Toast.makeText(getApplicationContext(), "Account Registered", Toast.LENGTH_LONG);
            toast.show();
        }
        // userName.getText()
        // password.getText()
        // verifyPassword.getText()
        // email.getText()
    }
}