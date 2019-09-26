package com.example.roomdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VoterAdapter extends RecyclerView.Adapter< VoterAdapter.VoterViewHolder> {

    List<VoterModel> list;
    Context context;

    public VoterAdapter(List<VoterModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VoterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voter_item,parent,false);
        return new VoterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoterViewHolder holder, int position) {
        VoterModel vm = list.get(position);

        holder.voterid.setText("1");

        holder.marathiname.setText(vm.getVoter_namemarathi());
//        holder.age.setText(vm.getAge());
//        holder.gender.setText(vm.getGender());
//        holder.voterno.setText(vm.getVoterno());
//        holder.partno.setText(vm.getPart_no());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VoterViewHolder extends RecyclerView.ViewHolder{
        TextView marathiname,partno,gender,voterno,age,voterid;
        public VoterViewHolder(@NonNull View itemView) {
            super(itemView);

            marathiname=itemView.findViewById(R.id.marathiname);
            partno=itemView.findViewById(R.id.partno);
            gender=itemView.findViewById(R.id.gender);
            voterno=itemView.findViewById(R.id.voterno);
            age=itemView.findViewById(R.id.age);
            voterid=itemView.findViewById(R.id.voterid);

        }
    }
}