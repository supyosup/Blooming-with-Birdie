package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

//FIXME: Currently when selecting a "Location" TextField, the keyboard will not hide when pressing the "Enter" key.
//TODO: Add functionality for the "Description" TextField, including dynamically loading existing Module Descriptions, and generating a "Blank" TextField.

public class JournalView extends AppCompatActivity {
    private Button dateConfirmButton;
    private Module module;
    private DatePicker datePicker;
    private int textId;
    private LinearLayout dateLayout;
    private LinearLayout locationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_view);
        datePicker = findViewById(R.id.datePicker);
        dateConfirmButton = findViewById(R.id.dateConfirmButton);
        datePicker.setBackgroundColor(getResources().getColor(R.color.green));
        if (getIntent().getExtras() != null) {
            // Load the module
            module = (Module) getIntent().getSerializableExtra("module");

            // Set appropriate ActionBar color & Title
            configureActionBar(module);

            // Just some test data for the different fields
//            module.getJournal().addEntry(1, 1, 2021, "This is a test of the Journal");
//            module.getJournal().addEntry(1, 1, 2021, "Another one");
//            module.getJournal().addEntry(1, 1, 2021, "Here is a third test of the Journal");

            dateLayout = findViewById(R.id.dateLayout);
            if (!module.getJournal().getDescriptions().isEmpty()) {
                for (String journalEntry : module.getJournal().getDescriptions())
                {
                    addDateBox(dateLayout, journalEntry);
                }
            }
            addDateBox(dateLayout, "Date I saw it");

            locationLayout = findViewById(R.id.locationLayout);

            // If the Journal is not empty, loop through the Journal entries
            // and dynamically create textboxes to show the entries.
            if (!module.getJournal().getLocations().isEmpty()) {
                for (String locationEntry : module.getJournal().getDescriptions())
                {
                    addLocationBox(locationLayout, locationEntry);
                }
            }
            addLocationBox(locationLayout, "");

        }
    }

    /** Creates a new "Location" field after the user enters a new date */
    private void addLocationBox(LinearLayout locationLayout, String text) {
        // Create the EditText & configure...
        EditText view = new EditText(this);
        view.setId(View.generateViewId());
        view.setPadding(0, 10, 0, 40);
        view.setText(text);
        view.setTextSize(20);
        view.setText(text);
        view.setTextColor(getResources().getColor(R.color.black));
        view.setGravity(Gravity.CENTER);

        // Add a listener to the field
        view.setOnClickListener(v -> {
            view.setEnabled(true);
            view.clearComposingText();
        });

        locationLayout.addView(view);
    }

    /** Creates a new "Date" field after the user enters a new date */
    private void addDateBox(LinearLayout dateLayout, String text) {
        // Create the TextView & configure...
        TextView view = new TextView(this);
        view.setId(View.generateViewId());
        view.setPadding(0, 10, 0, 40);
        view.setText(text);
        view.setTextSize(20);
        view.setTextColor(getResources().getColor(R.color.black));
        view.setGravity(Gravity.CENTER);

        // Add a listener to the field
        view.setOnClickListener(v -> {
            datePicker.setVisibility(View.VISIBLE);
            dateConfirmButton.setVisibility(View.VISIBLE);
            textId = view.getId();
        });

        dateLayout.addView(view);
    }

    // TODO: Add this functionality for the "Description" field
    private void addDescriptionBox(LinearLayout descriptionLayout, String text) {

    }

    /** This function will set the selected date in the "Date" column of the Journal & add the data, again
     * currently data does NOT persist throughout the application views.*/
    public void confirmDate(View view) {
        TextView dateText = findViewById(textId);
        dateText.setText(String.format("%d/%d/%d", datePicker.getMonth(), datePicker.getDayOfMonth(), datePicker.getYear()));
        datePicker.setVisibility(View.INVISIBLE);
        dateConfirmButton.setVisibility(View.INVISIBLE);
        addDateBox(dateLayout, "Date I saw it");
        module.getJournal().addEntry(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear(), "");
    }

    /**  This function is used to set the correct title and color for the ActionBar at the top of the application. **/
    void configureActionBar(Module module) {
        // Set the ActionBar title
        getSupportActionBar().setTitle(module.getName() + " Journal");

        // Set the ActionBar color based on the module
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(module.getColor()));
    }
}