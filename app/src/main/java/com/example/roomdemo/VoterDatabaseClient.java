package com.example.roomdemo;

import android.content.Context;

import androidx.room.Room;

public class VoterDatabaseClient {
    private static VoterDatabaseClient mInstance;
    private Context context;
    private VoterDataBase voterDataBase;

    public VoterDatabaseClient(Context context) {
        this.context = context;
        voterDataBase = Room.databaseBuilder(context,VoterDataBase.class,"Voter").build();
    }

    public static synchronized VoterDatabaseClient getInstance(Context context) {
        if (mInstance == null)
            mInstance = new VoterDatabaseClient(context);
        return mInstance;
    }

    public VoterDataBase getVoterDataBase() {
        return voterDataBase;
    }

}
