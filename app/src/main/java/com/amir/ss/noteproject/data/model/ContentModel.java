package com.amir.ss.noteproject.data.model;

import android.net.Uri;

public class ContentModel {
    private Long id;
    private String displayName;
    private Uri contentUris;
    private String relativePath;
    private int width;

    public ContentModel(Long id, String displayName, Uri contentUris, String relativePath, int width) {
        this.id = id;
        this.displayName = displayName;
        this.contentUris = contentUris;
        this.relativePath = relativePath;
        this.width = width;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Uri getContentUris() {
        return contentUris;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public int getWidth() {
        return width;
    }
}
