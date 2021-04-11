package com.example.bloomingwithbirdie.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {

    public UserModel() {

    }

    public UserModel(String name, String password) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setPassword(String password){ this.password = password; }

    public String getPassword() { return password; }
}
