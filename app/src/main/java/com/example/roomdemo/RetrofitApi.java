package com.example.roomdemo;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RetrofitApi {
    @POST("Api_getallvoterthread")
    Call<List<VoterModel>> getPost(@QueryMap String string);
}
