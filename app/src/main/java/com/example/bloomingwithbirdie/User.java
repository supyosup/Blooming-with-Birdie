/** The User class represents a User of the application.
 */

package com.example.bloomingwithbirdie;

import java.io.Serializable;
import java.util.ArrayList;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "badge")
    private ArrayList<Badge> badges = null;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        // New Users won't have badges. Those will only apply when existing user
        badges = new ArrayList<>();
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public void addBadge(Badge badge) {
        badges.add(badge);
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setPassword(String password){ this.password = password; }

    public String getPassword() { return password; }
}
