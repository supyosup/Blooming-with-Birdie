package com.example.bloomingwithbirdie.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.bloomingwithbirdie.EntityClass.UserModel;

@Dao
public interface DaoClass {

    @Insert
    void insertAllData( UserModel model);

    //Select All
    @Query("select * from user")
    List<UserModel> getAllData();
}
