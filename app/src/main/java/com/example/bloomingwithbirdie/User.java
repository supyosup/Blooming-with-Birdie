/** The User class represents a User of the application.
 */

package com.example.bloomingwithbirdie;

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "badge")
    private List<Badge> badges;

    @Ignore
    private List<File> photos;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        // New Users won't have badges. Those will only apply when existing user
        badges = new ArrayList<>();
        photos = new ArrayList<>();
    }

    public void addPhoto(File photo) { photos.add(photo); }

    public List<File> getPhotos() { return photos; }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public void addBadge(Badge badge) {
        badges.add(badge);
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setPassword(String password){ this.password = password; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
