/** The User class represents a User of the application.
 */

package com.example.bloomingwithbirdie;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<Badge> badges = null;
    private String name;

    public User(String name) {
        this.name = name;
        if (badges == null) {
            badges = new ArrayList<>();
        } else {
            // TODO: else load from DB
        }
    }

    public void addBadge(Badge badge) {
        badges.add(badge);
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }
}
