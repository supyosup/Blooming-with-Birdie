package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getSupportActionBar().setTitle("Login");

        // Can use the getText method for these variables to build the DB query
        // Should probably encrypt the password, but for the scope of this program, I'd say forget it
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This is where we would put the DB query and return the User account if login is successful
     *
     */
    public void login(View view) {

    }

    public void loadCreateAccountPage(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
}