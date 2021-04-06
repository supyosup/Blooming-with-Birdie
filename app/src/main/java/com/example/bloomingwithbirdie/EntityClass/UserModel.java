package com.example.bloomingwithbirdie.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {

    //Primary key
    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "name")
    private String name;

    public void setNam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
