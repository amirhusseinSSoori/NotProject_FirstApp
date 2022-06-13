package com.amir.ss.noteproject.data.datasource.file;

import android.net.Uri;
import android.util.Log;

import com.amir.ss.noteproject.MyApplication;

public class DeleteFileSourceImp implements DeleteFileSource {

    @Override
    public void deleteFile(Uri uri,int id) {
        try {
          MyApplication.getAppContext().getContentResolver().delete(uri, null, null);
        } catch (Throwable exception) {
            exception.printStackTrace();
        }

    }
}
