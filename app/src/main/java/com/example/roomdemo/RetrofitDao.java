package com.example.roomdemo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface RetrofitDao {
    @Query("SELECT * FROM VoterModel")
    List<VoterModel> getAllVoter();
}
