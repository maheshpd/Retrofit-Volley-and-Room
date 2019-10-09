package com.example.roomdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//@Entity(indices = {@Index(value ={"voter_id","voter_namemarathi","part_no","votersrno","voterno"},unique = true)})
@Entity
public class VoterModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    String VOTING_CENTER;
    int age;
    String gender;
    public String voter_namemarathi;
    String voterno;
    int votersrno;
    int part_no;
    public int voter_id;

    public final static String COL_POS = "position";

    @ColumnInfo(name = COL_POS)
    public int position;

//    public VoterModel() {
//    }


    public VoterModel(String VOTING_CENTER, int age, String gender, String voter_namemarathi, String voterno, int votersrno, int part_no, int voter_id, int position) {
        this.VOTING_CENTER = VOTING_CENTER;
        this.age = age;
        this.gender = gender;
        this.voter_namemarathi = voter_namemarathi;
        this.voterno = voterno;
        this.votersrno = votersrno;
        this.part_no = part_no;
        this.voter_id = voter_id;
        this.position = position;
    }


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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
