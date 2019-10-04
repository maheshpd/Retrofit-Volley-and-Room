package com.example.roomdemo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RetrofitApi {

    @POST("Api_getallvoterthread")
    Call<List<VoterModel1>> getPost(@Body RequestBody params);
}
