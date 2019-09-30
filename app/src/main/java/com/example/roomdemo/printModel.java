package com.example.roomdemo;

public class printModel {
    String version,name,subjects,minposition,maxposition,examid;



    public printModel(String version, String name, String subjects, String minposition, String maxposition, String examid) {
        this.version = version;
        this.name = name;
        this.subjects = subjects;
        this.minposition = minposition;
        this.maxposition = maxposition;
        this.examid = examid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getMinposition() {
        return minposition;
    }

    public void setMinposition(String minposition) {
        this.minposition = minposition;
    }

    public String getMaxposition() {
        return maxposition;
    }

    public void setMaxposition(String maxposition) {
        this.maxposition = maxposition;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }
}
