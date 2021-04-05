package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ModuleCompleteView extends AppCompatActivity {

    private ImageView badgeView;
    private User user;
    private Badge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_complete_view);
        badgeView = (ImageView) findViewById(R.id.completeModuleBadgeView);
        Module module = (Module) getIntent().getSerializableExtra("module");
        badge = module.getBadge();
//        user.addBadge(badge);
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