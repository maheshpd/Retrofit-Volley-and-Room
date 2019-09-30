package com.example.roomdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrintAdapter extends RecyclerView.Adapter<PrintAdapter.MyPrintViewHolder> {
    Context context;
    List<printModel> list;

    public PrintAdapter(Context context, List<printModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyPrintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.print_item,parent,false);
        return new MyPrintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPrintViewHolder holder, int position) {
        printModel pm = list.get(position);
         holder.version.setText("version: "+pm.getVersion());
         holder.name.setText("name:"+pm.getName());
         holder.subject.setText("subject: "+pm.getSubjects());
         holder.minposition.setText("minposition: "+pm.getMinposition());
         holder.maxposition.setText("maxposition: "+pm.getMaxposition());
         holder.examid.setText("examid: "+pm.getExamid());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyPrintViewHolder extends RecyclerView.ViewHolder {
        TextView version,name,subject,minposition,maxposition,examid;
        public MyPrintViewHolder(@NonNull View itemView) {
            super(itemView);

            version = itemView.findViewById(R.id.version_txt);
            name = itemView.findViewById(R.id.name_txt);
            subject = itemView.findViewById(R.id.subjects_txt);
            minposition = itemView.findViewById(R.id.minposition);
            maxposition = itemView.findViewById(R.id.maxposition);
            examid = itemView.findViewById(R.id.examid);
        }
    }
}
