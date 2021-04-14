package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ModuleCompleteView extends AppCompatActivity {

    private ImageView badgeView;
    private User user;
    private Badge badge;
    private TextView textView;
    private Module module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_complete_view);
        module = (Module) getIntent().getSerializableExtra("module");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(module.getColor()));
        getSupportActionBar().setTitle("Congratulations!");
        badgeView = (ImageView) findViewById(R.id.completeModuleBadgeView);
        badge = module.getBadge();
        user = (User) getIntent().getSerializableExtra("user");

        boolean userHasBadge = false;
        List<Badge> badges = user.getBadges();
        for (Badge userBadge : badges) {
            if (userBadge.getName().compareTo(badge.getName()) == 0) {
                userHasBadge = true;
            }
        }

        if (userHasBadge == false) {
            user.addBadge(badge);
        }

        textView = findViewById(R.id.completeModuleTextbox);
        textView.setText("You finished the " + module.getName() + " Module and earned this badge!");
        textView.setGravity(Gravity.CENTER);
        int imageId = getResources().getIdentifier(badge.getFilePath(), "drawable", getPackageName());
        badgeView.setImageDrawable(getResources().getDrawable(imageId));
    }

    public void completeModule(View view) {
        //TODO: Add badge to user here, save to DB?
        Intent intent = new Intent(this, BadgeView.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}