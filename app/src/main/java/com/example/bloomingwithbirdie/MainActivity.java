/** This MainActivity Class is the Main Screen the Application.
 * Each View acts as its own controller.
 */

package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BackgroundPlayer backgroundMusic;
    MediaPlayer backgroundPlayer;
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

        /**
         * If the user already exists, we would just want to load the user that is passed from whatever view we just came from.
         * Currently the else part of this statement creates a new user, but this won't be needed when we get the Login feature completed.
         * **/
        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra("user");
        } else {
            user = new User("Test User", "test@gmail.com","pa33w0rd");
        }

        // Create our Modules, set their names & videoId's for youtube
        module1 = new Module("Monarch Butterflies", "LGxVX5qnCmo", getResources().getColor(R.color.orange), "Monarch", "monarch_badge");
        module2 = new Module("Bees", "WJQJQrbsuIE", getResources().getColor(R.color.yellow), "Bee", "bee_badge");
        module3 = new Module("Rivers","1iv3rwTyXQA", getResources().getColor(R.color.blue), "River", "river_badge");
        module4 = new Module("Wildflowers","e43SK_i3Yis", getResources().getColor(R.color.red), "Wildflower", "wildflower_badge");

        button1 = findViewById(R.id.module1);
        button1.setText(module1.getName());
        button2 = findViewById(R.id.module2);
        button2.setText(module2.getName());
        button3 = findViewById(R.id.module3);
        button3.setText(module3.getName());
        button4 = findViewById(R.id.module4);
        button4.setText(module4.getName());

        //Initialize Media Player
        backgroundMusic = BackgroundPlayer.getInstance();
        backgroundMusic.init(getApplicationContext());
        backgroundPlayer = BackgroundPlayer.getSingletonMedia();
        backgroundPlayer.start();

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
        if (intent != null) {
            intent.putExtra("user", user);
            startActivity(intent);
        }
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
                intent.putExtra("user", user);
                break;
            case R.id.grownupsButton:
                intent = new Intent(this, LoginView.class);
                intent.putExtra("user", user);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}