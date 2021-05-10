package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//TODO: Update code to dynamically load User data into Date/Location/Description fields, clean up code

public class JournalView extends AppCompatActivity {
    MediaPlayer backgroundPlayer;
    private ImageButton homeButton;
    private Button dateConfirmButton;
    private Module module;
    private DatePicker datePicker;
    private int textId;
    private int index;
    private int maxPages;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_view);



        if (getIntent().getExtras() != null) {
            // Load the module
            module = (Module) getIntent().getSerializableExtra("module");
            user = (User) getIntent().getSerializableExtra("user");

            // Set appropriate ActionBar color & Title
            configureActionBar(module);

            // Just some test data for the different fields
            if (module.getJournal().getSize() == 0) {
                module.getJournal().addEntry("11/7/2020", "By the Oak tree", "It was a yellow bug");
                module.getJournal().addEntry("11/10/2020", "In the field", "Blue / black butterfly");
                module.getJournal().addEntry("11/11/2020", "In the backyard", "Big fuzzy caterpillar");
            }

            // If the Journal is not empty, loop through the Journal entries
            // and populate all of the textboxes
            if (module.getJournal().getSize() > 0) {
                String imageViewId;
                int resID;
                maxPages = module.getJournal().getSize();
                index = maxPages - 1;

                //Populate date text
                String date = module.getJournal().getDates().get(index);
                imageViewId = "dateView";
                resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
                TextView view = findViewById(resID);
                view.setTextColor(getResources().getColor(R.color.black));
                view.setText(date);

                //Populate location text
                String location = module.getJournal().getLocations().get(index);
                imageViewId = "locationView";
                resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
                view = findViewById(resID);
                view.setTextColor(getResources().getColor(R.color.black));
                view.setText(location);


                //Populate description text
                String description = module.getJournal().getDescriptions().get(index);
                imageViewId = "descriptionView";
                resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
                view = findViewById(resID);
                view.setTextColor(getResources().getColor(R.color.black));
                view.setText(description);

            }

            //Turn on background music
            backgroundPlayer = BackgroundPlayer.getSingletonMedia();
            backgroundPlayer.start();
        }
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
     * This function will load the screen for adding entries
     * into the module's journal.
     */
    public void addJournalEntry(View view) {
        Intent intent = new Intent(this, JournalEntryView.class);
        intent.putExtra("module", module);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * This function will load the next screen in the module
     **/
    public void loadDrawingJournal(View view) {
        Intent intent = new Intent(this, DrawingJournalView.class);
        intent.putExtra("module", module);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void homeButtonPressed(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void goBackAPage(View view)
    {
        if(!module.getJournal().getDates().isEmpty())
        {
            if(index == 0)
            {
                index = maxPages;
            }
            index-=1;
            String imageViewId;
            int resID;
            //Populate date text
            String date = module.getJournal().getDates().get(index);
            imageViewId = "dateView";
            resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            TextView textView = findViewById(resID);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setText(date);

            //Populate location text
            String location = module.getJournal().getLocations().get(index);
            imageViewId = "locationView";
            resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            textView = findViewById(resID);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setText(location);


            //Populate descrioptoin text
            String description = module.getJournal().getDescriptions().get(index);
            imageViewId = "descriptionView";
            resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            textView = findViewById(resID);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setText(description);


        }
    }
    public void goForwardAPage(View view)
    {
        if(!module.getJournal().getDates().isEmpty())
        {
            index+=1;
            if(index == maxPages)
            {
                index = 0;
            }
            String imageViewId;
            int resID;
            //Populate date text
            String date = module.getJournal().getDates().get(index);
            imageViewId = "dateView";
            resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            TextView textView = findViewById(resID);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setText(date);

            //Populate location text
            String location = module.getJournal().getLocations().get(index);
            imageViewId = "locationView";
            resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            textView = findViewById(resID);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setText(location);


            //Populate description text
            String description = module.getJournal().getDescriptions().get(index);
            imageViewId = "descriptionView";
            resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
            textView = findViewById(resID);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setText(description);
        }
    }
}