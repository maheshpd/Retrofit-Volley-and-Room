package com.example.roomdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RetrofitApi retrofitApi;
    private RetrofitDao retrofitDao;

    RecyclerView recyclerView;
    VoterAdapter adapter;

    String dataurl = "https://admin.electionwinner.in//ElectionRoute/Api_getallvoterthread";
    List<VoterModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPost();

        recyclerView = findViewById(R.id.recycler_data);
       adapter = new VoterAdapter(list,MainActivity.this);

        LinearLayoutManager llm = new LinearLayoutManager(this);

    }

    private void getPost() {
        Map<String, Object> jsonParam = new ArrayMap<>();
        jsonParam.put("Voter_SrNo1", 1);
        jsonParam.put("Voter_SrNo2", 100);

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://admin.electionwinner.in//ElectionRoute/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitApi = retrofit.create(RetrofitApi.class);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParam)).toString());

        Call<List<VoterModel>> call = retrofitApi.getPost(body);

        call.enqueue(new Callback<List<VoterModel>>() {
            @Override
            public void onResponse(Call<List<VoterModel>> call, retrofit2.Response<List<VoterModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Response Code " + response.code(), Toast.LENGTH_SHORT).show();
                }
                writeRecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<VoterModel>> call, Throwable t) {

            }
        });

    }

    private void writeRecycler(List<VoterModel> body) {
        VoterAdapter adapter = new VoterAdapter(body,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }

//    private void writeRecycler(Response<List<VoterModel>> response) {
//        try {
//            JSONArray array = new JSONArray(response);
//            JSONObject c = null;
//
//            for (int i = 0; i <array.length() ; i++) {
//                c = array.getJSONObject(i);
//
//                String voter_namemarathi = c.getString("voter_namemarathi");
//                int part_no = Integer.parseInt(c.getString("part_no"));
//                String gender = c.getString("gender");
//                int age = Integer.parseInt(c.getString("age"));
//                String voterno = c.getString("voterno");
//
//                VoterModel voterModel = new VoterModel(voter_namemarathi,gender,voterno,age,part_no);
//                list.add(voterModel);
//            }
//
//            adapter.notifyDataSetChanged();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();

//        new getVoterData().execute();
    }

    private class getVoterData extends AsyncTask<Void,Void,List<VoterModel>> {
        @Override
        protected List<VoterModel> doInBackground(Void... voids) {

            list = VoterDatabaseClient.getInstance(MainActivity.this)
                    .getVoterDataBase()
                    .retrofitDao()
                    .getAllVoter();

            return list;
        }

        @Override
        protected void onPostExecute(List<VoterModel> voterModels) {
            super.onPostExecute(voterModels);

            VoterAdapter adapter = new VoterAdapter(voterModels,MainActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            adapter.notifyDataSetChanged();
        }
    }
}
