package com.amir.ss.noteproject.data.model;

import android.net.Uri;

import java.io.Serializable;

public class ContentModel implements Serializable {
    private int id;
    private String displayName;
    private Uri contentUris;

    public void setId(int id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setContentUris(Uri contentUris) {
        this.contentUris = contentUris;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private String relativePath;
    private int width;

//    public ContentModel(Long id, String displayName, Uri contentUris, String relativePath, int width) {
//        this.id = id;
//        this.displayName = displayName;
//        this.contentUris = contentUris;
//        this.relativePath = relativePath;
//        this.width = width;
//    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Uri getContentUris() {
        return contentUris;
    }


}
