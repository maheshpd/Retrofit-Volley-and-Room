package com.example.roomdemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RetrofitApi retrofitApi;
    private RetrofitDao retrofitDao;

    RecyclerView recyclerView;
    VoterAdapter adapter;
    ProgressBar moreDataProgressBar;
    String dataurl = "https://admin.electionwinner.in//ElectionRoute/Api_getallvoterthread";
    String total_count = "https://admin.electionwinner.in//ElectionRoute/Api_getallvoterthreadcount";
    List<VoterModel1> onlinelist = new ArrayList<>();
    List<VoterModel> offlinelist = new ArrayList<>();

    private int Voter_SrNo1;
    private int Voter_SrNo2;

    String namemarathi, gender, Voting_center, voterno;
    int partno, age, votersrno, voter_id;

    LinearLayout lin;

    ProgressDialog dialog;

    List<TotalCountModel> totlaVoterCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_data);
        lin = findViewById(R.id.buttonLin);
        dialog = new ProgressDialog(this);


        getAllVoterCount();
    }

    private void getAllVoterCount() {

        retrofitApi = RetrofitInstance.getRetrofitInstance().create(RetrofitApi.class);
        Call<List<TotalCountModel>> call = retrofitApi.getData();
        call.enqueue(new Callback<List<TotalCountModel>>() {
            @Override
            public void onResponse(Call<List<TotalCountModel>> call, Response<List<TotalCountModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Response Code", Toast.LENGTH_SHORT).show();
                }
                totlaVoterCount = response.body();

                if (totlaVoterCount.size()== 0) {
                    Toast.makeText(MainActivity.this, "No voter data", Toast.LENGTH_SHORT).show();
                } else {
                    displayButton(totlaVoterCount);
                }
            }

            @Override
            public void onFailure(Call<List<TotalCountModel>> call, Throwable t) {

            }
        });


    }

    private void displayButton(List<TotalCountModel> totlaVoterCount) {

        int totalcount = Integer.parseInt(totlaVoterCount.get(0).getTotalcount());

        for (int i = 1; i <= totalcount; i++) {
            final Button votercountBtn = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 0);
            Voter_SrNo1 = i;
            Voter_SrNo2 = i + 10000;
            votercountBtn.setText(Voter_SrNo1 + "-" + Voter_SrNo2);
            votercountBtn.setId(i);
            i = i + 10000;
            votercountBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String text = votercountBtn.getText().toString();
                    String[] firstNo = text.split("-");
                    Voter_SrNo1 = Integer.parseInt(firstNo[0]);
                    Voter_SrNo2 = Integer.parseInt(firstNo[1]);
                    votercountBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    votercountBtn.setTextColor(getResources().getColor(android.R.color.white));
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(Voter_SrNo1 + "-" + Voter_SrNo2 + " Do you want to download file?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog.setMessage("File is downloading...");
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();


                            Log.d(TAG, "Voter_SrNo1: " + Voter_SrNo1);
                            Log.d(TAG, "Voter_SrNo2: " + Voter_SrNo2);

//                            getPost(Voter_SrNo1, Voter_SrNo2);

                            getPost(Voter_SrNo1,Voter_SrNo2);

                        }
                    });
                    builder.show();


                }
            });

            lin.addView(votercountBtn);
        }
    }


    private void getPost(int Voter_SrNo1, int Voter_SrNo2) {
        Map<String, Object> jsonParam = new ArrayMap<>();
        jsonParam.put("Voter_SrNo1", Voter_SrNo1);
        jsonParam.put("Voter_SrNo2", Voter_SrNo2);

        Gson gson;
        gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://admin.electionwinner.in/ElectionRoute/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitApi = retrofit.create(RetrofitApi.class);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParam)).toString());

        Call<List<VoterModel1>> call = retrofitApi.getPost(body);
        onlinelist.clear();
        call.enqueue(new Callback<List<VoterModel1>>() {
            @Override
            public void onResponse(Call<List<VoterModel1>> call, retrofit2.Response<List<VoterModel1>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Response Code " + response.code(), Toast.LENGTH_SHORT).show();
                }
                onlinelist.addAll(response.body());
                new saveInVotter().execute();

            }

            @Override
            public void onFailure(Call<List<VoterModel1>> call, Throwable t) {
                dialog.dismiss();
            }
        });
        Bitmap res = null;
        Glide.with(this)
                .asBitmap()
                .load("")
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap res = resource;
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(res);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private class saveInVotter extends AsyncTask<Void, Void, List<VoterModel>> {

        VoterModel voterModel;

        @Override
        protected List<VoterModel> doInBackground(Void... voids) {
            for (int i = 0; i < onlinelist.size(); i++) {
                namemarathi = onlinelist.get(i).getVoter_namemarathi();
                partno = onlinelist.get(i).getPart_no();
                age = onlinelist.get(i).getAge();
                gender = onlinelist.get(i).getGender();
                Voting_center = onlinelist.get(i).getVOTING_CENTER();
                voterno = onlinelist.get(i).getVoterno();
                voter_id = onlinelist.get(i).getVoter_id();
                votersrno = onlinelist.get(i).getVotersrno();

                voterModel = new VoterModel(Voting_center, age, gender, namemarathi, voterno, votersrno, partno, voter_id);

                VoterDatabaseClient.getInstance(getApplicationContext()).getVoterDataBase().retrofitDao().insert(voterModel);

            }


            return null;
        }

        @Override
        protected void onPostExecute(List<VoterModel> voterModels) {
            super.onPostExecute(voterModels);
            dialog.dismiss();
//            new getVoterData().execute();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.download) {
            startActivity(new Intent(MainActivity.this, DefaultScreen.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
