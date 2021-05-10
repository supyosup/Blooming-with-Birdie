/**
 * The Journal class represents a Journal for a specific Module.
 * Entries in the Journal include Locations, Dates, and Descriptions of what the user saw.
 */

package com.example.bloomingwithbirdie;

import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {
    private String name;
    private ArrayList<String> dates;
    private ArrayList<String> descriptions;
    private ArrayList<String> locations;
    private int size;

    public Journal(String name) {
        this.name = name;
        dates = new ArrayList<>();
        descriptions = new ArrayList<>();
        locations = new ArrayList<>();
        size = 0;
    }

    public void addEntry(String date, String location, String description) {
        dates.add(date);
        locations.add(location);
        descriptions.add(description);
        size++;
    }

    public int getSize() {
        return size;
    }

    /** Used in for the "Where I saw it" Journal entry**/
    public void addLocation(String location) {
        locations.add(location);
    }

    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    public ArrayList<String> getDates() { return dates; }

    public ArrayList<String> getLocations() {
        return locations;
    }
}
