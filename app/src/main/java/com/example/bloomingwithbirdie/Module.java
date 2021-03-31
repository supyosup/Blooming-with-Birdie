/**
 * The Module class represents a Module for the Application.
 * Example Modules would include the Rivers, Monarchs, Bees, and Wildflowers Modules.
 * The Module class stores all information for each module, include the name, journal entries,
 * YouTube video id's, and whatever else we may need to add.
 */

package com.example.bloomingwithbirdie;

import androidx.databinding.BaseObservable;
import java.io.Serializable;

//TODO: This class will probably need more added to it - i.e. Badges

public class Module extends BaseObservable implements Serializable {
    private String name;
    private String videoId;
    private Journal journal;
    private int color;
    private Badge badge;

    public Module(String name, String videoId, int color) {
        this.name = name;
        this.videoId = videoId;
        journal = new Journal(name);
        this.color = color;
    }

    public Module(String name, String videoId, int color, String badgeName, String filePath) {
        this.name = name;
        this.videoId = videoId;
        journal = new Journal(name);
        this.color = color;
        badge = new Badge(badgeName, filePath);
    }

    public int getColor() {
        return color;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Badge getBadge() { return badge; }
}
