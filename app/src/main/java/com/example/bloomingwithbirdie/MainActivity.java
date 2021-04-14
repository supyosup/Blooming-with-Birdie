/** This MainActivity Class is the Main Screen the Application.
 * Each View acts as its own controller.
 */

package com.example.bloomingwithbirdie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//TODO: Add buttons for "My Drawings", "Badges", and "GrownUps", add them to the loadOtherView function.
// Find a way to make module data persist throughout the Application.

public class MainActivity extends AppCompatActivity {
    private static Module module1;
    private Module module2;
    private Module module3;
    private Module module4;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the ActionBar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getSupportActionBar().setTitle("Blooming With Birdie");
//        getSupportActionBar().setCustomView(ActionBar.DISPLAY_SHOW_CUSTOM);
        user = new User("Test User", "pa33w0rd");

        /**Ideally, these would be loaded from a DataBase - I'm not sure how realistic that is given the time frame...
         * Additionally, it doesn't seem the data for these persists when we change Activities (Screens), so we will
         * need to find a solution for this.*/
        // Create our Modules, set their names & videoId's for youtube
        module1 = new Module("Monarch Butterflies", "rVN0QPs3eyo", getResources().getColor(R.color.orange), "Monarch", "a");
        module2 = new Module("Bees", "XDc6Kss5--c", getResources().getColor(R.color.yellow));
        module3 = new Module("Rivers","1U-cgn3cEGA", getResources().getColor(R.color.blue));
        module4 = new Module("Wildflowers","Hy5wnwuIZ2M", getResources().getColor(R.color.red));

        // Update buttons texts, this can be done via variable binding in XML
        // but it was a huge headache to implement
        button1 = findViewById(R.id.module1);
        button1.setText(module1.getName());
        button2 = findViewById(R.id.module2);
        button2.setText(module2.getName());
        button3 = findViewById(R.id.module3);
        button3.setText(module3.getName());
        button4 = findViewById(R.id.module4);
        button4.setText(module4.getName());
    }

    /** Changes the view based on what module button is clicked and passes the necessary Module */
    public void loadModule(View view) {
        Intent intent = new Intent(this, YouTubeModuleView.class);

        // Select which module to pass based on which button was clicked
        switch (view.getId()) {
            case R.id.module1:
                intent.putExtra("module", module1);
                break;
            case R.id.module2:
                intent.putExtra("module", module2);
                break;
            case R.id.module3:
                intent.putExtra("module", module3);
                break;
            case R.id.module4:
                intent.putExtra("module", module4);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

    /** Same functionality as loadModule, however this handles the buttons that are no modules (Drawings, Badges, Grownups)*/
    public void loadOtherView(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.badgeButton:
                intent = new Intent(this, BadgeView.class);
                intent.putExtra("user", user);
                break;
            case R.id.drawingButton:
                intent = new Intent(this, MyDrawingsView.class);
                break;
            case R.id.grownupsButton:
                intent = new Intent(this, LoginView.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}