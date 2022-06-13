package com.amir.ss.noteproject.data.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int CategoryId;


    private String Title;
    private String Date;
    private String Detail;

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String name) {
        Title = name;
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
}
