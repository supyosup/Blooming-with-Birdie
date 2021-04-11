package com.example.bloomingwithbirdie.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.bloomingwithbirdie.EntityClass.UserModel;
@Dao
public interface DaoClass {
    //Select All
    @Query("SELECT * FROM user")
    List<UserModel> getAll();

    @Query("SELECT * FROM user WHERE uid = :id")
    List<UserModel> loadAllById(int id);

    @Insert
    void insertAllData(UserModel model);

    @Delete
    void delete(UserModel user);

    @Query("DELETE FROM User")
    void deleteAll();

}
