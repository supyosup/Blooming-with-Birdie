package com.example.bloomingwithbirdie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.bloomingwithbirdie.User;
@Dao
public interface DaoClass {
    //Select All
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid = :id")
    List<User> loadAllById(int id);

    @Insert
    void insertAll(User model);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    void deleteAll();

}
