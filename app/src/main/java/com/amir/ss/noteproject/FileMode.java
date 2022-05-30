package com.amir.ss.noteproject;

import android.net.Uri;

public class FileMode {
    private String Detail;
    private Uri uri;


    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
