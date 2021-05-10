package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class BadgeView extends AppCompatActivity {
    private List<Badge> badges;
    private User user;
    private int counter = 0;
    private TextView noBadges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badge_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_green)));
        getSupportActionBar().setTitle("My Badges");

        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra("user");
            badges = user.getBadges();

            if (badges.isEmpty()) {
                noBadges = findViewById(R.id.noBadgeText);
                noBadges.setText("You don't have any badges yet!\nComplete a lesson to earn a badge!");
            }

            for (Badge badge: badges) {
                String imageViewId = "imageView" + counter;
                int resID = getResources().getIdentifier(imageViewId, "id", getPackageName());
                ImageView imageView = findViewById(resID);
                int resourceId = getResources().getIdentifier(badge.getFilePath(), "drawable", getPackageName());
                imageView.setImageDrawable(getResources().getDrawable(resourceId));
                counter++;
            }
        }
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}