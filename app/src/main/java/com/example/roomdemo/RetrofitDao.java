package com.example.roomdemo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RetrofitDao {
    @Query("SELECT * FROM VoterModel")
    List<VoterModel> getAllVoter();

    @Insert
    void insert(VoterModel voterModel);

    @Query("SELECT " + VoterModel.COL_POS + " FROM VoterModel")
    List<Integer> getButtonText();
}
