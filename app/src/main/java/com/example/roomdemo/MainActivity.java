package com.example.roomdemo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RetrofitApi retrofitApi;
    private RetrofitDao retrofitDao;

    RecyclerView recyclerView;
    VoterAdapter adapter;
    ProgressBar moreDataProgressBar;
    String dataurl = "https://admin.electionwinner.in//ElectionRoute/Api_getallvoterthread";
    List<VoterModel> list = new ArrayList<>();

    private int Voter_SrNo1 = 1;
    private int Voter_SrNo2 = 100;

    String namemarathi,gender,Voting_center,voterno;
    int partno,age,votersrno,voter_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPost();

//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//                Log.d( "run: ","1");
//
//
//            }
//        },0,10000);


//        final Handler handler = new Handler();
//        final Runnable perioddicUpdate = new Runnable() {
//            @Override
//            public void run() {
//
//                handler.postDelayed(,10*1000,sd)
//
//            }
//        };

        moreDataProgressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recycler_data);
         adapter = new VoterAdapter(list,MainActivity.this);


    }

    private void getPost() {
        Map<String, Object> jsonParam = new ArrayMap<>();
        jsonParam.put("Voter_SrNo1", Voter_SrNo1);
        jsonParam.put("Voter_SrNo2", Voter_SrNo2);

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

                 list =response.body();


                new saveDena().execute();

//                InsertData();

//

//                if (response.body().get(0).get)


//                writeRecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<VoterModel>> call, Throwable t) {

            }
        });

    }

    private void InsertData() {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


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
//

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                new getVoterData().execute();
            }
        },0,10000);


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


    private class saveDena extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <list.size() ; i++) {
                namemarathi = list.get(i).getVoter_namemarathi();
                partno = list.get(i).getPart_no();
                age = list.get(i).getAge();
                gender = list.get(i).getGender();
                Voting_center = list.get(i).getVOTING_CENTER();
                voterno = list.get(i).getVoterno();
                voter_id = list.get(i).getVoter_id();
                votersrno = list.get(i).getVotersrno();

                Log.d( "name: ",list.get(i).getVoter_id()+" "+list.get(i).getVoter_namemarathi());
            }

            VoterModel voterModel = new VoterModel(Voting_center,age,gender,namemarathi,voterno,votersrno,partno,voter_id);

            VoterDatabaseClient.getInstance(getApplicationContext()).getVoterDataBase().retrofitDao().insert(voterModel);
            return null;
        }
    }
}
