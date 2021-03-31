/** The User class represents a User of the application.
 */

package com.example.bloomingwithbirdie;

import java.util.ArrayList;

public class User {
    private ArrayList<Badge> badges;
    private String name;

    public User(String name) {
        this.name = name;
        badges = new ArrayList<>();
        Badge badge = new Badge("Monarch", "monarch.jpg");
        badges.add(badge);
        badges.add(badge);
        badges.add(badge);
        badges.add(badge);
        badges.add(badge);
        badges.add(badge);badges.add(badge);badges.add(badge);badges.add(badge);badges.add(badge);badges.add(badge);badges.add(badge);
    }

    public void addBadge(Badge badge) {
        badges.add(badge);
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }
}
