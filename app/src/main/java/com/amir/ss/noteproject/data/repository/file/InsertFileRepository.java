package com.amir.ss.noteproject.data.repository.file;

import android.graphics.Bitmap;

import java.io.IOException;

public interface InsertFileRepository {
    void insertImageToRepository(Bitmap bitmap, String name) throws IOException;
}
