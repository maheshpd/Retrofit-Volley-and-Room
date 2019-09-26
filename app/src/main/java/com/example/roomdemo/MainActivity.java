package com.example.roomdemo;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RetrofitApi retrofitApi;
    TextView textView;

    String dataurl = "https://admin.electionwinner.in//ElectionRoute/Api_getallvoterthread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

//        Gson gson = new GsonBuilder().serializeNulls().create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://admin.electionwinner.in//ElectionRoute/")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        retrofitApi = retrofit.create(RetrofitApi.class);

        getPost();
    }

    private void getPost() {

        RequestQueue queue = Volley.newRequestQueue(this);

        Map<String, Integer> params = new HashMap<>();
        params.put("Voter_SrNo1", 1);
        params.put("Voter_SrNo2", 100);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(dataurl,
                new JSONObject(params), new com.android.volley.Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    JSONObject c = null;
                    for (int i = 0; i < arr.length(); i++) {
                        c = arr.getJSONObject(i);
                        int voter_id = Integer.parseInt(c.getString("voter_id"));
                        int votersrno = Integer.parseInt(c.getString("votersrno"));
                        String voterno = c.getString("voterno");
                        String VOTING_CENTER = c.getString("VOTING_CENTER");
                        String gender = c.getString("gender");
                        int age = Integer.parseInt(c.getString("age"));
                        String voter_namemarathi = c.getString("voter_namemarathi");
                        int part_no = Integer.parseInt(c.getString("part_no"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, " " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };


        queue.add(jsonObjectRequest);

    }
//
//
////        StringRequest sr = new StringRequest(Request.Method.POST, dataurl, new Response.Listener<String>() {
////            @Override
////            public void onResponse(String response) {
////                try {
////                    JSONArray arr = new JSONArray(response);
////                    JSONObject c = null;
////                    for (int i = 0; i < arr.length(); i++) {
////                        c = arr.getJSONObject(i);
////                        int voter_id = Integer.parseInt(c.getString("voter_id"));
////                        int votersrno = Integer.parseInt(c.getString("votersrno"));
////                        String voterno = c.getString("voterno");
////                        String VOTING_CENTER = c.getString("VOTING_CENTER");
////                        String gender = c.getString("gender");
////                        int age = Integer.parseInt(c.getString("age"));
////                        String voter_namemarathi = c.getString("voter_namemarathi");
////                        int part_no = Integer.parseInt(c.getString("part_no"));
////
////
//////                        SelectTagModel stm = new SelectTagModel(name, place, tag, time, ctf, date, tf,sessionname);
//////                        taglist.add(stm);
//////                        progressDialog.dismiss();
//////                        tagAdapter.notifyDataSetChanged();
////
////
//////                        VoterModel voterModel = new VoterModel(voter_namemarathi, gender, voterno, VOTING_CENTER, voter_id, votersrno, age, part_no);
//////
//////
//////                        String content = "";
//////                        content += "Voter id: " + voterModel.voter_id + "\n";
//////                        content += "Voter serialno: " + voterModel.votersrno + "\n";
//////                        content += "Voting center: " + voterModel.VOTING_CENTER + "\n";
//////                        content += "Voter no: " + voterModel.voterno + "\n";
//////
//////                        textView.append(content);
////
////
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
////            }
////        }) {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                JsonObject jsonObject = new JsonObject();
////                jsonObject.add("Voter_SrNo1",1);
////
////                Map<String, String> params = new HashMap<>();
////                params.put("Voter_SrNo1", "1");
////                params.put("Voter_SrNo2", "100");
////                return params;
////
////            }
////        };
//
//
//        queue.add(jsonObjectRequest);
//    }

//    private void getPost() {
////        Map<String, Integer> para = new HashMap<>();
////        para.put("Voter_SrNo1", 1);
////        para.put("Voter_SrNo2", 100);
//
//        JSONObject jsonObject = new JSONObject();
//        Call<List<VoterModel>> call = null;
//        try {
//            jsonObject = new JSONObject();
//            jsonObject.put("Voter_SrNo1",1);
//            jsonObject.put("Voter_SrNo2", 100);
//
//            call = retrofitApi.getPost(jsonObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//        call.enqueue(new Callback<List<VoterModel>>() {
//            @Override
//            public void onResponse(Call<List<VoterModel>> call, Response<List<VoterModel>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "Response Code " + response.code(), Toast.LENGTH_SHORT).show();
//                }
//
//                List<VoterModel> voterModels = response.body();
////
//                for (VoterModel voterModel : voterModels) {
//
//                    String content = "";
//                    content += "Voter id: " + voterModel.voter_id + "\n";
//                    content += "Voter serialno: " + voterModel.votersrno + "\n";
//                    content += "Voting center: " + voterModel.VOTING_CENTER + "\n";
//                    content += "Voter no: " + voterModel.voterno + "\n";
//
//                    textView.append(content);
//                }
//
//            }
//            @Override
//            public void onFailure(Call<List<VoterModel>> call, Throwable t) {
//
//            }
//        });
//
//
////        Call<List<VoterModel>> call = retrofitApi.getPost(jsonObject.toString());
////
////        call.enqueue(new Callback<List<VoterModel>>() {
////            @Override
////            public void onResponse(Call<List<VoterModel>> call, Response<List<VoterModel>> response) {
////                if (!response.isSuccessful()) {
////                    Toast.makeText(MainActivity.this, "Response Code " + response.code(), Toast.LENGTH_SHORT).show();
////                }
////
////                List<VoterModel> voterModels = response.body();
////
////                for (VoterModel voterModel : voterModels) {
////
////                    String content = "";
////                    content += "Voter id: "+voterModel.voter_id + "\n";
////                    content += "Voter serialno: "+voterModel.votersrno + "\n";
////                    content += "Voting center: "+voterModel.VOTING_CENTER + "\n";
////                    content += "Voter no: "+voterModel.voterno + "\n";
////
////                    textView.append(content);
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<VoterModel>> call, Throwable t) {
////                    textView.setText(t.getMessage());
////            }
////        });
//    }
}
