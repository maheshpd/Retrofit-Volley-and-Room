package com.example.roomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {VoterModel.class},version = 1)
public abstract class VoterDataBase extends RoomDatabase {
    public abstract RetrofitDao retrofitDao();
}
