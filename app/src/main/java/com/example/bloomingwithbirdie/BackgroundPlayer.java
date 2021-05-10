package com.example.bloomingwithbirdie;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundPlayer {
    static MediaPlayer backgroundPlayer;
    private static BackgroundPlayer ourInstance = new BackgroundPlayer();
    private Context appContext;
    private BackgroundPlayer() { }

    public static Context get()
    {
        return getInstance().getContext();
    }
    public static synchronized BackgroundPlayer getInstance()
    {
        return ourInstance;
    }
    public void init(Context context)
    {
        if(appContext == null){
            this.appContext = context;
        }
    }
    private Context getContext()
    {
        return appContext;
    }
    public static MediaPlayer getSingletonMedia()
    {
        if(backgroundPlayer == null) {
            backgroundPlayer = MediaPlayer.create(get(), R.raw.bloomin_w_birdie_theme);
            backgroundPlayer.setLooping(true);
        }
        return backgroundPlayer;
    }


}
