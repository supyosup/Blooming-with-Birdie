package com.example.bloomingwithbirdie;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class DatabaseClass extends RoomDatabase { //AppDatabase
    public abstract DaoClass daoClass();

    private static volatile DatabaseClass INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DatabaseClass getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (DatabaseClass.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseClass.class, "user_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
