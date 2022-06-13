package com.amir.ss.noteproject.data.repository.file;

import android.graphics.Bitmap;

import com.amir.ss.noteproject.data.datasource.file.InsertFileSource;

import java.io.IOException;

public class InsertFileRepositoryImp implements InsertFileRepository {

    InsertFileSource insertFileSource;

    public InsertFileRepositoryImp(InsertFileSource insertFileSource) {
        this.insertFileSource = insertFileSource;
    }

    @Override
    public void insertImageToRepository(Bitmap bitmap, String name) throws IOException {
        insertFileSource.saveImage(bitmap, name);
    }
}
