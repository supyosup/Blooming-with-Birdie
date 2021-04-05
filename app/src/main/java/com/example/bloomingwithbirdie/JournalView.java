package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

//TODO: Update code to dynamically load User data into Date/Location/Description fields, clean up code

public class JournalView extends AppCompatActivity {
    private Button dateConfirmButton;
    private Module module;
    private DatePicker datePicker;
    private int textId;
    private int counter;
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
            module.getJournal().addEntry("12/16/2021", "This is a test of the Journal");
            module.getJournal().addEntry("12/16/2021", "Another one");
            module.getJournal().addEntry("12/16/2021", "Here is a third test of the Journal");

            dateLayout = findViewById(R.id.dateLayout);
            if (!module.getJournal().getDates().isEmpty()) {
                counter = 1;
                for (String date : module.getJournal().getDates())
                {
                    String imageViewId = "date" + counter;
                    int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
                    TextView view = findViewById(resID);
                    view.setText(date);
                    counter++;
                }
            }

            locationLayout = findViewById(R.id.locationLayout);
            // If the Journal is not empty, loop through the Journal entries
            // and dynamically create textboxes to show the entries.
            if (!module.getJournal().getDescriptions().isEmpty()) {
                counter = 1;
                for (String location : module.getJournal().getDescriptions())
                {
                    String imageViewId = "location" + counter;
                    int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
                    TextView view = findViewById(resID);
                    view.setText(location);
                    counter++;
                }
            }
        }
    }

    /** Creates a new "Location" field after the user enters a new date */
    private void addLocationBox(ScrollView locationLayout, String text) {
        // Create the EditText & configure...
        EditText view = new EditText(this);
        view.setId(View.generateViewId());
        view.setPadding(0, 10, 0, 40);
        view.setText(text);
        view.setTextSize(20);
        view.setText(text);
        view.setTextColor(getResources().getColor(R.color.black));
        view.setGravity(Gravity.CENTER);
        //TODO view.set
        // Add a listener to the field
        view.setOnClickListener(v -> {
            view.setEnabled(true);
            view.clearComposingText();
        });

        locationLayout.addView(view);
    }

    public void toggleDatePicker(View view) {
        textId = view.getId();
        view.setBackgroundColor(getResources().getColor(R.color.yellow));
        datePicker.setVisibility(View.VISIBLE);
        dateConfirmButton.setVisibility(View.VISIBLE);
    }

    /** This function will set the selected date in the "Date" column of the Journal & add the data, again
     * currently data does NOT persist throughout the application views.*/
    public void confirmDate(View view) {
        TextView dateText = (TextView) findViewById(textId);
        dateText.setBackground(getResources().getDrawable(R.drawable.edittextbackground));
        dateText.setText(String.format("%d/%d/%d", datePicker.getMonth(), datePicker.getDayOfMonth(), datePicker.getYear()));
        datePicker.setVisibility(View.INVISIBLE);
        dateConfirmButton.setVisibility(View.INVISIBLE);
        module.getJournal().addEntry(String.format("%d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear()), "");
    }

    /**  This function is used to set the correct title and color for the ActionBar at the top of the application. **/
    void configureActionBar(Module module) {
        // Set the ActionBar title
        getSupportActionBar().setTitle(module.getName() + " Journal");

        // Set the ActionBar color based on the module
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(module.getColor()));
    }

    public void loadDrawingJournal(View view) {
        Intent intent = new Intent(this, DrawingJournalView.class);
        intent.putExtra("module", module);
        startActivity(intent);
    }
}