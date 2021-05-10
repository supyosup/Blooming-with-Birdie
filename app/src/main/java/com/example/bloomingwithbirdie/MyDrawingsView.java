package com.example.bloomingwithbirdie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MyDrawingsView extends AppCompatActivity {
    private User user;
    private List<File> photos = null;
    private int counter = 0;
    private int maxPages;
    private int index = 0;
    private ImageView imageView;
    private Bitmap myBitmap;
    private TextView numOfPhotos;
    private ImageView prevButton;
    private ImageView nextButton;
    private TextView noDrawings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_drawings_view);
        getSupportActionBar().setTitle("My Drawings");

        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra("user");
            photos = user.getPhotos();
            maxPages = photos.size();
        }

        if (!photos.isEmpty()) {
            imageView = findViewById(R.id.photo);
            numOfPhotos = findViewById(R.id.numOfPhotos);
            updateImage();

            if (photos.size() > 1) {
                prevButton = findViewById(R.id.prevPageButton);
                prevButton.setImageDrawable(getResources().getDrawable(R.drawable.left_arrow));
                nextButton = findViewById(R.id.nextPageButton);
                nextButton.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow));
            }
        } else {
            noDrawings = findViewById(R.id.noDrawingsText);
            noDrawings.setText("You don't have any drawings yet!\nCome back after you draw!");
        }
    }

    public void goBackAPage(View view) {
        if (!photos.isEmpty()) {

            if (index == 0) {
                index = maxPages;
            }

            index -= 1;
            updateImage();
        }
    }

    private void updateImage() {
        myBitmap = BitmapFactory.decodeFile(photos.get(index).getAbsolutePath());
        imageView.setImageBitmap(myBitmap);
        numOfPhotos.setText("Drawings: " + (index  + 1) + " / " + maxPages);
    }

    public void goForwardAPage(View view) {
       if (!photos.isEmpty()) {

           index += 1;

           if (index == maxPages) {
               index = 0;
           }
           updateImage();
       }
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}