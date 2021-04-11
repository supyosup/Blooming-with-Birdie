package com.example.bloomingwithbirdie.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {

    public UserModel() {

    }

    public UserModel(int uid, String name, String password) {
        this.uid = uid;
        this.name = name;
        this.password = password;
    }

    //Primary key
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "password")
    private String password;

    public int getUid() { return uid; }

    public void setUid(int uid) { this.uid = uid; }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setPassword(String password){ this.password = password; }

    public String getPassword() { return password; }
}
