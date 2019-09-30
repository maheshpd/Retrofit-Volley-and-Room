package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DefaultScreen extends AppCompatActivity {

    List<printModel> list;
    RecyclerView print_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_screen);

        print_rv = findViewById(R.id.print_rv);

//        list = DBHelper.getInstance(this).getAllData();
//
//        for (int i = 0; i <list.size() ; i++) {
//            String content = "";
//            content += "version: "+list.get(i).getVersion() +"\n";
//            content += "name: "+list.get(i).getName()+"\n";
//            content += "subjects: "+list.get(i).getSubjects()+"\n";
//            content += "minposition: "+list.get(i).getMinposition()+"\n";
//            content += "maxposition: "+list.get(i).getMaxposition()+"\n";
//            content += "examid: "+list.get(i).getExamid()+"\n";
//
//            resultTxt.setText(content);
//        }

        print_rv.setLayoutManager(new LinearLayoutManager(this));
        print_rv.setHasFixedSize(true);

        PrintAdapter adapter = new PrintAdapter(this,DBHelper.getInstance(this).getAllData());
        print_rv.setAdapter(adapter);
    }
}
