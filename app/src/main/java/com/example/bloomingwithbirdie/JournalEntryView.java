package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class JournalEntryView extends AppCompatActivity {
    private ImageButton homeButton;
    private Button dateConfirmButton;
    private Module module;
    private DatePicker datePicker;
    private TextView textView;
    private int textId;
    private int counter;
    private String dateFromPicker = null;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_entry_view);


        datePicker = findViewById(R.id.datePicker);
        dateConfirmButton = findViewById(R.id.dateConfirmButton);
        datePicker.setBackgroundColor(getResources().getColor(R.color.green));
        if (getIntent().getExtras() != null) {
            // Load the module
            module = (Module) getIntent().getSerializableExtra("module");
            user = (User) getIntent().getSerializableExtra("user");

            // Set appropriate ActionBar color & Title
            configureActionBar(module);
        }
    }


    public void toggleDatePicker(View view) {
        textId = view.getId();
        view.setBackgroundColor(getResources().getColor(R.color.yellow));
        datePicker.setVisibility(View.VISIBLE);
        dateConfirmButton.setVisibility(View.VISIBLE);
    }

    /**
     * This function will set the selected date in the "Date" column of the Journal & add the data, again
     * currently data does NOT persist throughout the application views.
     */
    public void confirmDate(View view) {
        TextView dateText = (TextView) findViewById(textId);
        dateText.setBackground(getResources().getDrawable(R.drawable.edittextbackground));
        dateText.setTextColor(getResources().getColor(R.color.black));
        dateText.setText(String.format("%d/%d/%d", datePicker.getMonth() + 1, datePicker.getDayOfMonth(), datePicker.getYear()));
        datePicker.setVisibility(View.INVISIBLE);
        dateConfirmButton.setVisibility(View.INVISIBLE);
        dateFromPicker = String.format("%d/%d/%d", datePicker.getMonth() + 1, datePicker.getDayOfMonth(),  datePicker.getYear());
    }

    /**
     * This function is used to set the correct title and color for the ActionBar at the top of the application.
     **/
    void configureActionBar(Module module) {
        // Set the ActionBar title
        getSupportActionBar().setTitle(module.getName() + " Journal");

        // Set the ActionBar color based on the module
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(module.getColor()));
    }

    /**
     * This function will load the next screen in the module
     **/
    public void addEntry(View view) {
        EditText location = findViewById(R.id.location);
        EditText description = findViewById(R.id.description);

        if (location.getText().toString().equals("") || description.getText().toString().equals("") || dateFromPicker == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all fields!", Toast.LENGTH_LONG);
            toast.show();
        } else {
//            if (dateFromPicker == null)
//                dateFromPicker = String.format("%d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
            module.getJournal().addEntry(dateFromPicker, location.getText().toString(), description.getText().toString());

            // Go back to Journal View
            Intent intent = new Intent(this, JournalView.class);
            intent.putExtra("module", module);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    public void homeButtonPressed(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}