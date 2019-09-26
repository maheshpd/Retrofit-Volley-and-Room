package com.example.roomdemo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VoterModel {


    String VOTING_CENTER;
    int age;
    String gender;
    @PrimaryKey(autoGenerate = true)
    private int id;
    public String voter_namemarathi;
    String voterno;
    int votersrno;

    int part_no;


    public int voter_id;

    public VoterModel(String VOTING_CENTER, int age, String gender, int id, String voter_namemarathi, String voterno, int votersrno, int part_no, int voter_id) {
        this.VOTING_CENTER = VOTING_CENTER;
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.voter_namemarathi = voter_namemarathi;
        this.voterno = voterno;
        this.votersrno = votersrno;
        this.part_no = part_no;
        this.voter_id = voter_id;
    }


    //    public VoterModel(String voter_namemarathi, String gender, String voterno, String VOTING_CENTER, int voter_id, int votersrno, int age, int part_no) {
//        this.voter_namemarathi = voter_namemarathi;
//        this.gender = gender;
//        this.voterno = voterno;
//        this.VOTING_CENTER = VOTING_CENTER;
//        this.voter_id = voter_id;
//        this.votersrno = votersrno;
//        this.age = age;
//        this.part_no = part_no;
//    }


//    public VoterModel(String voter_namemarathi, String gender, String voterno, int age, int part_no) {
//        this.voter_namemarathi = voter_namemarathi;
//        this.gender = gender;
//        this.voterno = voterno;
//        this.age = age;
//        this.part_no = part_no;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoter_namemarathi() {
        return voter_namemarathi;
    }

    public void setVoter_namemarathi(String voter_namemarathi) {
        this.voter_namemarathi = voter_namemarathi;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVoterno() {
        return voterno;
    }

    public void setVoterno(String voterno) {
        this.voterno = voterno;
    }

    public String getVOTING_CENTER() {
        return VOTING_CENTER;
    }

    public void setVOTING_CENTER(String VOTING_CENTER) {
        this.VOTING_CENTER = VOTING_CENTER;
    }

    public int getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(int voter_id) {
        this.voter_id = voter_id;
    }

    public int getVotersrno() {
        return votersrno;
    }

    public void setVotersrno(int votersrno) {
        this.votersrno = votersrno;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPart_no() {
        return part_no;
    }

    public void setPart_no(int part_no) {
        this.part_no = part_no;
    }
}
