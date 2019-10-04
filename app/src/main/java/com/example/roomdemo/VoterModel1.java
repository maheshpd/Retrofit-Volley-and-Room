package com.example.roomdemo;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class VoterModel1 {
    @SerializedName("VOTING_CENTER")
    String VOTING_CENTER;

    @SerializedName("age")
    int age;

    @SerializedName("gender")
    String gender;

    @SerializedName("voter_namemarathi")
    public String voter_namemarathi;

    @SerializedName("voterno")
    String voterno;

    @SerializedName("votersrno")
    int votersrno;

    @SerializedName("part_no")
    int part_no;

    @SerializedName("voter_id")
    public int voter_id;

    public VoterModel1(String VOTING_CENTER, int age, String gender, String voter_namemarathi, String voterno, int votersrno, int part_no, int voter_id) {
        this.VOTING_CENTER = VOTING_CENTER;
        this.age = age;
        this.gender = gender;
        this.voter_namemarathi = voter_namemarathi;
        this.voterno = voterno;
        this.votersrno = votersrno;
        this.part_no = part_no;
        this.voter_id = voter_id;
    }

    public String getVOTING_CENTER() {
        return VOTING_CENTER;
    }

    public void setVOTING_CENTER(String VOTING_CENTER) {
        this.VOTING_CENTER = VOTING_CENTER;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVoter_namemarathi() {
        return voter_namemarathi;
    }

    public void setVoter_namemarathi(String voter_namemarathi) {
        this.voter_namemarathi = voter_namemarathi;
    }

    public String getVoterno() {
        return voterno;
    }

    public void setVoterno(String voterno) {
        this.voterno = voterno;
    }

    public int getVotersrno() {
        return votersrno;
    }

    public void setVotersrno(int votersrno) {
        this.votersrno = votersrno;
    }

    public int getPart_no() {
        return part_no;
    }

    public void setPart_no(int part_no) {
        this.part_no = part_no;
    }

    public int getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(int voter_id) {
        this.voter_id = voter_id;
    }
}
