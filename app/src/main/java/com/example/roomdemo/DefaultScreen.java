package com.example.roomdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.http.Url;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultScreen extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static final String TAG = "DefaultScreen";

    private static final int WRITE_REQUEST_CODE = 300;
    List<printModel> list;

    RecyclerView recyclerView;
    TextView total;
    VoterAdapter adapter;
   ProgressDialog dialog;
    private String url;

    List<VoterModel> offlinelist = new ArrayList<>();
    SearchView search_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_screen);

        search_view = findViewById(R.id.searchView);
        search_view.setOnQueryTextListener(this);
        dialog = new ProgressDialog(this);

        recyclerView = findViewById(R.id.recycler_data);
        total = findViewById(R.id.totalno);
        new getVoterData().execute();


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<VoterModel> fillterlist = new ArrayList<>();
        for (VoterModel vm:offlinelist) {
            String voterno = vm.getVoterno().toLowerCase();
            if (voterno.contains(newText)){
                fillterlist.add(vm);
            }
        }
        adapter.setFilter(fillterlist);
        return true;
    }

//    private class DownloadFile extends AsyncTask<String,String,String> {
//        private ProgressDialog progressDialog;
//        private String fileName;
//        private String folder;
//        private boolean isDownloaded;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            this.progressDialog = new ProgressDialog(DefaultScreen.this);
//            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            this.progressDialog.setCancelable(false);
//            this.progressDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... f_url) {
//            int count;
//            try {
//                URL url = new URL(f_url[0]);
//                URLConnection connection = url.openConnection();
//                connection.connect();
//
//                // getting file length
//                int lengthOfFile = connection.getContentLength();
//
//                // input stream to read file - with 8k buffer
//                InputStream input = new BufferedInputStream(url.openStream(), 8192);
//
//                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//
//                //Extract file name from URL
//                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());
//
//                //Create androiddeft folder if it does not exist
//                File directory = new File(folder);
//
//                //External directory path to save file
//                folder = Environment.getExternalStorageDirectory() + File.separator + "androied/";
//
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//
//                // Output stream to write file
//                OutputStream output = new FileOutputStream(folder + fileName);
//
//                byte data[] = new byte[1024];
//
//                long total = 0;
//                 while((count=input.read(data)) != -1) {
//                     total += count;
//
//                     // publishing the progress....
//                     // After this onProgressUpdate will be called
//                     publishProgress("" + (int) ((total * 100) / lengthOfFile));
//                     Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));
//
//                     // writing data to file
//                     output.write(data, 0, count);
//                 }
//
//                // flushing output
//                output.flush();
//
//                // closing streams
//                output.close();
//                input.close();
//                return "Downloaded at: " + folder + fileName;
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return "Something went wrong";
//        }
//
//
//        /**
//         * Updating progress bar
//         */
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//
//            //setting progress percentage
//            progressDialog.setProgress(Integer.parseInt(values[0]));
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            //dismiss the dialog after the file was download
//            this.progressDialog.dismiss();
//
//            //Display File path after downloading
//            Toast.makeText(DefaultScreen.this, s, Toast.LENGTH_SHORT).show();
//        }
//    }


    private class getVoterData extends AsyncTask<Void, Void, List<VoterModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Please wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected List<VoterModel> doInBackground(Void... voids) {
            offlinelist.clear();
            offlinelist = VoterDatabaseClient.getInstance(DefaultScreen.this)
                    .getVoterDataBase()
                    .retrofitDao()
                    .getAllVoter();

            return offlinelist;
        }

        @Override
        protected void onPostExecute(List<VoterModel> voterModels) {
            super.onPostExecute(voterModels);

            adapter = new VoterAdapter(voterModels, DefaultScreen.this);
            recyclerView.setLayoutManager(new GridLayoutManager(DefaultScreen.this,2));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            adapter.notifyDataSetChanged();
            total.setText("Total No: "+offlinelist.size());
            dialog.dismiss();
        }
    }

}
