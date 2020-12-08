package com.amir.ss.noteproject;

import java.io.Serializable;

public class Category implements Serializable {
    private int Categoryid;



    private String Titel;
    private String Date;
    private String Detail;

    public Category() {

    }
    public int getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(int categoryid) {
        Categoryid = categoryid;
    }

    public String getTitel() {
        return Titel;
    }

    public void setTitel(String name) {
        Titel = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
    public Category(String titel, String date) {
        setTitel(titel);
        setDate(date);
    }
}
